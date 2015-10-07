package com.natint.site;

import com.natint.data.IData;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.List;

/**
 * Created by ivaa on 10/6/2015.
 */
public abstract class Site {

    private PhantomJSDriver driver;

    public Site (PhantomJSDriver driver) {
        this.driver = driver;
    }

    protected PhantomJSDriver getDriver() {
        return driver;
    }

    public Site () {
    }

    public List<IData> collectData(String searchCriteria, int resultsAmount)
    {
        open();
        doSearch(searchCriteria);
        return getResults(resultsAmount);
    }

    protected abstract List<IData> getResults(int resultsAmount);

    protected abstract void doSearch(String searchCriteria);

    protected abstract void open();

}
