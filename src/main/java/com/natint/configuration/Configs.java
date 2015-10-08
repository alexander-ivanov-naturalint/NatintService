package com.natint.configuration;

import com.natint.site.Amazon;
import com.natint.site.SiteFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Created by skn on 07/10/2015.
 */
@Configuration
public class Configs {

    @Autowired
    private PhantomJSDriver driver;

    @Value("${natint.amazonLink}")
    private String amazonLink;

    @Bean (destroyMethod = "quit")
    public PhantomJSDriver driver() {
        URL url = this.getClass().getClassLoader().getResource("phantomjs.exe");
        String phantomjsPath = null;
        try {
            phantomjsPath = URLDecoder.decode(url.getPath(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Capabilities caps = new DesiredCapabilities();
        ((DesiredCapabilities) caps).setJavascriptEnabled(true);
        ((DesiredCapabilities) caps).setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                phantomjsPath
        );

        driver = new PhantomJSDriver(caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.getErrorHandler().setIncludeServerErrors(false);
        driver.setLogLevel(Level.OFF);
        return driver;
    }

    @Bean
    public Amazon amazon(){
        return new Amazon(driver, amazonLink);
    }

    @Bean
    public SiteFactory siteFactory () {
        return new SiteFactory(amazon());
    }
}
