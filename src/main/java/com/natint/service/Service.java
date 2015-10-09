package com.natint.service;


import com.natint.exec.TaskExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by ivaa on 10/6/2015.
 */
public abstract class Service
{

    @Autowired
    TaskExecutor taskExecutor;

    public abstract String getTask (Map<String, String> params);
}
