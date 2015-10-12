package com.natint.task;

import com.natint.data.IData;
import com.natint.exec.ResultController;
import com.natint.exec.Status;
import com.natint.exec.StatusController;
import com.natint.site.Endpoint;

import java.util.List;
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
    protected Endpoint endpoint;

    public Task(final Map<String, String> params, ResultController resultController, StatusController statusController) {
        this.id = nextId++;
        this.params = params;
        this.resultController = resultController;
        this.statusController = statusController;
        statusController.setStatus(getId(), Status.INITIALIZED);
    }

    public int getId() {
        return id;
    }

    public Map<String, String> getParams() {
        return params;
    }

//    @Override
//    public List<IData> call() {
//        System.out.println("======================== In call method ========================");
//        return endpoint.collectData();
//    }

    @Override
    public void run() {
        statusController.setStatus(getId(), Status.INPROGRESS);
        try {
            List<IData> result = endpoint.collectData();
            resultController.setResult(getId(), result);
            statusController.setStatus(getId(), Status.COMPLETE);
        } catch (Exception e) {
            statusController.setStatus(getId(), Status.ERROR);
            throw new IllegalStateException(e);
        }
    }
}
