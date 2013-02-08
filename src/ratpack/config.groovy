import org.ratpackframework.config.Config

// The config file determines how to configure the app on startup.
// This file is NOT reloadable. If you change something here you need to bounce the app.

(this as Config).with { // not necessary, but enables IDE intellsense

    routing.with {
        reloadable = false // automatically reload routes when the script changes
        routing.staticallyCompile = true
    }

    templating.with {
        staticallyCompile = true
        cacheSize = 10 // disable template caching, always reload
    }

}