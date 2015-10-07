package com.natint.site;

import com.natint.data.IData;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by ivaa on 10/6/2015.
 */
public abstract class Site {

    private PhantomJSDriver driver;

    public static Site getInstance(String siteName) {
        return new Ebay();
    }

    protected PhantomJSDriver getDriver() {
        return driver;
    }

    public List<IData> collectData(String searchCriteria, int resultsAmount)
    {
        prepare();
        open();
        doSearch(searchCriteria);
        return getResults(resultsAmount);
    }

    protected abstract List<IData> getResults(int resultsAmount);

    protected abstract void doSearch(String searchCriteria);

    protected abstract void open();

    private void prepare()
    {
        URL url = Site.class.getClassLoader().getResource("phantomjs.exe");
        String phantomjsPath = null;
        try {
            phantomjsPath = URLDecoder.decode(url.getPath(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Capabilities caps = new DesiredCapabilities();
        ((DesiredCapabilities) caps).setJavascriptEnabled(true);
        //((DesiredCapabilities) caps).setCapability("takesScreenshot", true);
        ((DesiredCapabilities) caps).setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                phantomjsPath
        );
        
        driver = new PhantomJSDriver(caps);
    }


}
