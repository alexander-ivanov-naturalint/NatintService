package com.natint.service;

import com.natint.task.UiTask;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by ivaa on 10/6/2015.
 */
@RestController
public class UIService extends Service {

    @Override
    @RequestMapping(value = "/ui", method = RequestMethod.POST)
    public @ResponseBody String getTask(@RequestBody Map<String, String> params) throws ExecutionException, InterruptedException {
        UiTask task = (UiTask) taskExecutorExample.initUiTask(params);
        return String.valueOf(taskExecutorExample.execute(task));
    }

}
