package org.apromore.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apromore.common.Constants;
import org.apromore.graph.JBPT.CPF;
import org.apromore.graph.JBPT.CpfEvent;
import org.apromore.graph.JBPT.CpfOrGateway;
import org.jbpt.graph.algo.rpst.RPST;
import org.jbpt.pm.Activity;
import org.jbpt.pm.ControlFlow;
import org.jbpt.pm.FlowNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chathura Ekanayake
 */
public class GraphUtil {

    private static final Logger log = LoggerFactory.getLogger(GraphUtil.class);


    /**
     * Copies the graph g to ng by duplicating all vertices and edges.
     *
     * @param g  Source graph
     * @param ng Target graph
     * @return mapping old node Id -> new node Id
     */
    public static Map<String, String> copyContentGraph(final CPF g, final CPF ng) {
        Collection<FlowNode> vertices = g.getVertices();
        Collection<ControlFlow<FlowNode>> edges = g.getEdges();
        Map<String, String> vMap = new HashMap<String, String>(0);
        Map<String, String> pocketMap = new HashMap<String, String>(0);

        // copy vertices to the new graph
        for (FlowNode v : vertices) {
            String type = g.getVertexProperty(v.getId(), Constants.TYPE);

            //TODO FM 22.09.2012, workaround to get correct CpfNode types
            FlowNode newV = cloneNode(v);
            if (newV == null) {
                newV = new Activity(v.getName());
            }

            ng.addVertex(newV);
            ng.setVertexProperty(newV.getId(), Constants.TYPE, type);
            vMap.put(v.getId(), newV.getId());

            if (Constants.POCKET.equals(type)) {
                pocketMap.put(v.getId(), newV.getId());
            }
        }

        // add edges connecting new vertices
        for (ControlFlow<FlowNode> e : edges) {
            FlowNode newSource = ng.getVertex(vMap.get(e.getSource().getId()));
            FlowNode newTarget = ng.getVertex(vMap.get(e.getTarget().getId()));
            ng.addEdge(newSource, newTarget);
        }

        return vMap;
    }

    /**
     * Dirty hack to keep the class of this Node
     *
     * @param v node to clone
     * @return
     */
    private static FlowNode cloneNode(final FlowNode v) {
        try {
            FlowNode newNode = v.getClass().newInstance();
            newNode.setName(v.getName());
            return newNode;
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void fillGraph(final CPF g, final CPF sg) {
        fillVertices(g, sg);
        fillEdges(g, sg);
    }

    public static void fillVertices(final CPF g, final CPF sg) {
        Collection<FlowNode> vs = sg.getVertices();
        for (FlowNode v : vs) {
            String type = sg.getVertexProperty(v.getId(), Constants.TYPE);
            g.addVertex(v);
            g.setVertexProperty(v.getId(), Constants.TYPE, type);
        }
    }

    public static void fillEdges(final CPF g, final CPF sg) {
        Collection<ControlFlow<FlowNode>> edges = sg.getEdges();
        for (ControlFlow<FlowNode> edge : edges) {
            g.addEdge(edge.getSource(), edge.getTarget());
        }
    }

    public static RPST<ControlFlow<FlowNode>, FlowNode> normalizeGraph(final CPF graph) {
        log.debug("Normalizing graph with size " + graph.getVertices().size());

        List<FlowNode> srcs = graph.getSourceVertices();
        List<FlowNode> tgts = graph.getSinkVertices();

        // remove isolated vertices
        List<FlowNode> isolatedVertices = new ArrayList<FlowNode>(0);
        for (FlowNode isrc : srcs) {
            if (tgts.contains(isrc)) {
                isolatedVertices.add(isrc);
            }
        }
        srcs.removeAll(isolatedVertices);
        tgts.removeAll(isolatedVertices);
        graph.removeVertices(isolatedVertices);

        FlowNode entry = null;
        FlowNode exit = null;

        for (FlowNode src : srcs) {
            String srcLabel = src.getName();
            if ("_entry_".equals(srcLabel)) {
                entry = src;
            }
        }

        for (FlowNode tgt : tgts) {
            String tgtLabel = tgt.getName();
            if ("_exit_".equals(tgtLabel)) {
                exit = tgt;
            }
        }

        if (entry == null) {
            srcs.retainAll(tgts);
            // remove nodes that have no input and output edges
            for (FlowNode v : srcs) {
                graph.removeVertex(v);
            }

            srcs = graph.getSourceVertices();
            tgts = graph.getSinkVertices();

            entry = new CpfEvent("_entry_");
            graph.addVertex(entry);

            exit = new CpfEvent("_exit_");
            graph.addVertex(exit);

            if (srcs.size() == 1) {
                for (FlowNode tgt : srcs) {
                    graph.addEdge(entry, tgt);
                }
            } else {
                FlowNode sourceAggregator = new CpfOrGateway("OR");
                graph.addFlowNode(sourceAggregator);
                graph.setVertexProperty(sourceAggregator.getId(), Constants.TYPE, Constants.CONNECTOR);
                graph.addEdge(entry, sourceAggregator);
                for (FlowNode tgt : srcs) {
                    graph.addEdge(sourceAggregator, tgt);
                }
            }

            if (tgts.size() == 1) {
                for (FlowNode src : tgts) {
                    graph.addEdge(src, exit);
                }
            } else {
                FlowNode sinkAggregator = new CpfOrGateway("OR");
                graph.addFlowNode(sinkAggregator);
                graph.setVertexProperty(sinkAggregator.getId(), Constants.TYPE, Constants.CONNECTOR);
                graph.addEdge(sinkAggregator, exit);
                for (FlowNode src : tgts) {
                    graph.addEdge(src, sinkAggregator);
                }
            }
        }

        return new RPST<ControlFlow<FlowNode>, FlowNode>(graph);
    }
}