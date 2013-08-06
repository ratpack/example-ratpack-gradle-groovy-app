package groovywebconsole

import org.ratpackframework.handling.Context
import org.ratpackframework.render.ByTypeRenderer

import static groovy.json.JsonOutput.toJson
import static org.ratpackframework.groovy.Util.with

class ScriptResultRenderer extends ByTypeRenderer<ScriptResult> {

    ScriptResultRenderer() {
        super(ScriptResult)
    }

    @Override
    void render(Context context, ScriptResult result) {
        with(context.accepts) {
          type("application/json") {
              context.response.send toJson(result)
          }
        }
    }
}
