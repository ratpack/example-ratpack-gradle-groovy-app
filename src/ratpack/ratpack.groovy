import groovy.json.JsonOutput
import groovywebconsole.ReloadingThing
import groovywebconsole.ScriptExecutor
import groovywebconsole.ScriptResult
import org.ratpackframework.Request
import org.ratpackframework.Response
import org.ratpackframework.groovy.routing.ClosureRouting

// Unless reloading is disabled via config, this file is reloaded at runtime automatically.
// This does not require SpringLoaded, as the the reloading is handled internally by Ratpack.

def r = this as ClosureRouting // not required, but enables IDE intellisense.

r.get("/") { Request request, Response response ->
    response.render "skin.html", title: "Groovy Web Console"

    // The response object is also the delegate
    // render "skin.html", title: "Groovy Web Console"
}

r.post("/execute") { Request request, Response response ->
    String script = request.form.get("script")[0]
    ScriptResult result = new ScriptExecutor().execute(script)
    response.text "application/json", JsonOutput.toJson(result)
}

r.get("/reloadexample") {
    text new ReloadingThing().toString()
}