package com.natint.exec;

import com.natint.task.Task;
import com.natint.task.UiTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by skn on 08/10/2015.
 */
@Component
public class TaskExecutor {

    static ExecutorService executor = Executors.newFixedThreadPool(2);

    @Autowired
    StatusController statusController;
    @Autowired
    ResultController resultController;

    public int execute(final Task task) {
        executor.execute(task);
        return task.getId();
    }

    public Task init(Map<String, String> params) {
        return new UiTask(params, resultController, statusController);
    }
}
