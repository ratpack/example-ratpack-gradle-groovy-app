This is an example Ratpack app, based on the Vert.x version. It is a subset port of http://groovyconsole.appspot.com/.

It is also using the Ratpack Gradle plugin as the development environment.

## Getting Started

*To run this app, you need Java 7* (Vert.x requires Java 7).

Check this project out, cd into the directory and run:

    ./gradlew run

This will start the ratpack app. In your browser go to `http://localhost:5050`.

The Gradle Ratpack plugin builds on the Gradle Application plugin. This means it's easy to create a standalone
distribution for your app.

Run:

    ./gradlew installApp
    cd build/install/groovy-web-console/
    bin/groovy-web-console

Your app should now be running (see http://gradle.org/docs/current/userguide/application_plugin.html) for more on what
the Gradle application plugin can do for you.

## Development time reloading

One of the key Ratpack features is support for runtime reloading. The `src/ratpack/ratpack.groovy` file (which defines
the request routes and handlers) can be changed at runtime.

Furthermore, full reloading of supporting classes (i.e. `src/main/groovy`) is enabled via
[SpringSource's SpringLoaded](https://github.com/SpringSource/spring-loaded) library.

See `src/ratpack/ratpack.groovy` and `src/main/groovy/groovywebconsole/ReloadingThing.groovy` for instructions on how to test
the SpringLoaded based reloading.

## IDEA integration

The Ratpack Gradle plugin has special support for IntelliJ IDEA. To open the project in IDEA, run:

    ./gradlew idea

If you have the `idea` command line tool installed you can then run:

    idea groovy-web-console.ipr

In the “Run” menu, you will find a run configuration for launching the Ratpack app from within your IDE. Hot reloading
is supported in this mode. See `src/main/groovy/groovywebconsole/ReloadingThing.groovy` for details.

## More on Ratpack

There are no published docs for Ratpack at this time.

To learn more, check out the source @ https://github.com/ratpack/ratpack/tree/vertx or open this project in IDEA and
dig through the source there.