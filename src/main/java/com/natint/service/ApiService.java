package com.natint.service;

import com.natint.task.ApiTask;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by skn on 09/10/2015.
 */
@RestController
public class ApiService extends Service{

    @Override
    @RequestMapping(value = "/api", method = RequestMethod.POST)
    public String getTask(Map<String, String> params) {
        ApiTask task = (ApiTask) taskExecutor.initApiTask(params);
        return String.valueOf(taskExecutor.execute(task));
    }
}
