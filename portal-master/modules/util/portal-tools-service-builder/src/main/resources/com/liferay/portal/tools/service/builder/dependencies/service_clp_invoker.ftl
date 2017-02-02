package ${apiPackagePath}.service.base;

import ${apiPackagePath}.service.${entity.name}${sessionTypeName}ServiceUtil;

import aQute.bnd.annotation.ProviderType;

import java.util.Arrays;

/**
 * @author ${author}
<#if classDeprecated>
 * @deprecated ${classDeprecatedComment}
</#if>
 * @generated
 */

<#if classDeprecated>
	@Deprecated
</#if>

@ProviderType
public class ${entity.name}${sessionTypeName}ServiceClpInvoker {

	public ${entity.name}${sessionTypeName}ServiceClpInvoker() {
		<#list methods as method>
			<#if !method.isConstructor() && method.isPublic() && serviceBuilder.isCustomMethod(method) && (method.name != "invokeMethod")>
				<#assign parameters = method.parameters>

				_methodName${method_index} = "${method.name}";

				_methodParameterTypes${method_index} = new String[] {

				<#list parameters as parameter>
					"${parameter.type}${serviceBuilder.getDimensions(parameter.type.dimensions)}"

					<#if parameter_has_next>
						,
					</#if>
				</#list>

				};
			</#if>
		</#list>
	}

	public Object invokeMethod(
			String name, String[] parameterTypes, Object[] arguments)
		throws Throwable {

		<#list methods as method>
			<#if !method.isConstructor() && method.isPublic() && serviceBuilder.isCustomMethod(method) && (method.name != "invokeMethod")>
				<#assign returnTypeName = serviceBuilder.getTypeGenericsName(method.returns)>
				<#assign parameters = method.parameters>

				if (_methodName${method_index}.equals(name) && Arrays.deepEquals(_methodParameterTypes${method_index}, parameterTypes)) {
					<#if returnTypeName != "void">
						return
					</#if>

					${entity.name}${sessionTypeName}ServiceUtil.${method.name}(

					<#list parameters as parameter>
						<#assign parameterTypeName = serviceBuilder.getTypeGenericsName(parameter.type)>

						<#if (parameterTypeName == "boolean") || (parameterTypeName == "double") || (parameterTypeName == "float") || (parameterTypeName == "int") || (parameterTypeName == "long") || (parameterTypeName == "short")>
							((${serviceBuilder.getPrimitiveObj(parameter.type)})arguments[${parameter_index}])${serviceBuilder.getPrimitiveObjValue(serviceBuilder.getPrimitiveObj(parameter.type))}
						<#elseif (method.name == "dynamicQuery") && (parameterTypeName == "com.liferay.portal.kernel.util.OrderByComparator<T>")>
							(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[${parameter_index}]
						<#else>
							(${parameterTypeName})arguments[${parameter_index}]
						</#if>

						<#if parameter_has_next>
							,
						</#if>
					</#list>

					);

					<#if returnTypeName == "void">
						return null;
					</#if>

				}
			</#if>
		</#list>

		throw new UnsupportedOperationException();
	}

	<#list methods as method>
		<#if !method.isConstructor() && method.isPublic() && serviceBuilder.isCustomMethod(method) && (method.name != "invokeMethod")>
			<#assign parameters = method.parameters>

			private String _methodName${method_index};
			private String[] _methodParameterTypes${method_index};
		</#if>
	</#list>

}