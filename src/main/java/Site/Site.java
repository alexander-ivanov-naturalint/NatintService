package Site;

import Data.IData;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.List;

/**
 * Created by ivaa on 10/6/2015.
 */
public abstract class Site {
    public static Site getInstance(String siteName) {
        return new Ebay();
    }

    protected PhantomJSDriver getDriver() {
        return driver;
    }

    private PhantomJSDriver driver;

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
        Capabilities caps = new DesiredCapabilities();
        ((DesiredCapabilities) caps).setJavascriptEnabled(true);
        //((DesiredCapabilities) caps).setCapability("takesScreenshot", true);
        ((DesiredCapabilities) caps).setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "your custom path\\phantomjs.exe"
        );

        //File file = new File("C:/Program Files/phantomjs-1.9.7-windows/phantomjs.exe");
        //System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
        driver = new PhantomJSDriver(caps);
    }


}
