package com.natint.service;

import com.natint.data.IData;
import com.natint.site.Site;
import com.natint.site.SiteFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ivaa on 10/6/2015.
 */
@RestController
public class UIService implements Service {

    @Override
    @RequestMapping(value = "/ui", method = RequestMethod.POST)
    public
    @ResponseBody
    List<IData> getData(@RequestBody Map<String, String> params) {
        SiteFactory siteFactory = new SiteFactory();
        Site site = siteFactory.getSite(params.get("siteName"));
        return site.collectData(params.get("searchCriteria"), Integer.parseInt(params.get("resultsAmount")));
    }

}
