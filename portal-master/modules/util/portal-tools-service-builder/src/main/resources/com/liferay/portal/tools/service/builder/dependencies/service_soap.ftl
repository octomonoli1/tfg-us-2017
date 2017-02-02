package ${packagePath}.service.http;

import ${apiPackagePath}.service.${entity.name}ServiceUtil;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the SOAP utility for the
 * {@link ${apiPackagePath}.service.${entity.name}ServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
<#if entity.hasColumns()>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link ${apiPackagePath}.model.${entity.name}Soap}.
 * If the method in the service utility returns a
 * {@link ${apiPackagePath}.model.${entity.name}}, that is translated to a
 * {@link ${apiPackagePath}.model.${entity.name}Soap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
</#if>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author ${author}
 * @see ${entity.name}ServiceHttp
<#if entity.hasColumns()>
 * @see ${apiPackagePath}.model.${entity.name}Soap
</#if>
 * @see ${apiPackagePath}.service.${entity.name}ServiceUtil
<#if classDeprecated>
 * @deprecated ${classDeprecatedComment}
</#if>
 * @generated
 */

<#if classDeprecated>
	@Deprecated
</#if>

@ProviderType
public class ${entity.name}ServiceSoap {

	<#assign hasMethods = false>

	<#list methods as method>
		<#if !method.isConstructor() && method.isPublic() && serviceBuilder.isCustomMethod(method) && serviceBuilder.isSoapMethod(method)>
			<#assign hasMethods = true>

			<#assign returnValueName = method.returns.value>
			<#assign returnValueDimension = serviceBuilder.getDimensions(method.returns.dimensions)>
			<#assign returnTypeGenericsName = serviceBuilder.getTypeGenericsName(method.returns)>
			<#assign extendedModelName = apiPackagePath + ".model." + entity.name>
			<#assign soapModelName = apiPackagePath + ".model." + entity.name + "Soap">

			${serviceBuilder.getJavadocComment(method)}

			<#if serviceBuilder.hasAnnotation(method, "Deprecated")>
				@Deprecated
			</#if>

			public static

			<#if returnValueName == extendedModelName>
				${soapModelName}${returnValueDimension}
			<#elseif stringUtil.startsWith(returnValueName, apiPackagePath + ".model.") && serviceBuilder.hasEntityByGenericsName(returnValueName)>
				${returnValueName}Soap${returnValueDimension}
			<#elseif stringUtil.startsWith(returnValueName, "com.liferay.portal.kernel.json.JSON")>
				java.lang.String
			<#elseif stringUtil.startsWith(returnValueName, "com.liferay.portal.kernel.repository.model.")>
				${returnValueName}Soap
			<#elseif returnValueName == "java.util.List">
				<#if returnTypeGenericsName == "java.util.List<java.lang.Boolean>">
					java.lang.Boolean[]
				<#elseif returnTypeGenericsName == "java.util.List<java.lang.Double>">
					java.lang.Double[]
				<#elseif returnTypeGenericsName == "java.util.List<java.lang.Float>">
					java.lang.Float[]
				<#elseif returnTypeGenericsName == "java.util.List<java.lang.Integer>">
					java.lang.Integer[]
				<#elseif returnTypeGenericsName == "java.util.List<java.lang.Long>">
					java.lang.Long[]
				<#elseif returnTypeGenericsName == "java.util.List<java.lang.Short>">
					java.lang.Short[]
				<#elseif returnTypeGenericsName == "java.util.List<java.lang.String>">
					java.lang.String[]
				<#elseif returnTypeGenericsName == ("java.util.List<" + extendedModelName + ">")>
					${soapModelName}[]
				<#elseif stringUtil.startsWith(returnTypeGenericsName, "java.util.List<com.liferay.portal.kernel.repository.model.")>
					${serviceBuilder.getListActualTypeArguments(method.getReturns())}Soap[]
				<#elseif entity.hasColumns() && (extendedModelName == serviceBuilder.getListActualTypeArguments(method.getReturns()))>
					${soapModelName}[]
				<#elseif !entity.hasColumns()>
					${serviceBuilder.getListActualTypeArguments(method.getReturns())}[]
				<#else>
					${serviceBuilder.getListActualTypeArguments(method.getReturns())}Soap[]
				</#if>
			<#else>
				${returnTypeGenericsName}
			</#if>

			${method.name}(

			<#assign localizationMapVariables = "">

			<#list method.parameters as parameter>
				<#assign parameterTypeName = serviceBuilder.getTypeGenericsName(parameter.type)>
				<#assign parameterListActualType = serviceBuilder.getListActualTypeArguments(parameter.type)>

				<#if parameterTypeName == "java.util.Locale">
					<#assign parameterTypeName = "String">
				<#elseif parameterTypeName == "java.util.List<java.lang.Long>">
					<#assign parameterTypeName = "Long[]">
				<#elseif (parameter.type.value == "java.util.List") && serviceBuilder.hasEntityByGenericsName(parameterListActualType)>
					<#assign parameterEntity = serviceBuilder.getEntityByGenericsName(parameterListActualType)>

					<#assign parameterTypeName = parameterEntity.apiPackagePath + ".model." + parameterEntity.name + "Soap[]">
				<#elseif serviceBuilder.hasEntityByParameterTypeValue(parameter.type.value)>
					<#assign parameterEntity = serviceBuilder.getEntityByParameterTypeValue(parameter.type.value)>

					<#assign parameterTypeName = parameterEntity.apiPackagePath + ".model." + parameterEntity.name + "Soap">
				</#if>

				<#if parameterTypeName == "java.util.Map<java.util.Locale, java.lang.String>">
					java.lang.String[] ${parameter.name}LanguageIds, java.lang.String[] ${parameter.name}Values

					<#assign localizationMapVariables = localizationMapVariables + "Map<Locale, String>" + parameter.name + " = LocalizationUtil.getLocalizationMap(" + parameter.name + "LanguageIds, " + parameter.name + "Values);">
				<#else>
					${parameterTypeName} ${parameter.name}
				</#if>

				<#if parameter_has_next>
					,
				</#if>
			</#list>

			) throws RemoteException {
				try {
					${localizationMapVariables}

					<#if returnValueName != "void">
						${returnTypeGenericsName} returnValue =
					</#if>

					${entity.name}ServiceUtil.${method.name}(

					<#list method.parameters as parameter>
						<#assign parameterTypeName = serviceBuilder.getTypeGenericsName(parameter.type)>
						<#assign parameterListActualType = serviceBuilder.getListActualTypeArguments(parameter.type)>

						<#if parameterTypeName == "java.util.Locale">
							LocaleUtil.fromLanguageId(
						<#elseif parameterTypeName == "java.util.List<java.lang.Long>">
							ListUtil.toList(
						<#elseif (parameter.type.value == "java.util.List") && serviceBuilder.hasEntityByGenericsName(parameterListActualType)>
							<#assign parameterEntity = serviceBuilder.getEntityByGenericsName(parameterListActualType)>

							${parameterEntity.packagePath}.model.impl.${parameterEntity.name}ModelImpl.toModels(
						<#elseif serviceBuilder.hasEntityByParameterTypeValue(parameter.type.value)>
							<#assign parameterEntity = serviceBuilder.getEntityByGenericsName(parameter.type.value)>

							${parameterEntity.packagePath}.model.impl.${parameterEntity.name}ModelImpl.toModel(
						</#if>

						${parameter.name}

						<#if parameterTypeName == "java.util.Locale">
							)
						<#elseif parameterTypeName == "java.util.List<java.lang.Long>">
							)
						<#elseif (parameter.type.value == "java.util.List") && serviceBuilder.hasEntityByGenericsName(parameterListActualType)>
							)
						<#elseif serviceBuilder.hasEntityByParameterTypeValue(parameter.type.value)>
							)
						</#if>

						<#if parameter_has_next>
							,
						</#if>
					</#list>

					);

					<#if returnValueName != "void">
						<#if returnValueName == extendedModelName>
							<#if returnValueDimension == "">
								return ${soapModelName}.toSoapModel(returnValue);
							<#else>
								return ${soapModelName}.toSoapModels(returnValue);
							</#if>
						<#elseif stringUtil.startsWith(returnValueName, apiPackagePath + ".model.") && serviceBuilder.hasEntityByGenericsName(returnValueName)>
							<#if returnValueDimension == "">
								return ${returnValueName}Soap.toSoapModel(returnValue);
							<#else>
								return ${returnValueName}Soap.toSoapModels(returnValue);
							</#if>
						<#elseif stringUtil.startsWith(returnValueName, "com.liferay.portal.kernel.json.JSON")>
							return returnValue.toString();
						<#elseif stringUtil.startsWith(returnValueName, "com.liferay.portal.kernel.repository.model.")>
							return ${returnValueName}Soap.toSoapModel(returnValue);
						<#elseif returnValueName == "java.util.List">
							<#if returnTypeGenericsName == "java.util.List<java.lang.Boolean>">
								return returnValue.toArray(new java.lang.Boolean[returnValue.size()]);
							<#elseif returnTypeGenericsName == "java.util.List<java.lang.Double>">
								return returnValue.toArray(new java.lang.Double[returnValue.size()]);
							<#elseif returnTypeGenericsName == "java.util.List<java.lang.Integer>">
								return returnValue.toArray(new java.lang.Integer[returnValue.size()]);
							<#elseif returnTypeGenericsName == "java.util.List<java.lang.Float>">
								return returnValue.toArray(new java.lang.Float[returnValue.size()]);
							<#elseif returnTypeGenericsName == "java.util.List<java.lang.Long>">
								return returnValue.toArray(new java.lang.Long[returnValue.size()]);
							<#elseif returnTypeGenericsName == "java.util.List<java.lang.Short>">
								return returnValue.toArray(new java.lang.Short[returnValue.size()]);
							<#elseif returnTypeGenericsName == "java.util.List<java.lang.String>">
								return returnValue.toArray(new java.lang.String[returnValue.size()]);
							<#elseif returnTypeGenericsName == ("java.util.List<" + extendedModelName + ">")>
								return ${extendedModelName}Soap.toSoapModels(returnValue);
							<#elseif stringUtil.startsWith(returnTypeGenericsName, "java.util.List<com.liferay.portal.kernel.repository.model.")>
								return ${serviceBuilder.getListActualTypeArguments(method.getReturns())}Soap.toSoapModels(returnValue);
							<#elseif entity.hasColumns() && (extendedModelName == serviceBuilder.getListActualTypeArguments(method.getReturns()))>
								return ${soapModelName}.toSoapModels(returnValue);
							<#elseif !entity.hasColumns()>
								return returnValue.toArray(new ${serviceBuilder.getListActualTypeArguments(method.getReturns())}[returnValue.size()]);
							<#else>
								return ${serviceBuilder.getListActualTypeArguments(method.getReturns())}Soap.toSoapModels(returnValue);
							</#if>
						<#else>
							return returnValue;
						</#if>
					</#if>
				}
				catch (Exception e) {
					_log.error(e, e);

					throw new RemoteException(e.getMessage());
				}
			}
		</#if>
	</#list>

	<#if hasMethods>
		private static Log _log = LogFactoryUtil.getLog(${entity.name}ServiceSoap.class);
	</#if>

}