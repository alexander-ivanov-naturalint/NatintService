package com.natint.task;

import com.natint.exec.ResultController;
import com.natint.exec.StatusController;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by skn on 08/10/2015.
 */
@Component
public class TaskExecutorExample {

    TaskExecutor executor;
    @Autowired
    StatusController statusController;
    @Autowired
    ResultController resultController;
    int post;
    static int startIndex = 1;

    @Autowired
    public TaskExecutorExample(TaskExecutor executor) {
        this.executor = executor;
        this.post = startIndex++;
    }

    public TaskExecutorExample() {
    }

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

    public void execute(String resourcePath) throws IOException, ExecutionException, InterruptedException {
        List<Task> taskList = getTasks(resourcePath);
        for (Task task : taskList){
            executor.execute(task);
        }
    }

    public List<Task> getTasks(String resourcePath) throws IOException {
        List<Task> taskList = new ArrayList<>();
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(resourcePath)));
        HSSFSheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            Task task;
            Map<String, String> params = new HashMap<>();
            Cell firstCell = row.getCell(0);
            switch (firstCell.getStringCellValue().toLowerCase()) {
                case "ui":
                    params.put("siteName", row.getCell(1).getStringCellValue());
                    params.put("searchCriteria", row.getCell(2).getStringCellValue());
                    Double resultAmount = row.getCell(3).getNumericCellValue();
                    params.put("resultsAmount", String.valueOf(resultAmount.intValue()));
                    task = new UiTask(params, resultController, statusController);
                    break;
                default:
                    params.put("siteName", row.getCell(1).getStringCellValue());
                    Double postId = row.getCell(2).getNumericCellValue();
                    params.put("postId", String.valueOf(postId.intValue()));
                    task = new ApiTask(params, resultController, statusController);
            }
            taskList.add(task);
        }
        return taskList;
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
