package com.natint.service;

import com.natint.task.ApiTask;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by skn on 09/10/2015.
 */
@RestController
public class ApiService extends Service{

    @Override
    @RequestMapping(value = "/api", method = RequestMethod.POST)
    public @ResponseBody String getTask(@RequestBody Map<String, String> params) throws ExecutionException, InterruptedException {
        ApiTask task = (ApiTask) taskExecutorExample.initApiTask(params);
        return String.valueOf(taskExecutorExample.execute(task));
    }
}
