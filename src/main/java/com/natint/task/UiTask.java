package com.natint.task;

import com.natint.exec.ResultController;
import com.natint.exec.StatusController;
import com.natint.site.Site;
import com.natint.site.SiteFactory;

import java.util.Map;

/**
 * Created by skn on 08/10/2015.
 */
public class UiTask extends Task {

    public UiTask(Map<String, String> params, ResultController resultController, StatusController statusController) {
        super(params, resultController, statusController);
        this.endpoint = new SiteFactory().getSite(params.get("siteName"));
        ((Site) endpoint).withParams(params.get("searchCriteria"), Integer.parseInt(params.get("resultsAmount")));
    }
}
