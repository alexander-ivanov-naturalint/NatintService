package com.natint.task;

import com.natint.exec.ResultController;
import com.natint.exec.StatusController;

import java.util.Map;

/**
 * Created by skn on 08/10/2015.
 */
public abstract class Task implements Runnable {

    protected ResultController resultController;
    protected StatusController statusController;

    private static int nextId = 1;

    private final int id;
    private final Map<String, String> params;

    public Task(final Map<String, String> params, ResultController resultController, StatusController statusController) {
        this.id = nextId++;
        this.params = params;
        this.resultController = resultController;
        this.statusController = statusController;
    }

    public int getId() {
        return id;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
