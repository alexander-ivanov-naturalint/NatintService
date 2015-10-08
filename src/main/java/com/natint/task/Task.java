package com.natint.task;

import java.util.Map;

/**
 * Created by skn on 08/10/2015.
 */
public abstract class Task {

    private static int nextId = 1;

    private final int id;
    private final Map<String, String> params;

    public Task(final Map<String, String> params){
        this.id=nextId++;
        this.params=params;
    }

    public int getId() {
        return id;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
