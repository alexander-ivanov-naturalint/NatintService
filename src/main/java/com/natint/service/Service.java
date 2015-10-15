package com.natint.service;


import com.natint.task.TaskExecutorExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by ivaa on 10/6/2015.
 */
public abstract class Service
{

    @Autowired
    TaskExecutorExample taskExecutorExample;

    public abstract String getTask (Map<String, String> params) throws ExecutionException, InterruptedException;
}
