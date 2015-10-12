package com.natint.task;

import com.natint.api.Api;
import com.natint.api.JsonPlaceholderApi;
import com.natint.exec.ResultController;
import com.natint.exec.StatusController;

import java.util.Map;

/**
 * Created by skn on 09/10/2015.
 */
public class ApiTask extends Task {

    public ApiTask(Map<String, String> params, ResultController resultController, StatusController statusController) {
        super(params, resultController, statusController);
        this.endpoint = getSite();
    }

    private Api getSite() {
        switch (getParams().get("siteName").toUpperCase()){
            case "JSONPLACEHOLDER" :
                return new JsonPlaceholderApi(getParams());
            default:
                throw new IllegalArgumentException("Please provide correct site name. For example : JsonPlaceholder");
        }
    }
}
