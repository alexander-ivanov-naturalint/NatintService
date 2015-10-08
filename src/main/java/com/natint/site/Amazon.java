package com.natint.site;

import com.natint.data.BaseData;
import com.natint.data.IData;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skn on 07/10/2015.
 */
public class Amazon extends Site {

    private Logger logger = Logger.getLogger(this.getClass());

    private PhantomJSDriver driver;
    private String amazonLink;

    private static final String SEARCH_INPUT = "//input[@id='twotabsearchtextbox']";
    private static final String SEARCH_BUTTON = "//input[@value='Go']";
    private static final String PRODUCT_LINK_TEMPLATE = "//li[@id='result_%d']//a[contains(@class,'s-access-detail-page')]";
    private static final String PRODUCT_PRICE_TEMPLATE = "//li[@id='result_%d']//span[contains(@class,'s-price')]";

    public Amazon (){
        logger.info(getDriver());
        PageFactory.initElements(getDriver(), this);
    }

    public Amazon(PhantomJSDriver driver, String amazonLink) {
        this.driver = driver;
        this.amazonLink = amazonLink;
    }

    @Override
    public PhantomJSDriver getDriver() {
        return driver;
    }

    @Override
    protected List<IData> getResults(int resultsAmount) {
        logger.info("Method getResults started...");
        List<IData> result = new ArrayList<>();
        for (int i = 0; i < resultsAmount; i++){
            WebElement productLinkElement = getDriver().findElementByXPath(String.format(PRODUCT_LINK_TEMPLATE, i));
            WebElement productPriceElement = getDriver().findElementByXPath(String.format(PRODUCT_PRICE_TEMPLATE, i));
            result.add(new BaseData(productLinkElement.getAttribute("href"), Double.parseDouble(productPriceElement.getText().substring(1))));
        }
        logger.info("Search results : " + result.toString());
        return result;
    }

    @Override
    protected void doSearch(String searchCriteria) {
        logger.info("Method doSearch started...");
        getDriver().findElementByXPath(SEARCH_INPUT).sendKeys(searchCriteria);
        getDriver().findElementByXPath(SEARCH_BUTTON).click();
        logger.info("Search performed");
    }

    @Override
    protected void open() {
        logger.info("Method open() started...");
        getDriver().get(amazonLink);
        logger.info("Amazon site is opened");
    }
}
