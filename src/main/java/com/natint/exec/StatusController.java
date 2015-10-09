package com.natint.exec;

import java.util.HashMap;

/**
 * Created by skn on 09/10/2015.
 */
public class StatusController {

    private HashMap<Integer, Status> statusMap;

    public StatusController (HashMap<Integer, Status> statusMap) {
        this.statusMap = statusMap;
    }

    public void setStatus (Integer id, Status status) {
        statusMap.put(id, status);
    }

    public Status getStatus (Integer id) {
        return statusMap.containsKey(id) ? statusMap.get(id) : Status.NOTFOUND;
    }
}
