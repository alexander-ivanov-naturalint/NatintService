package com.natint.configuration;

import com.natint.data.IData;
import com.natint.exec.Status;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;

/**
 * Created by skn on 08/10/2015.
 */
@Configuration
public class Configs {

    private Logger logger = Logger.getLogger(this.getClass());

    @Bean
    public HashMap<Integer, Status> statusMap () {
        return new HashMap<Integer, Status>();
    }

    @Bean
    public HashMap<Integer, List<IData>> resultMap () {
        return new HashMap<Integer, List<IData>>();
    }

}
