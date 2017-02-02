# Service Builder Gradle Plugin

The Service Builder Gradle plugin allows you to generate a service layer
defined in a [Service Builder](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)
`service.xml` file.

## Usage

To use the plugin, include it in your build script:

```gradle
buildscript {
	dependencies {
		classpath group: "com.liferay", name: "com.liferay.gradle.plugins.service.builder", version: "1.0.10"
	}

	repositories {
		maven {
			url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
		}
	}
}

apply plugin: "com.liferay.portal.tools.service.builder"
```

The Service Builder plugin automatically applies the [`java`](https://docs.gradle.org/current/userguide/java_plugin.html)
plugin.

Since the plugin automatically resolves the Liferay Service Builder library as a
dependency, you have to configure a repository that hosts the library
and its transitive dependencies. The Liferay CDN repository hosts them all:

```gradle
repositories {
	maven {
		url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
	}
}
```

## Tasks

The plugin adds one task to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`buildService` | \- | [`BuildServiceTask`](#buildservicetask) | Runs the Liferay Service Builder.

The `buildService` task is automatically configured with sensible defaults,
depending on whether the [`war`](https://docs.gradle.org/current/userguide/war_plugin.html)
plugin is applied, or whether the [`osgiModule`](#osgimodule) property is `true`:

Property Name | Default Value
------------- | -------------
[`apiDir`](#apidir) | <p>**If the `war` plugin is applied:** `${project.webAppDir}/WEB-INF/service`<p><p>**Otherwise:** `null`</p>
[`hbmFile`](#hbmfile) | <p>**If `osgiModule` is `true`:** `${buildService.resourcesDir}/META-INF/module-hbm.xml`</p><p>**Otherwise:** `${buildService.resourcesDir}/META-INF/module-hbm.xml`</p>
[`implDir`](#impldir) | The first `java` directory of the `main` source set (by default: `src/main/java`).
[`inputFile`](#inputfile) | <p>**If the `war` plugin is applied:** `${project.webAppDir}/WEB-INF/service.xml`<p><p>**Otherwise:** `${project.projectDir}/service.xml`</p>
[`modelHintsFile`](#modelhintsfile) | The file `META-INF/portlet-model-hints.xml` in the first `resources` directory of the `main` source set (by default: `src/main/resources/META-INF/portlet-model-hints.xml`).
[`pluginName`](#pluginname) | <p>**If `osgiModule` is `true`:** `""`</p><p>**Otherwise:** `project.name`</p>
[`propsUtil`](#pluginname) | <p>**If `osgiModule` is `true`:** `"${bundleSymbolicName}.util.ServiceProps"`<br />The `bundleSymbolicName` of the project is inferred via the [`OsgiHelper`](https://github.com/gradle/gradle/blob/master/subprojects/osgi/src/main/java/org/gradle/api/internal/plugins/osgi/OsgiHelper.java) class.</p><p>**Otherwise:** `"com.liferay.util.service.ServiceProps"`</p>
[`resourcesDir`](#resourcesdir) | The first `resources` directory of the `main` source set (by default: `src/main/resources`).
[`springFile`](#springfile) | <p>**If `osgiModule` is `true`:** the file `META-INF/spring/module-spring.xml` in the first `resources` directory of the `main` source set (by default: `src/main/resources/META-INF/spring/module-spring.xml`)</p><p>**Otherwise:** the file `META-INF/portlet-spring.xml` in the first `resources` directory of the `main` source set (by default: `src/main/resources/META-INF/portlet-spring.xml`)</p>
[`sqlDir`](#sqldir) | <p>**If the `war` plugin is applied:** `${project.webAppDir}/WEB-INF/sql`</p><p>**Otherwise:** The directory `META-INF/sql` in the first `resources` directory of the `main` source set (by default: `src/main/resources/META-INF/sql`).</p>

In the [typical scenario](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/defining-an-object-relational-map-with-service-builder)
of a data-driven Liferay OSGi application split in `myapp-app`, `myapp-service`
and `myapp-web` modules, the `service.xml` file is usually contained in the root
directory of `myapp-service`. In the `build.gradle` of the same module, it is
enough to apply the `com.liferay.service.builder` plugin [as described](#usage),
and then add the following snippet to enable the use of Liferay Service Builder:

```gradle
buildService {
	apiDir = "../myapp-api/src/main/java"
	testDir = "../myapp-test/src/testIntegration/java"
}
```

While `apiDir` is required, the `testDir` property assignment can be left out,
in which case Arquillian-based integration test classes are generated.

### BuildServiceTask

Tasks of type `BuildWSDDTask` extend [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html),
so all its properties and methods, such as [`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args(java.lang.Iterable))
and [`maxHeapSize`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:maxHeapSize)
are available. They also have the following properties set by default:

Property Name | Default Value
------------- | -------------
[`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args) | Service Builder command line arguments
[`classpath`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:classpath) | [`project.configurations.serviceBuilder`](#liferay-service-builder-dependency)
[`main`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:main) | `"com.liferay.portal.tools.service.builder.ServiceBuilder"`
[`systemProperties`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:systemProperties) | `["file.encoding": "UTF-8"]`


#### Task Properties

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
<a name="apidir"></a>`apiDir` | `File` | `null` | A directory where the service API Java source files are generated.
`autoImportDefaultReferences` | `boolean` | `true` | Whether to automatically add default references, like `com.liferay.portal.ClassName`, `com.liferay.portal.Resource` and `com.liferay.portal.User`, to the services.
`autoNamespaceTables` | `boolean` | `true` | Whether to prefix table names by the namespace specified in the `service.xml` file.
`beanLocatorUtil` | `String` | `"com.liferay.util.bean.PortletBeanLocatorUtil"` | The fully qualified class name of a bean locator class to use in the generated service classes.
`buildNumber` | `long` | `1` | A specific value to assign the `build.number` property in the `service.properties` file.
`buildNumberIncrement` | `boolean` | `true` | Whether to automatically increment the `build.number` property in the `service.properties` file by one at every service generation.
<a name="hbmfile"></a>`hbmFile` | `File` | `null` | A Hibernate Mapping file to generate.
<a name="impldir"></a>`implDir` | `File` | `null` | A directory where the service Java source files are generated.
<a name="inputfile"></a>`inputFile` | `File` | `null` | The project's `service.xml` file.
`modelHintsConfigs` | `Set` | `["classpath*:META-INF/portal-model-hints.xml", "META-INF/portal-model-hints.xml", "classpath*:META-INF/ext-model-hints.xml", "classpath*:META-INF/portlet-model-hints.xml"]` | Paths to the [model hints](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/customizing-model-entities-with-model-hints) files for Liferay Service Builder to use in generating the service layer.
<a name="modelhintsfile"></a>`modelHintsFile` | `File` | `null` | A model hints file for the project.
<a name="osgimodule"></a>`osgiModule` | `boolean` | `false` | Whether to generate the service layer for OSGi modules.
<a name="pluginname"></a>`pluginName` | `String` | `null` | If specified, a plugin can enable additional generation features, such as `Clp` class generation, for non-OSGi modules.
<a name="propsutil"></a>`propsUtil` | `String` | `null` | The fully qualified class name of the service properties util class to generate.
`readOnlyPrefixes` | `Set` | `["fetch", "get", "has", "is", "load", "reindex", "search"]` | Prefixes of methods to consider read-only.
`resourceActionsConfigs` | `Set` | `["META-INF/resource-actions/default.xml", "resource-actions/default.xml"]` | Paths to the [resource actions](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/adding-permissions-to-resources) files for Liferay Service Builder to use in generating the service layer.
<a name="resourcesdir"></a>`resourcesDir` | `File` | `null` | A directory where the service non-Java files are generated.
<a name="springfile"></a>`springFile` | `File` | `null` | A service Spring file to generate.
`springNamespaces` | `Set` | `["beans"]` | Namespaces of Spring XML Schemas to add to the service Spring file.
<a name="sqldir"></a>`sqlDir` | `File` | `null` | A directory where the SQL files are generated.
`sqlFileName` | `String` | `"tables.sql"` | A name (relative to `sqlDir`) for the file in which the SQL table creation instructions are generated.
`sqlIndexesFileName` | `String` | `"indexes.sql"` | A name (relative to `sqlDir`) for the file in which the SQL index creation instructions are generated.
`sqlSequencesFileName` | `String` | `"sequences.sql"` | A name (relative to `sqlDir`) for the file in which the SQL sequence creation instructions are generated.
`targetEntityName` | `String` | `null` | If specified, it's the name of the entity for which Liferay Service Builder should generate the service.
`testDir` | `File` | `null` | If specified, it's a directory where integration test Java source files are generated.

The properties of type `File` supports any type that can be resolved by [`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file(java.lang.Object)).
Moreover, it is possible to use Closures and Callables as values for the
`String` properties, to defer evaluation until task execution.

## Additional Configuration

There are additional configurations that can help you use Service Builder.

### Liferay Service Builder Dependency

By default, the plugin creates a configuration called `serviceBuilder` and adds
a dependency to the latest released version of Liferay Service Builder. It is
possible to override this setting and use a specific version of the tool by
manually adding a dependency to the `serviceBuilder` configuration:

```gradle
dependencies {
	serviceBuilder group: "com.liferay", name: "com.liferay.portal.tools.service.builder", version: "1.0.122"
}
```
