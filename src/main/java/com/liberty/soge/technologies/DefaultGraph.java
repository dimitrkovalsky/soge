package com.liberty.soge.technologies;

import lombok.extern.slf4j.Slf4j;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Dimitr
 * Date: 05.04.2017
 * Time: 8:49
 */
@Slf4j
public class DefaultGraph implements ResearchGraph<Long, DefaultNode> {
    private DirectedGraph<ResearchNode, DefaultEdge> graph;
    private Map<Long, ResearchNode> indexed;

    public DefaultGraph() {
        graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        indexed = new HashMap<>();
    }

    public void fillGraph() {
        long id = 1;
        DefaultNode root = new DefaultNode(id, "Root node", "Test");
        id++;
        DefaultNode n1 = new DefaultNode(id, "node1", "Test node 1");
        id++;
        DefaultNode n2 = new DefaultNode(id, "node2", "Test node 2");
        id++;
        DefaultNode n3 = new DefaultNode(id, "node3", "Test node 3");
        id++;
        DefaultNode n4 = new DefaultNode(id, "node4", "Test node 4");

        graph.addVertex(root);
        graph.addVertex(n1);
        graph.addVertex(n2);
        graph.addVertex(n3);
        graph.addVertex(n4);

        graph.addEdge(root, n1);
        graph.addEdge(root, n3);
        graph.addEdge(n1, n2);
        graph.addEdge(n2, n4);
        graph.addEdge(n3, n4);

       
    }

    @Override
    public List<DefaultNode> getCompletedTechnologies() {
        return null;
    }

    @Override
    public List<DefaultNode> getAvailableForResearch() {
        return null;
    }

    @Override
    public List<DefaultNode> getAllGraph() {
        return null;
    }

    @Override
    public DefaultNode getResearch(Long id) {
        return null;
    }
}
