package com.natint.task;

import com.natint.email.Email;
import com.natint.email.Gmail4j;
import com.natint.email.JavaxMail;
import com.natint.exec.ResultController;
import com.natint.exec.StatusController;

import java.util.Map;

/**
 * Created by skn on 12/10/2015.
 */
public class EmailTask extends Task{
    public EmailTask(Map<String, String> params, ResultController resultController, StatusController statusController) {
        super(params, resultController, statusController);
        this.endpoint = getInstance(params.get("name"));
    }

    private Email getInstance(String name){
        switch (name.toUpperCase()){
            case "GMAIL4J" :
                return new Gmail4j(getParams());
            case "JAVAX" :
                return new JavaxMail();
            default:
                throw new IllegalArgumentException("No matches found for email endpoint " + name);
        }
    }
}
