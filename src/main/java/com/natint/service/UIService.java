package com.natint.service;

import com.natint.exec.TaskExecutor;
import com.natint.task.UiTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by ivaa on 10/6/2015.
 */
@RestController
public class UIService implements Service {

    @Autowired
    TaskExecutor taskExecutor;

    @Override
    @RequestMapping(value = "/ui", method = RequestMethod.POST)
    public @ResponseBody String getTask(@RequestBody Map<String, String> params) {
        UiTask task = (UiTask) taskExecutor.init(params);
        return String.valueOf(taskExecutor.execute(task));
    }

}
