package com.natint.site;

import com.natint.site.Ebay.Ebay;

/**
 * Created by skn on 07/10/2015.
 */
public class SiteFactory {

    private static Amazon amazon;

    public SiteFactory (Amazon amazon) {
        this.amazon = amazon;
    }

    public Site getSite (String siteName) {
        switch (siteName) {
            case "EBAY" :
                return new Ebay();
            case "AMAZON" :
                return amazon;
            default :
                throw new IllegalArgumentException("Possible values for property 'siteName' are : EBAY, AMAZON");
        }
    }
}
