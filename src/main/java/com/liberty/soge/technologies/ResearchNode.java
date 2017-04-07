package com.liberty.soge.technologies;


import java.util.List;

public interface ResearchNode<T> {
    T getId();
    List<Restriction> getAdditionalRestrictions();
}
