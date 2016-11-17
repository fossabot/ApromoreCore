package au.edu.qut.promplugins;

import au.edu.qut.processmining.log.LogParser;
import au.edu.qut.processmining.log.SimpleLog;
import au.edu.qut.processmining.miners.heuristic.net.HeuristicNet;
import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.models.graphbased.directed.bpmn.BPMNDiagram;

/**
 * Created by Adriano on 25/10/2016.
 */

@Plugin(
        name = "Mine Heuristic Net from log",
        parameterLabels = { "Event Log" },
        returnLabels = { "Heuristic Net" },
        returnTypes = { BPMNDiagram.class },
        userAccessible = true,
        help = "Returns the Heuristic net mined from the input log"
)
public class HeuristicNetPlugin {

    @UITopiaVariant(
            affiliation = "University of Tartu",
            author = "Adriano Augusto",
            email = "adriano.augusto@ut.ee"
    )
    @PluginVariant(variantLabel = "Mine Heuristic Net from log", requiredParameterLabels = {0})
    public static BPMNDiagram mineHeuristicNet(UIPluginContext context, XLog log) {
        boolean debug = true;

        SimpleLog sLog = LogParser.getSimpleLog(log);
        HeuristicNet net = new HeuristicNet(sLog);
        net.generateHeuristicNet();

        if( debug ) {
            net.printFrequencies();
            net.printConflicts();
            net.printParallelisms();
        }

        return net.getHeuristicDiagram(true);
    }
}