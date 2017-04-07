package com.liberty.soge.technologies;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Restriction<T> {
    private List<T> dependencies;

    public List<T> dependsOn() {
        return dependencies;
    }
}
