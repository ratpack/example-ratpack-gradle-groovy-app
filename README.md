This is an example Ratpack app, based on the latest ([Netty](http://netty.io)-powered) version. It is a subset port of the [Groovy Console](http://groovyconsole.appspot.com/).

It is also using the Ratpack Gradle plugin as the development environment.

## Getting Started

*To run this app, you need Java 7* (to be fixed)

Check this project out, cd into the directory and run:

    ./gradlew run

This will start the ratpack app. In your browser go to <http://localhost:5050>.

The Gradle Ratpack plugin builds on the Gradle Application plugin. This means it's easy to create a standalone
distribution for your app.

Run:

    ./gradlew installApp
    cd build/install/groovy-web-console/
    bin/groovy-web-console

Your app should now be running. See [The Application Plugin](http://gradle.org/docs/current/userguide/application_plugin.html) chapter in the [Gradle User Guide](http://www.gradle.org/docs/current/userguide/userguide.html) for more on what
the Gradle application plugin can do for you.

## Development time reloading

One of the key Ratpack features is support for runtime reloading. The `src/ratpack/ratpack.groovy` file (which defines
the request routes and handlers) can be changed at runtime.

Furthermore, full reloading of supporting classes (i.e. `src/main/groovy`) is enabled via
[SpringSource's SpringLoaded](https://github.com/SpringSource/spring-loaded) library.

See `src/ratpack/ratpack.groovy` and `src/main/groovy/groovywebconsole/ReloadingThing.groovy` for instructions on how to test
the SpringLoaded based reloading.

## IDEA integration

The Ratpack Gradle plugin has special support for [IntelliJ IDEA](http://www.jetbrains.com/idea/download/). To open the project in IDEA, run:

    ./gradlew idea

If you have the `idea` command line tool installed you can then run:

    idea groovy-web-console.ipr

In the “Run” menu, you will find a run configuration for launching the Ratpack app from within your IDE. Hot reloading
is supported in this mode. See `src/main/groovy/groovywebconsole/ReloadingThing.groovy` for details.

## Configuring a port

### When running via Gradle

Add

    jvmArgs "-Dratpack.port=8080"

To your ```run``` closure, for example:

    run {
        jvmArgs "-Dratpack.port=8080"
        systemProperty "ratpack.reloadable", "true"
    }

### When running the built application/jar

Set the JVM property using the ```JAVA_OPTS``` shell variable.  For example, in the Bash shell:

    export JAVA_OPTS=-Dratpack.port=8080

## More on Ratpack

The published [Ratpack Manual](http://www.ratpack-framework.org/manual/snapshot/) is currently minimal, but contributions are welcome.

More information, including issue tracker and support forum, is available on the [Ratpack Website](http://www.ratpack-framework.org).

You can also check out the source @ https://github.com/ratpack/ratpack/tree/master or open this project in IDEA and
dig through the source there.
