package com.natint.exec;

import com.natint.data.IData;
import com.natint.site.Site;
import com.natint.site.SiteFactory;
import com.natint.task.Task;
import com.natint.task.UiTask;
import org.openqa.selenium.WebDriverException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by skn on 08/10/2015.
 */
public class TaskExecutor {

    static ExecutorService executor = Executors.newFixedThreadPool(1);
    private Task task;

    HashMap<Integer, Status> statusMap;
    HashMap<Integer, List<IData>> resultMap;

    public TaskExecutor(HashMap<Integer, Status> statusMap, HashMap<Integer, List<IData>> resultMap) {
        this.statusMap = statusMap;
        this.resultMap = resultMap;
    }

    public int execute(final Site site) {

        Runnable worker = new Runnable() {
            @Override
            public void run() {
                statusMap.put(getTaskId(), Status.INPROGRESS);
               try {
                   List<IData> data = site.collectData();
                   resultMap.put(getTaskId(), data);
                   statusMap.put(getTaskId(), Status.COMPLETE);
               } catch (WebDriverException e) {
                   statusMap.put(getTaskId(),Status.ERROR);
                   throw new IllegalStateException(e);
               }
            }
        };

        executor.execute(worker);

        return getTaskId();
    }

    public Integer getTaskId() {
        return task.getId();
    }

    public Site init(Map<String, String> params) {
        task = new UiTask(params);
        statusMap.put(getTaskId(), Status.INITIALIZED);

        SiteFactory siteFactory = new SiteFactory();
        Site site = siteFactory.getSite(params.get("siteName"));
        site.withParams(params.get("searchCriteria"), Integer.parseInt(params.get("resultsAmount")));
        return site;
    }
}
