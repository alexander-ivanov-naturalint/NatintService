package Site.Ebay;

import Data.BaseData;
import Data.IData;
import Site.Site;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ivaa on 10/6/2015.
 */
public class Ebay extends Site {

    public static final String EBAY_COM = "http://www.ebay.com";

    @FindBy(css = "table[class='gh-tbl']")
    private SearchBlock searchBlock;

    @Override
    protected List<IData> getResults(int resultsAmount)
    {
        SearchResults searchResults = new SearchResults(getDriver());
        List<SearchResultElement> results = searchResults.getSearchItems();
        if (resultsAmount < results.size())
            results = results.subList(0, resultsAmount);

        LinkedList<IData> list = new LinkedList<>();
        for (SearchResultElement element : results)
            list.add(new BaseData(element.getLink().getReference(), element.getPrice().getText()));

        return list;
    }

    @Override
    protected void doSearch(String searchCriteria)
    {
        searchBlock.performSearch(searchCriteria);
    }

    @Override
    protected void open()
    {
        PageFactory.initElements(new HtmlElementDecorator(getDriver()), this);
        getDriver().get(EBAY_COM);
    }
}
