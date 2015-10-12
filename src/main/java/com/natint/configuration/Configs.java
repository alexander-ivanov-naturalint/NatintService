package com.natint.configuration;

import com.natint.data.IData;
import com.natint.exec.ResultController;
import com.natint.exec.Status;
import com.natint.exec.StatusController;
import com.natint.task.TaskExecutor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by skn on 08/10/2015.
 */
@Configuration
public class Configs {

    @Value("${poolSize}")
    private String poolSize;

    private Logger logger = Logger.getLogger(this.getClass());

    @Bean
    public TaskExecutor taskExecutor () {
        return new TaskExecutor();
    }

    @Bean
    public ResultController resultController () {
        return new ResultController(new HashMap<Integer, List<IData>>());
    }

    @Bean
    public StatusController statusController () {
        return new StatusController(new HashMap<Integer, Status>());
    }

    @Bean
    public ExecutorService executor (){
        return Executors.newFixedThreadPool(Integer.parseInt(poolSize));
    }

}
