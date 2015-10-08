package com.natint.service;

import com.natint.data.IData;
import com.natint.exec.Status;
import com.natint.exec.TaskExecutor;
import com.natint.site.Site;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ivaa on 10/6/2015.
 */
@RestController
public class UIService implements Service {

    @Resource
    HashMap<Integer, Status> statusMap;

    @Resource
    HashMap<Integer, List<IData>> resultMap;

    @Override
    @RequestMapping(value = "/ui", method = RequestMethod.POST)
    public @ResponseBody String getTask(@RequestBody Map<String, String> params) {
        TaskExecutor taskExecutor = new TaskExecutor(statusMap, resultMap);
        Site site = taskExecutor.init(params);

        return String.valueOf(taskExecutor.execute(site));
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public @ResponseBody List<IData> getResult (@RequestParam("id") Integer id) {
        if (resultMap.containsKey(id)){
            return resultMap.get(id);
        } else {
            throw new IllegalStateException("Result for id " + id + " was not found. Its status is : " + getStatus(id));
        }
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public Status getStatus(@RequestParam("id") Integer id) {
        return statusMap.containsKey(id) ? statusMap.get(id) : Status.NOTFOUND;
    }

}
