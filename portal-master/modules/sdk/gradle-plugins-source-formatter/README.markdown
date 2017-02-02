# Source Formatter Gradle Plugin

The Source Formatter Gradle plugin allows you to format project files using the
Liferay Source Formatter tool.

## Usage

To use the plugin, include it in your build script:

```gradle
buildscript {
	dependencies {
		classpath group: "com.liferay", name: "com.liferay.gradle.plugins.source.formatter", version: "1.0.19"
	}

	repositories {
		maven {
			url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
		}
	}
}

apply plugin: "com.liferay.source.formatter"
```

Since the plugin automatically resolves the Liferay Source Formatter library as
a dependency, you have to configure a repository that hosts the library and its
transitive dependencies. The Liferay CDN repository hosts them all:

```gradle
repositories {
	maven {
		url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
	}
}
```

## Tasks

The plugin adds two tasks to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`checkSourceFormatting` | \- | [`FormatSourceTask`](#formatsourcetask) | Runs the Liferay Source Formatter to check for source formatting errors.
`formatSource` | \- | [`FormatSourceTask`](#formatsourcetask) | Runs the Liferay Source Formatter to format the project files.

If desired, it is possible to check for source formatting errors while executing
the [`check`](https://docs.gradle.org/current/userguide/java_plugin.html#N15056)
task by adding the following dependency:

```gradle
check {
	dependsOn checkSourceFormatting
}
```

The same can be achieved by adding the following snippet to the `build.gradle`
file in the root directory of a [*Liferay Workspace*](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace):

```gradle
subprojects {
	afterEvaluate {
		if (plugins.hasPlugin("base") && plugins.hasPlugin("com.liferay.source.formatter")) {
			check.dependsOn checkSourceFormatting
		}
	}
}
```

### FormatSourceTask

Tasks of type `FormatSourceTask` extend [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html),
so all its properties and methods, like [`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args(java.lang.Iterable))
and [`maxHeapSize`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:maxHeapSize)
are available. They also have the following properties set by default:

Property Name | Default Value
------------- | -------------
[`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args) | Source Formatter command line arguments
[`classpath`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:classpath) | [`project.configurations.sourceFormatter`](#liferay-source-formatter-dependency)
[`main`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:main) | `"com.liferay.source.formatter.SourceFormatter"`

#### Task Properties

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`autoFix` | `boolean` | `false` | Whether to automatically fix source formatting errors.
`baseDir` | `File` |  | The Source Formatter base directory. *(Read-only)*
`baseDirName` | `String` | `"./"` | The name of the Source Formatter base directory, relative to the project directory.
`copyrightFile` | `File` | | The file containing the copyright header enforced by Source Formatter. *(Read-only)*
`copyrightFileName` | `String` | `"copyright.txt"` | The name of the file containing the copyright header enforced by Source Formatter, relative to the project directory.
`files` | `List<File>` | | The list of files to format. *(Read-only)*
`fileNames` | `List<String>` | `null` | The file names to format, relative to the project directory. If `null`, all files contained in `baseDir` will be formatted.
`formatCurrentBranch` | `boolean` | `false` | Whether to format only the files contained in `baseDir` that are added or modified in the current Git branch.
`formatLatestAuthor` | `boolean` | `false` | Whether to format only the files contained in `baseDir` that are added or modified in the latest Git commits of the same author.
`formatLocalChanges` | `boolean` | `false` | Whether to format only the unstaged files contained in `baseDir`.
`maxLineLength` | `int` | `80` | The maximum number of characters allowed in Java files.
`printErrors` | `boolean` | `true` | Whether to print formatting errors on the Standard Output stream.
`processThreadCount` | `int` | `5` | The number of threads used by Source Formatter.
`throwException` | `boolean` | `false` | Whether to fail the build if formatting errors are found.

## Additional Configuration

There are additional configurations that can help you use the Source Formatter.

### Liferay Source Formatter Dependency

By default, the plugin creates a configuration called `sourceFormatter` and adds
a dependency to the latest released version of Liferay Source Formatter. It is
possible to override this setting and use a specific version of the tool by
manually adding a dependency to the `sourceFormatter` configuration:

```gradle
dependencies {
	sourceFormatter group: "com.liferay", name: "com.liferay.source.formatter", version: "1.0.252"
}
```

### System Properties

It is possible to set the default values of the `formatCurrentBranch`,
`formatLatestAuthor`, and `formatLocalChanges` properties for a
`FormatSourceTask` task via system properties:

- `-D${task.name}.format.current.branch=true`
- `-D${task.name}.format.latest.author=true`
- `-D${task.name}.format.local.changes=true`

For example, run the following Bash command to format only the unstaged files in
the project:

```bash
./gradlew formatSource -DformatSource.format.local.changes=true
```
