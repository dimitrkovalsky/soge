package com.liberty.soge.technologies;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class DefaultNode implements ResearchNode<Long> {
    private Long id;
    private String name;
    private String Description;

    private List<Restriction> restrictions;

    public DefaultNode(Long id, String name, String description) {
        this(id, name, description, Collections.emptyList());
    }

    public DefaultNode(Long id, String name, String description, List<Restriction> restrictions) {
        this.id = id;
        this.name = name;
        Description = description;
        this.restrictions = restrictions;
    }

    @Override
    public List<Restriction> getAdditionalRestrictions() {
        return restrictions;
    }
}
