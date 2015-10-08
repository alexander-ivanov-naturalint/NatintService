package com.natint.service;


import com.natint.data.IData;
import com.natint.exec.Status;

import java.util.List;
import java.util.Map;

/**
 * Created by ivaa on 10/6/2015.
 */
public interface Service
{
    public String getTask (Map<String, String> params);

    public List<IData> getResult(Integer id);

    public Status getStatus(Integer id);
}
