package com.natint.service;

import com.natint.data.IData;
import com.natint.site.Site;
import com.natint.site.SiteFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ivaa on 10/6/2015.
 */
@RestController
public class UIService implements Service {

    @Autowired
    SiteFactory siteFactory;

    @Override
    @RequestMapping(value = "/ui", method = RequestMethod.POST)
    public
    @ResponseBody
    List<IData> getData(@RequestBody Map<String, String> params) {
        Site site = siteFactory.getSite(params.get("siteName"));
        return site.collectData(params.get("searchCriteria"), Integer.parseInt(params.get("resultsAmount")));
    }

    @RequestMapping(value = "/ui", method = RequestMethod.OPTIONS)
    public String info() {
        return "POST : {description : Get information from UI, body : {siteName : {type : String, description : EBAY or AMAZON}, searchCriteria : {type : String, description : Item to search for}, resultsAmount : {type : number, description : amount of search results}}}";
    }

}
