package com.natint.service;

import com.natint.task.EmailTask;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by skn on 12/10/2015.
 */
@RestController
public class EmailService extends Service {
    @Override
    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public @ResponseBody String getTask(@RequestBody Map<String, String> params) throws ExecutionException, InterruptedException {
        EmailTask task = (EmailTask) taskExecutorExample.initEmailTask(params);
        return String.valueOf(taskExecutorExample.execute(task));
    }
}
