package com.domo.refresh;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Utility
{

    public static final String URL = "https://abcfinancial.domo.com/page/-100000";

    public static final String SECONDURL = "https://abcfinancial.domo.com/datacenter/datasources";

    public static final String SUPERSEARCH1 = "superSearch1";

    public static final String SEARCHDATANAME = "//a[@class='inherit-styles single-line-ellipsis disable-safari-tool-tip ng-binding'][contains(text(), '";

    public static final String SEARCHDATANAMEINVOICE =
        "//div[@class='db-text-display-6 datasource-name']//a[@db-tooltip='" + Utility.getProperties().getProperty( "environment" ) + "_" + "dim_invoice']";

    public static final String SEARCHDATANAMEINVOICENUMBER =
        "//div[@class='db-text-display-6 datasource-name']//a[@db-tooltip='" + Utility.getProperties().getProperty( "environment" ) + "_" + "dim_invoice_number']";

    public static final String SETTINGS = "//db-tab[@name = 'settings']";

    public static final String DETAILS = "//span[contains(text(),'Details')]";

    public static final String DETAILSINPUT1 = "//*[contains(text(),'Details')]//following::input[1]";

    public static final String CHANGEQUERY =
        "//*[@class= 'ListItem-module_contentText__b6_eH']//div[substring(text(), string-length(text()) - string-length('Parameter') +1) = 'Query']";

    public static final String QUERYTEXT = "//div[@class='cw-section-text cw-section-min-width margin-bottom-small']//textarea";

    public static final String SAVEDETAILS = "//*[contains(text(),'Scheduling')]//preceding::button[2]";

    public static final String SCHEDULING = "//span[contains(text(),'Scheduling')]";

    public static final String BASICSCHEDULLING = "//div[@class='tab-bar lite small margin-bottom-small']//div[span]//*[contains(text(),'Basic Scheduling')]";

    public static final String CHANGEUPDATESETTING = "//div[@class='cw-section-updatemode cw-section-min-width margin-bottom-small']//a";
    //span[@class='block']//*[contains(text(),'Your data will be updated using')]//following::a[span]//*[contains(text(),'Change update settings')]";

    public static final String SELECTREPLACE = "//*[contains(text(),'Data Preview')]//preceding::input[2]";
    //*[contains(text(),'Scheduling')]//following::input[51]";

    public static final String SELECTREPLACE1 = "//*[contains(text(),'Data Preview')]//preceding::input[1]";

    public static final String CHANGEREPLACE = "//*[@class= 'ListItem-module_contentText__b6_eH']//div[contains(text(), 'Replace')]";

    public static final String SAVEREPLACE = "//*[contains(text(),'Data Preview')]//preceding::button[4]";

    public static final String SAVECHANGEUPDATESETTING = "//*[contains(text(),'Data Preview')]//preceding::button[2]";

    public static final String BEFORERUNNOW = "//*[@role='presentation' and @class='db-icon icon-dots-vertical md']";

    public static final String RUNNOW = "//div[@class='ListItem-module_contentText__b6_eH']//div[contains(text(),'Run Now')]";

    public static final String CHANGEQUERYPARAMETER = "//*[@class= 'ListItem-module_contentText__b6_eH']//div[contains(text(), 'Query Parameter')]";

    public static final String CHANGEMERGE = "//*[@class= 'ListItem-module_contentText__b6_eH']//div[contains(text(), 'Merge')]";

    public static final String SAVEMERGE = "//*[contains(text(),'Data Preview')]//preceding::input[1]";

    public static Properties getProperties()
    {
        FileReader reader = null;
        Properties p = null;
        try
        {
            reader = new FileReader( "resource/enviornment_property.properties" );
            p = new Properties();
            p.load( reader );
        }
        catch( FileNotFoundException e )
        {
            e.printStackTrace();
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }

        return p;
    }
}
