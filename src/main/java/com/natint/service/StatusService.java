package com.natint.service;

import com.natint.data.IData;
import com.natint.exec.ResultController;
import com.natint.exec.Status;
import com.natint.exec.StatusController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by skn on 09/10/2015.
 */
@RestController
public class StatusService  {

    @Autowired
    StatusController statusController;
    @Autowired
    ResultController resultController;

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public @ResponseBody
    List<IData> getResult (@RequestParam("id") Integer id) {
        return resultController.getResult(id);
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public Status getStatus(@RequestParam("id") Integer id) {
        return statusController.getStatus(id);
    }
}
