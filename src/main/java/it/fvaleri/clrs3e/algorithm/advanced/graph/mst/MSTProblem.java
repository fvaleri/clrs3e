package it.fvaleri.clrs3e.algorithm.advanced.graph.mst;

import it.fvaleri.clrs3e.data.advanced.Graph;
import it.fvaleri.clrs3e.data.advanced.Graph.Edge;

import java.util.List;

/**
 * Minimum Spanning Tree.
 * Assuming a weighted undirected graph as input.
 *
 * @author fvaleri
 */
public interface MSTProblem {
    public List<Edge> execute(Graph g);

}
