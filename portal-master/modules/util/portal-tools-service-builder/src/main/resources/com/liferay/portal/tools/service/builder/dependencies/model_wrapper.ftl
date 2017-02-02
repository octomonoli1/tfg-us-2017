package ${apiPackagePath}.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.sql.Blob;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ${entity.name}}.
 * </p>
 *
 * @author ${author}
 * @see ${entity.name}
<#if classDeprecated>
 * @deprecated ${classDeprecatedComment}
</#if>
 * @generated
 */

<#if classDeprecated>
	@Deprecated
</#if>

@ProviderType
public class ${entity.name}Wrapper implements ${entity.name}, ModelWrapper<${entity.name}> {

	public ${entity.name}Wrapper(${entity.name} ${entity.varName}) {
		_${entity.varName} = ${entity.varName};
	}

	@Override
	public Class<?> getModelClass() {
		return ${entity.name}.class;
	}

	@Override
	public String getModelClassName() {
		return ${entity.name}.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		<#list entity.regularColList as column>
			attributes.put("${column.name}", get${column.methodName}());
		</#list>

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		<#list entity.regularColList as column>
			<#if column.isPrimitiveType()>
				${serviceBuilder.getPrimitiveObj(column.type)}
			<#else>
				${column.genericizedType}
			</#if>

			${column.name} =

			<#if column.isPrimitiveType()>
				(${serviceBuilder.getPrimitiveObj(column.type)})
			<#else>
				(${column.genericizedType})
			</#if>

			attributes.get("${column.name}");

			if (${column.name} != null) {
				set${column.methodName}(${column.name});
			}
		</#list>
	}

	<#list methods as method>
		<#assign parameters = method.parameters>

		<#if !method.isConstructor() && !method.isStatic() && method.isPublic() && !(method.name == "equals" && (parameters?size == 1))>
			<#if method.name == "getStagedModelType">
				<#assign hasGetStagedModelTypeMethod = true>
			</#if>

			${serviceBuilder.getJavadocComment(method)}

			<#if serviceBuilder.hasAnnotation(method, "Deprecated")>
				@Deprecated
			</#if>

			@Override
			public ${serviceBuilder.getTypeGenericsName(method.returns)} ${method.name} (

			<#list parameters as parameter>
				${serviceBuilder.getTypeGenericsName(parameter.type)} ${parameter.name}

				<#if parameter_has_next>
					,
				</#if>
			</#list>

			)

			<#list method.exceptions as exception>
				<#if exception_index == 0>
					throws
				</#if>

				${exception.value}

				<#if exception_has_next>
					,
				</#if>
			</#list>

			{
				<#if method.name == "clone" && (parameters?size == 0)>
					return new ${entity.name}Wrapper((${entity.name})_${entity.varName}.clone());
				<#elseif (method.name == "toEscapedModel" || method.name == "toUnescapedModel") && (parameters?size == 0)>
					return new ${entity.name}Wrapper(_${entity.varName}.${method.name}());
				<#else>
					<#if method.returns.value != "void">
						return
					</#if>

					_${entity.varName}.${method.name}(

					<#list method.parameters as parameter>
						${parameter.name}

						<#if parameter_has_next>
							,
						</#if>
					</#list>

					);
				</#if>
			}
		</#if>
	</#list>

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ${entity.name}Wrapper)) {
			return false;
		}

		${entity.name}Wrapper ${entity.varName}Wrapper = (${entity.name}Wrapper)obj;

		if (Objects.equals(_${entity.varName}, ${entity.varName}Wrapper._${entity.varName})) {
			return true;
		}

		return false;
	}

	<#if entity.isHierarchicalTree()>
		@Override
		public long getNestedSetsTreeNodeLeft() {
			return _${entity.varName}.getNestedSetsTreeNodeLeft();
		}

		@Override
		public long getNestedSetsTreeNodeRight() {
			return _${entity.varName}.getNestedSetsTreeNodeRight();
		}

		@Override
		public long getNestedSetsTreeNodeScopeId() {
			return _${entity.varName}.getNestedSetsTreeNodeScopeId();
		}

		@Override
		public void setNestedSetsTreeNodeLeft(long nestedSetsTreeNodeLeft) {
			_${entity.varName}.setNestedSetsTreeNodeLeft(nestedSetsTreeNodeLeft);
		}

		@Override
		public void setNestedSetsTreeNodeRight(long nestedSetsTreeNodeRight) {
			_${entity.varName}.setNestedSetsTreeNodeRight(nestedSetsTreeNodeRight);
		}
	</#if>

	<#if entity.isStagedModel() && !hasGetStagedModelTypeMethod!false>
		@Override
		public StagedModelType getStagedModelType() {
			return _${entity.varName}.getStagedModelType();
		}
	</#if>

	@Override
	public ${entity.name} getWrappedModel() {
		return _${entity.varName};
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _${entity.varName}.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _${entity.varName}.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_${entity.varName}.resetOriginalValues();
	}

	private final ${entity.name} _${entity.varName};

}