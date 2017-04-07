package com.liberty.soge.technologies;

import java.util.List;


public interface ResearchGraph<K, T extends ResearchNode<K>> {
    List<T> getCompletedTechnologies();

    List<T> getAvailableForResearch();

    List<T> getAllGraph();

    T getResearch(K id);
}
