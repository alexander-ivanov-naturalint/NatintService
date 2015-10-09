package com.natint.task;

import com.natint.data.IData;
import com.natint.exec.ResultController;
import com.natint.exec.Status;
import com.natint.exec.StatusController;
import com.natint.site.Site;
import com.natint.site.SiteFactory;
import org.openqa.selenium.WebDriverException;

import java.util.List;
import java.util.Map;

/**
 * Created by skn on 08/10/2015.
 */
public class UiTask extends Task {

    private Site site;

    public UiTask(Map<String, String> params, ResultController resultController, StatusController statusController) {
        super(params, resultController, statusController);
        this.site = new SiteFactory().getSite(params.get("siteName"));
        site.withParams(params.get("searchCriteria"), Integer.parseInt(params.get("resultsAmount")));
    }

    public Site getSite() {
        return site;
    }

    @Override
    public void run() {
        statusController.setStatus(getId(), Status.INPROGRESS);
        try {
            List<IData> result = getSite().collectData();
            resultController.setResult(getId(), result);
            statusController.setStatus(getId(), Status.COMPLETE);
        } catch (WebDriverException e) {
            statusController.setStatus(getId(), Status.ERROR);
            throw new IllegalStateException(e);
        }
    }
}
