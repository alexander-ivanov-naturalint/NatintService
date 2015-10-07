package Site;

import Data.BaseData;
import Data.IData;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ivaa on 10/6/2015.
 */
public class Ebay extends Site {

    public static final String EBAY_COM = "http://www.ebay.com";

    @Override
    protected List<IData> getResults(int resultsAmount)
    {
        return null;
    }

    @Override
    protected void doSearch(String searchCriteria)
    {

    }

    @Override
    protected void open() {
        getDriver().get(EBAY_COM);
    }
}
