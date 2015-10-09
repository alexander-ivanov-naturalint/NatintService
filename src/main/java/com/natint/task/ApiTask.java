package com.natint.task;

import com.natint.exec.ResultController;
import com.natint.exec.StatusController;

import java.util.Map;

/**
 * Created by skn on 09/10/2015.
 */
public class ApiTask extends Task {

    public ApiTask(Map<String, String> params, ResultController resultController, StatusController statusController) {
        super(params, resultController, statusController);
    }

    @Override
    public void run() {

    }
}
