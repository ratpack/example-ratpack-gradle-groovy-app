import groovywebconsole.ReloadingThing
import groovywebconsole.ScriptExecutor
import org.ratpackframework.groovy.templating.TemplateRenderer
import org.ratpackframework.groovy.templating.TemplatingModule

import static groovy.json.JsonOutput.toJson
import static org.ratpackframework.groovy.RatpackScript.ratpack

ratpack {
    handlers {
        get {
            get(TemplateRenderer).render "skin.html", title: "Groovy Web Console"
        }

        post("execute") {
            def script = request.form.script
            def result = new ScriptExecutor().execute(script)
            response.send "application/json", toJson(result)
        }

        get("reloadexample") {
            response.send new ReloadingThing().toString()
        }

        prefix(":a/:b") {
            handler {
                response.addHeader("foo", "bar")
                next()
            }
            get(":c/:d?") {
                response.send(pathTokens.toString())
            }
        }

        assets "public"
    }
}


