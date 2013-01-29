package groovywebconsole

/**
 * Used to illustrate runtime reloading of supporting classes.
 *
 * If running the app via IDEA, you must “make” the project after changing this file to see the change.
 * IDEA does not automatically compile changes when there is an active “run” session.
 *
 * If running the app via Gradle (i.e. “./gradlew run”) you can open another terminal and recompile via “./gradlew classes”.
 * You do not need to restart the app.
 *
 * Credits to the Spring Source tools team for creating the awesome “SpringLoaded” library that enables this.
 */
class ReloadingThing {

    @Override
    String toString() {
        "a" // change me
    }

}
