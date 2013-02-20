import groovy.json.JsonOutput
import groovywebconsole.ReloadingThing
import groovywebconsole.ScriptExecutor
import groovywebconsole.ScriptResult
import org.ratpackframework.app.Request
import org.ratpackframework.app.Response
import org.ratpackframework.groovy.app.Routing

// Unless reloading is disabled via config, this file is reloaded at runtime automatically.
// This does not require SpringLoaded, as the the reloading is handled internally by Ratpack.

(this as Routing).with { // not required, but enables IDE intellisense.
    get("/") { Request request, Response response ->
        response.render "skin.html", title: "Groovy Web Console"

        // The response object is also the delegate
        // render "skin.html", title: "Groovy Web Console"
    }

    post("/execute") { Request request, Response response ->
        String script = request.form.get("script")[0]
        ScriptResult result = new ScriptExecutor().execute(script)
        response.text "application/json", JsonOutput.toJson(result)
    }

    get("/reloadexample") {
        text new ReloadingThing().toString()
    }
}

