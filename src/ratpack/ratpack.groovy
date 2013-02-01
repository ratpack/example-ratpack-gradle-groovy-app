import groovywebconsole.ReloadingThing
import groovywebconsole.ScriptExecutor
import groovywebconsole.ScriptResult
import org.ratpackframework.groovy.Request
import org.ratpackframework.Response
import org.ratpackframework.Routing

// Unless reloading is disabled via config, this file is reloaded at runtime automatically.
// This does not require SpringLoaded, as the the reloading is handled internally by Ratpack.

Routing r = getRouting() // not required, but enables IDE intellisense.

r.get("/") { Request request, Response response ->
    response.render "skin.html", title: "Groovy Web Console"

    // The response object is also the delegate
    // render "skin.html", title: "Groovy Web Console"
}

r.post("/execute") { Request request, Response response ->
    // request.form is async, and catches exceptions
    request.form { Map<String, String> params ->
        String script = params.script
        ScriptResult result = new ScriptExecutor().execute(script)
        response.renderJson(result)
    }
}

r.get("/reloadexample") {
    renderText new ReloadingThing().toString()
}