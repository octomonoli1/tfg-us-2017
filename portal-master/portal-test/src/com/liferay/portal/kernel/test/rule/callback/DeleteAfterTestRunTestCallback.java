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

package com.liferay.portal.kernel.test.rule.callback;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.runner.Description;

/**
 * @author Shuyang Zhou
 */
public class DeleteAfterTestRunTestCallback
	extends BaseTestCallback<Object, Object> {

	public static final DeleteAfterTestRunTestCallback INSTANCE =
		new DeleteAfterTestRunTestCallback();

	@Override
	public void afterMethod(
		Description description, Object object, Object target) {

		Class<?> testClass = description.getTestClass();

		Map<Class<?>, FieldBag> deleteAfterTestRunFieldBags = new HashMap<>();

		while (testClass != null) {
			for (Field field : testClass.getDeclaredFields()) {
				DeleteAfterTestRun deleteAfterTestRun = field.getAnnotation(
					DeleteAfterTestRun.class);

				if (deleteAfterTestRun == null) {
					continue;
				}

				Class<?> fieldClass = field.getType();

				if (PersistedModel.class.isAssignableFrom(fieldClass)) {
					addField(deleteAfterTestRunFieldBags, fieldClass, field);

					continue;
				}

				if (fieldClass.isArray()) {
					if (!PersistedModel.class.isAssignableFrom(
							fieldClass.getComponentType())) {

						throw new IllegalArgumentException(
							"Unable to annotate field " + field +
								" because it is not an array of type " +
									PersistedModel.class.getName());
					}

					addField(
						deleteAfterTestRunFieldBags,
						fieldClass.getComponentType(), field);

					continue;
				}

				if (Collection.class.isAssignableFrom(fieldClass)) {
					try {
						field.setAccessible(true);

						Collection<?> collection = (Collection<?>)field.get(
							target);

						if ((collection == null) || collection.isEmpty()) {
							continue;
						}

						Class<?> collectionType = getCollectionType(collection);

						if (collectionType == null) {
							throw new IllegalArgumentException(
								"Unable to annotate field " + field +
									" because it is not a collection of type " +
										PersistedModel.class.getName());
						}

						addField(
							deleteAfterTestRunFieldBags, collectionType, field);
					}
					catch (Exception e) {
						_log.error(
							"Unable to detect collection element type", e);
					}

					continue;
				}

				StringBundler sb = new StringBundler(6);

				sb.append("Unable to annotate field ");
				sb.append(field);
				sb.append(" because it is not type of ");
				sb.append(PersistedModel.class.getName());
				sb.append(" nor an array or collection of ");
				sb.append(PersistedModel.class.getName());

				throw new IllegalArgumentException(sb.toString());
			}

			testClass = testClass.getSuperclass();
		}

		Set<Map.Entry<Class<?>, FieldBag>> set =
			deleteAfterTestRunFieldBags.entrySet();

		Iterator<Map.Entry<Class<?>, FieldBag>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<Class<?>, FieldBag> entry = iterator.next();

			Class<?> clazz = entry.getKey();

			if (_orderedClasses.contains(clazz)) {
				continue;
			}

			iterator.remove();

			removeField(entry.getValue(), target);
		}

		for (Class<?> clazz : _orderedClasses) {
			FieldBag fieldBag = deleteAfterTestRunFieldBags.remove(clazz);

			if (fieldBag == null) {
				continue;
			}

			removeField(fieldBag, target);
		}
	}

	protected void addField(
		Map<Class<?>, FieldBag> deleteAfterTestRunFieldBags, Class<?> clazz,
		Field field) {

		FieldBag fieldBag = deleteAfterTestRunFieldBags.get(clazz);

		if (fieldBag == null) {
			fieldBag = new FieldBag(clazz);

			deleteAfterTestRunFieldBags.put(clazz, fieldBag);
		}

		field.setAccessible(true);

		fieldBag.addField(field);
	}

	protected Class<? extends PersistedModel> getCollectionType(
		Collection<?> collection) {

		Class<? extends PersistedModel> collectionType = null;

		for (Object object : collection) {
			Queue<Class<?>> classes = new LinkedList<>();

			classes.add(object.getClass());

			Class<?> clazz = null;

			while ((clazz = classes.poll()) != null) {
				if (ArrayUtil.contains(
						clazz.getInterfaces(), PersistedModel.class)) {

					if (collectionType == null) {
						collectionType = (Class<? extends PersistedModel>)clazz;
					}
					else if (collectionType != clazz) {
						return null;
					}

					break;
				}

				classes.add(clazz.getSuperclass());
				classes.addAll(Arrays.asList(clazz.getInterfaces()));
			}
		}

		return collectionType;
	}

	protected void removeField(FieldBag fieldBag, Object instance) {
		try {
			Class<?> fieldClass = fieldBag.getFieldClass();

			PersistedModelLocalService persistedModelLocalService =
				PersistedModelLocalServiceRegistryUtil.
					getPersistedModelLocalService(fieldClass.getName());

			for (Field field : fieldBag.getFields()) {
				Object object = field.get(instance);

				if (object == null) {
					continue;
				}

				Class<?> objectClass = object.getClass();

				if (objectClass.isArray()) {
					for (PersistedModel persistedModel :
							(PersistedModel[])object) {

						if (persistedModel == null) {
							continue;
						}

						persistedModelLocalService.deletePersistedModel(
							persistedModel);
					}
				}
				else if (Collection.class.isAssignableFrom(objectClass)) {
					Collection<? extends PersistedModel> collection =
						(Collection<? extends PersistedModel>)object;

					for (PersistedModel persistedModel : collection) {
						persistedModelLocalService.deletePersistedModel(
							persistedModel);
					}
				}
				else {
					persistedModelLocalService.deletePersistedModel(
						(PersistedModel)object);
				}

				field.set(instance, null);
			}
		}
		catch (Exception e) {
			_log.error("Unable to delete", e);
		}
	}

	protected static class FieldBag {

		public FieldBag(Class<?> fieldClass) {
			_fieldClass = fieldClass;
		}

		public void addField(Field field) {
			_fields.add(field);
		}

		public Class<?> getFieldClass() {
			return _fieldClass;
		}

		public List<Field> getFields() {
			return _fields;
		}

		private final Class<?> _fieldClass;
		private final List<Field> _fields = new ArrayList<>();

	}

	private DeleteAfterTestRunTestCallback() {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DeleteAfterTestRunTestCallback.class);

	private static final Set<Class<?>> _orderedClasses = new LinkedHashSet<>(
		Arrays.<Class<?>>asList(
			User.class, Organization.class, Role.class, UserGroup.class,
			Group.class, LayoutPrototype.class, LayoutSetPrototype.class,
			Company.class));

}