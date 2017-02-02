# WSDD Builder Gradle Plugin

The WSDD Builder Gradle plugin allows you to run the Liferay WSDD Builder tool
in order to generate the [Apache Axis](http://axis.apache.org/axis/) Web Service
Deployment Descriptor (WSDD) files from a [Service Builder](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)
`service.xml` file.

## Usage

To use the plugin, include it in your build script:

```gradle
buildscript {
	dependencies {
		classpath group: "com.liferay", name: "com.liferay.gradle.plugins.wsdd.builder", version: "1.0.8"
	}

	repositories {
		maven {
			url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
		}
	}
}

apply plugin: "com.liferay.portal.tools.wsdd.builder"
```

The WSDD Builder plugin automatically applies the [`java`](https://docs.gradle.org/current/userguide/java_plugin.html)
plugin.

Since the plugin automatically resolves the Liferay WSDD Builder library as a
dependency, you have to configure a repository that hosts the library and its
transitive dependencies. The Liferay CDN repository hosts them all:

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
`buildWSDD` | [`compileJava`](https://docs.gradle.org/current/userguide/java_plugin.html#sec:compile) | [`BuildWSDDTask`](#buildwsddtask) | Runs the Liferay WSDD Builder.

By default, the `buildWSDD` task uses the `${project.projectDir}/service.xml`
file as input. Then, it generates `${project.projectDir}/server-config.wsdd` and
the `*_deploy.wsdd` and `*_undeploy.wsdd` files in the first [`resources`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceSet.html#org.gradle.api.tasks.SourceSet:resources)
directory of the `main` [source set](https://docs.gradle.org/current/userguide/java_plugin.html#N1503E)
(by default: `src/main/resources`).

If the [`war`](https://docs.gradle.org/current/userguide/war_plugin.html)
plugin is applied, the task uses `${project.webAppDir}/WEB-INF/service.xml` as
input to generate `${project.webAppDir}/WEB-INF/server-config.wsdd`. The
`*_deploy.wsdd` and `*_undeploy.wsdd` files are still generated in the first
`resources` directory of the `main` source set.

Liferay WSDD Build Service requires an additional classpath (configured with the
`buildWSDD.builderClasspath` property), to correctly generate the WSDD files.
The `buildWSDD` task uses the following default value, which creates an implicit
dependency to the `compileJava` task:

```gradle
tasks.compileJava.outputs.files + sourceSets.main.compileClasspath + sourceSets.main.runtimeClasspath
```

### BuildWSDDTask

Tasks of type `BuildWSDDTask` extend [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html),
so all its properties and methods, such as [`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args(java.lang.Iterable))
and [`maxHeapSize`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:maxHeapSize),
are available. They also have the following properties set by default:

Property Name | Default Value
------------- | -------------
[`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args) | WSDD Builder command line arguments
[`classpath`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:classpath) | [`project.configurations.wsddBuilder`](#liferay-wsdd-builder-dependency)
[`main`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:main) | `"com.liferay.portal.tools.wsdd.builder.WSDDBuilder"`

#### Task Properties

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`builderClasspath` | `String` | `null` | A classpath that the Liferay WSDD Builder uses to generate WSDD files.
`inputFile` | `File` | `null` | A `service.xml` from which to generate the WSDD files.
`outputDir` | `File` | `null` | A directory where the `*_deploy.wsdd` and `*_undeploy.wsdd` files are generated.
`serverConfigFile` | `File` | `${project.projectDir}/server-config.wsdd` | A `server-config.wsdd` file to generate.
`serviceNamespace` | `String` | `"Plugin"` | A namespace for the WSDD Service.

The properties of type `File` support any type that can be resolved by [`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file(java.lang.Object)).
Moreover, it is possible to use Closures and Callables as values for the
`String` properties, to defer evaluation until task execution.

## Additional Configuration

There are additional configurations that can help you use the WSDD Builder.

### Liferay WSDD Builder Dependency

By default, the plugin creates a configuration called `wsddBuilder` and adds a
dependency to the latest released version of the Liferay WSDD Builder. It is
possible to override this setting and use a specific version of the tool by
manually adding a dependency to the `wsddBuilder` configuration:

```gradle
dependencies {
	wsddBuilder group: "com.liferay", name: "com.liferay.portal.tools.wsdd.builder", version: "1.0.4"
}
```
