package com.natint.task;

import com.natint.exec.ResultController;
import com.natint.exec.StatusController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

/**
 * Created by skn on 08/10/2015.
 */
@Component
public class TaskExecutor {

    @Autowired
    ExecutorService executor;
    @Autowired
    StatusController statusController;
    @Autowired
    ResultController resultController;

    public int execute(final Task task) throws ExecutionException, InterruptedException {
        executor.execute(task);
//        Future<List<IData>> result = executor.submit(task);
//        List<Future<List<IData>>> resultList = new ArrayList<>();
//        resultList.add(result);
//        for (Future<List<IData>> future : resultList){
//            System.out.println("Future result is - " + " - " + future.get() + "; And Task done is " + future.isDone());
//        }
//        executor.submit(task);
        return task.getId();
    }

    public Task initUiTask(Map<String, String> params) {
        return new UiTask(params, resultController, statusController);
    }

    public Task initApiTask(Map<String, String> params) {
        return new ApiTask(params, resultController, statusController);
    }

    public Task initEmailTask(Map<String, String> params) {
        return new EmailTask(params, resultController, statusController);
    }
}
