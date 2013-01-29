import groovywebconsole.ScriptExecutor
import groovywebconsole.ScriptResult
import org.ratpackframework.Request
import org.ratpackframework.Response
import org.ratpackframework.Routing

Routing r = getRouting()

r.get("/") { Request request, Response response ->
    response.render "skin.html", title: "Groovy Web Console"
}

r.post("/execute") { Request request, Response response ->
    request.form { Map<String, Object> params ->
        String script = params.script
        ScriptResult result = new ScriptExecutor().execute(script)
        response.renderJson(result)
    }
}