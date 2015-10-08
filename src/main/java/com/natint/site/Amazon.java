package com.natint.site;

import com.natint.data.BaseData;
import com.natint.data.IData;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skn on 07/10/2015.
 */
public class Amazon extends Site {

    private Logger logger = Logger.getLogger(this.getClass());

    private static final String AMAZON_LINK = "https://www.amazon.com/gp/gw/ajax/s.html";

    private static final String SEARCH_INPUT = "//input[@id='twotabsearchtextbox']";
    private static final String SEARCH_BUTTON = "//input[@value='Go']";
    private static final String PRODUCT_LINK_TEMPLATE = "//li[starts-with(@id,'result_')]//a[contains(@class,'s-access-detail-page')]";
    private static final String PRODUCT_PRICE_TEMPLATE = "//div[@class='a-row']//div[contains(@class,'a-span7')]/div[1]//a[1]/span[1]";
    private static final String NEXT_PAGE_LINK = "//a[@id='pagnNextLink']";

    @Override
    protected List<IData> getResults(int resultsAmount) {
        logger.info("Method getResults started...");
        List<IData> result = new ArrayList<>();

        while (resultsAmount > 0) {
            List<WebElement> linkElements = getDriver().findElementsByXPath(PRODUCT_LINK_TEMPLATE);
            List<WebElement> priceElements = getDriver().findElementsByXPath(PRODUCT_PRICE_TEMPLATE);
            int elementsAmount = linkElements.size();
            if (resultsAmount < elementsAmount) {
                for (int i = 0; i < resultsAmount; i++) {
                    WebElement productLinkElement = linkElements.get(i);
                    WebElement productPriceElement = priceElements.get(i);
                    result.add(new BaseData(productLinkElement.getAttribute("href"), (productPriceElement.getText())));
                }
            } else {
                for (int i = 0; i < elementsAmount; i++) {
                    WebElement productLinkElement = linkElements.get(i);
                    WebElement productPriceElement = priceElements.get(i);
                    result.add(new BaseData(productLinkElement.getAttribute("href"), productPriceElement.getText()));
                }
            }
            resultsAmount = resultsAmount - elementsAmount;
            if (!getDriver().findElementsByXPath(NEXT_PAGE_LINK).isEmpty()){
                getDriver().findElementByXPath(NEXT_PAGE_LINK).click();
            }
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
        getDriver().get(AMAZON_LINK);
        logger.info("Amazon site is opened");
    }
}
