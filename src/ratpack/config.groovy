import org.ratpackframework.app.Config

// The config file determines how to configure the app on startup.
// This file is NOT reloadable. If you change something here you need to bounce the app.

(this as Config).with { // not necessary, but enables IDE intellsense
    host "localhost"
    port 5050

    reloadRoutes true // automatically reload routes when the script changes

    staticallyCompileRoutes true
    staticallyCompileTemplates true

    templatesCacheSize 0 // disable template caching, always reload
}