/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.spring.context;

import com.liferay.portal.kernel.spring.util.SpringFactoryUtil;
import com.liferay.portal.spring.aop.ChainableMethodAdviceInjectorCollector;

import java.util.Map;

import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanIsAbstractException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletBeanFactoryPostProcessor
	implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(
		ConfigurableListableBeanFactory configurableListableBeanFactory) {

		ChainableMethodAdviceInjectorCollector.collect(
			configurableListableBeanFactory);

		ClassLoader classLoader = getClassLoader();

		configurableListableBeanFactory.setBeanClassLoader(classLoader);

		ListableBeanFactory parentListableBeanFactory =
			(ListableBeanFactory)
				configurableListableBeanFactory.getParentBeanFactory();

		if (parentListableBeanFactory != null) {
			Map<String, BeanPostProcessor> beanPostProcessors =
				parentListableBeanFactory.getBeansOfType(
					BeanPostProcessor.class, true, false);

			for (BeanPostProcessor beanPostProcessor :
					beanPostProcessors.values()) {

				if (beanPostProcessor instanceof BeanFactoryAware) {
					BeanFactoryAware beanFactoryAware =
						(BeanFactoryAware)beanPostProcessor;

					beanFactoryAware.setBeanFactory(
						configurableListableBeanFactory);
				}

				if (beanPostProcessor instanceof
						AbstractAutoProxyCreator) {

					AbstractAutoProxyCreator abstractAutoProxyCreator =
						(AbstractAutoProxyCreator)beanPostProcessor;

					abstractAutoProxyCreator.setProxyClassLoader(classLoader);
				}

				configurableListableBeanFactory.addBeanPostProcessor(
					beanPostProcessor);
			}
		}

		String[] names =
			configurableListableBeanFactory.getBeanDefinitionNames();

		for (String name : names) {
			if (!name.contains(SpringFactoryUtil.class.getName())) {
				continue;
			}

			try {
				Object bean = configurableListableBeanFactory.getBean(name);

				if (bean instanceof BeanPostProcessor) {
					configurableListableBeanFactory.addBeanPostProcessor(
						(BeanPostProcessor)bean);
				}
			}
			catch (BeanIsAbstractException biae) {
				continue;
			}
		}
	}

	protected ClassLoader getClassLoader() {
		return PortletApplicationContext.getBeanClassLoader();
	}

}