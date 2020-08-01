package com.domo.refresh;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class DimRefresh
{
    private WebDriver driver;

    public DimRefresh()
    {
        System.setProperty( "webdriver.chrome.driver", "chrome/chromedriver.exe" );
        driver = new ChromeDriver();
        try
        {
            dim_agency();
            Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
            dim_adjustment();
            Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
            dim_agreement();
            Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
            dim_date();
            Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
            dim_deposit();
            Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        }
        catch( InterruptedException e )
        {
            e.printStackTrace();
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }

    public void dim_agency() throws InterruptedException, IOException
    {
        driver.manage().window().maximize();
        driver.get( "https://abcfinancial.domo.com/auth/index" );
        // step to login domo UI
        WebElement username = driver.findElement( By.name( "username" ) );
        WebElement password = driver.findElement( By.name( "password" ) );
        WebElement login = driver.findElement( By.name( "submit" ) );
        username.sendKeys( Utility.getProperties().getProperty( "user" ) );
        password.sendKeys( Utility.getProperties().getProperty( "password" ) );
        login.click();
        driver.get( Utility.URL );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        driver.get( Utility.SECONDURL );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement field = driver.findElement( By.id( Utility.SUPERSEARCH1 ) );
        field.sendKeys( Utility.getProperties().getProperty( "environment" ) + "_" + Utility.getProperties().getProperty( "dim_agency" ) );
        field.sendKeys( Keys.ENTER );
        field.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );

        // step to open staging_dim_agency_job
        WebElement w = driver.findElement(
            By.xpath( Utility.SEARCHDATANAME + Utility.getProperties().getProperty( "environment" ) + "_" + Utility.getProperties().getProperty( "dim_agency" ) + "')]" ) );
        w.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to open settings tab
        WebElement settings = driver.findElement( By.xpath( Utility.SETTINGS ) );
        settings.click();
        WebElement details = driver.findElement( By.xpath( Utility.DETAILS ) );
        details.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement q = driver.findElement( By.xpath( Utility.DETAILSINPUT1 ) );
        q.click();
        WebElement query = driver.findElement(
            By.xpath( Utility.CHANGEQUERY ) );
        query.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement txt = driver.findElement( By.xpath( Utility.QUERYTEXT ) );
        txt.clear();
        txt.sendKeys( "SELECT *\n" +
                      "FROM dim.agency  \n" +
                      "ORDER BY data_warehouse_change_date ;" );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to save setting details
        WebElement btn = driver.findElement( By.xpath( Utility.SAVEDETAILS ) );
        btn.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement scheduling = driver.findElement( By.xpath( Utility.SCHEDULING ) );
        scheduling.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );

        driver.findElement( By.xpath( Utility.BASICSCHEDULLING ) ).click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );

        WebElement change_update_setting_query = driver.findElement( By.xpath( Utility.CHANGEUPDATESETTING ) );
        change_update_setting_query.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement sch = driver.findElement( By.xpath( Utility.SELECTREPLACE ) );
        sch.click();
        WebElement update_mode = driver.findElement(
            By.xpath( Utility.CHANGEREPLACE ) );
        update_mode.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        WebElement btn_replace_save = driver.findElement( By.xpath( Utility.SAVEREPLACE ) );
        btn_replace_save.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        // step to save scheduling setting
        WebElement btn_scheduling_save = driver.findElement( By.xpath( Utility.SAVECHANGEUPDATESETTING ) );
        btn_scheduling_save.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        // step to run query on the script
        WebElement i_click = driver.findElement( By.xpath( Utility.BEFORERUNNOW ) );
        i_click.click();
        WebElement run_query = driver.findElement( By.xpath( Utility.RUNNOW ) );
        run_query.click();
        // step -1 completed and wait to 30 sec.
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "dim_run" ) ) );
        // step-2 reverse script to run
        WebElement details2 = driver.findElement( By.xpath( Utility.DETAILS ) );
        details2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement q2 = driver.findElement( By.xpath( Utility.DETAILSINPUT1 ) );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        q2.click();
        WebElement queryPara = driver.findElement( By.xpath( Utility.CHANGEQUERYPARAMETER ) );
        queryPara.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement txt2 = driver.findElement( By.xpath( Utility.QUERYTEXT ) );
        txt2.clear();
        txt2.sendKeys( "SELECT * FROM dim.agency  \n" +
                       "WHERE data_warehouse_change_date >  !{lastvalue:data_warehouse_change_date}!\n" +
                       "ORDER BY data_warehouse_change_date;" );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to save setting details
        WebElement btn2 = driver.findElement( By.xpath( Utility.SAVEDETAILS ) );
        btn2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement scheduling2 = driver.findElement( By.xpath( Utility.SCHEDULING ) );
        scheduling2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        driver.findElement( By.xpath( Utility.BASICSCHEDULLING ) ).click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement change_update_setting_query2 = driver.findElement( By.xpath( Utility.CHANGEUPDATESETTING ) );
        change_update_setting_query2.click();

        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement sch2 = driver.findElement( By.xpath( Utility.SELECTREPLACE1 ) );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        sch2.click();
        WebElement update_mode2 = driver.findElement(
            By.xpath( Utility.CHANGEMERGE ) );
        update_mode2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        WebElement search = driver.findElement( By.xpath( Utility.SAVEMERGE ) );
        search.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "dim_run" ) ) );
        WebElement select_mode = driver.findElement(
            By.xpath( "//*[@class= 'ListItem-module_contentText__b6_eH']//div[contains(text(), 'agency_key')]" ) );
        select_mode.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement btn_merge_save = driver.findElement( By.xpath( Utility.SAVEREPLACE ) );
        btn_merge_save.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to save scheduling setting
        WebElement btn_scheduling_save2 = driver.findElement( By.xpath( Utility.SAVECHANGEUPDATESETTING ) );
        btn_scheduling_save2.click();
        // step to run query on the script
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        WebElement i_click2 = driver.findElement( By.xpath( Utility.BEFORERUNNOW ) );
        i_click2.click();
        WebElement run_query2 = driver.findElement( By.xpath( Utility.RUNNOW ) );
        run_query2.click();
    }

    public void dim_adjustment() throws InterruptedException, IOException
    {
        driver.get( Utility.SECONDURL );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement field = driver.findElement( By.id( Utility.SUPERSEARCH1 ) );
        field.sendKeys( Keys.CONTROL + "a" );
        field.sendKeys( Keys.DELETE );
        field.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        field.sendKeys( Utility.getProperties().getProperty( "environment" ) + "_" + Utility.getProperties().getProperty( "dim_adjustment" ) );
        field.sendKeys( Keys.ENTER );
        field.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to open staging_dim_agency_job
        WebElement w = driver.findElement( By.xpath(
            Utility.SEARCHDATANAME + Utility.getProperties().getProperty( "environment" ) + "_" + Utility.getProperties().getProperty( "dim_adjustment" ) +
            "')]" ) );
        w.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to open settings tab
        WebElement settings = driver.findElement( By.xpath( Utility.SETTINGS ) );
        settings.click();
        WebElement details = driver.findElement( By.xpath( Utility.DETAILS ) );
        details.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement q = driver.findElement( By.xpath( Utility.DETAILSINPUT1 ) );
        q.click();
        WebElement query = driver.findElement(
            By.xpath( Utility.CHANGEQUERY ) );
        query.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement txt = driver.findElement( By.xpath( Utility.QUERYTEXT ) );
        txt.clear();
        txt.sendKeys( "SELECT *\n" +
                      "FROM dim.adjustment  \n" +
                      "ORDER BY data_warehouse_change_date ;" );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to save setting details
        WebElement btn = driver.findElement( By.xpath( Utility.SAVEDETAILS ) );
        btn.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement scheduling = driver.findElement( By.xpath( Utility.SCHEDULING ) );
        scheduling.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        driver.findElement( By.xpath( Utility.BASICSCHEDULLING ) ).click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement change_update_setting_query = driver.findElement( By.xpath( Utility.CHANGEUPDATESETTING ) );
        change_update_setting_query.click();

        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement sch = driver.findElement( By.xpath( Utility.SELECTREPLACE ) );
        sch.click();
        WebElement update_mode = driver.findElement(
            By.xpath( Utility.CHANGEREPLACE ) );
        update_mode.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement btn_replace_save = driver.findElement( By.xpath( Utility.SAVEREPLACE ) );
        btn_replace_save.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        // step to save scheduling setting
        WebElement btn_scheduling_save = driver.findElement( By.xpath( Utility.SAVECHANGEUPDATESETTING ) );
        btn_scheduling_save.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        // step to run query on the script
        WebElement i_click = driver.findElement( By.xpath( Utility.BEFORERUNNOW ) );
        i_click.click();
        WebElement run_query = driver.findElement( By.xpath( Utility.RUNNOW ) );
        run_query.click();
        // step -1 completed and wait to 30 sec.
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "dim_run" ) ) );
        // step-2 reverse script to run
        WebElement details2 = driver.findElement( By.xpath( Utility.DETAILS ) );
        details2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement q2 = driver.findElement( By.xpath( Utility.DETAILSINPUT1 ) );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        q2.click();
        WebElement queryPara = driver.findElement( By.xpath( Utility.CHANGEQUERYPARAMETER ) );
        queryPara.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement txt2 = driver.findElement( By.xpath( Utility.QUERYTEXT ) );
        txt2.clear();
        txt2.sendKeys( "SELECT * FROM dim.adjustment  \n" +
                       "WHERE data_warehouse_change_date >  !{lastvalue:data_warehouse_change_date}!\n" +
                       "ORDER BY data_warehouse_change_date;" );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to save setting details
        WebElement btn2 = driver.findElement( By.xpath( Utility.SAVEDETAILS ) );
        btn2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement scheduling2 = driver.findElement( By.xpath( Utility.SCHEDULING ) );
        scheduling2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        driver.findElement( By.xpath( Utility.BASICSCHEDULLING ) ).click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement change_update_setting_query2 = driver.findElement( By.xpath( Utility.CHANGEUPDATESETTING ) );

        change_update_setting_query2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement sch2 = driver.findElement( By.xpath( Utility.SELECTREPLACE1 ) );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        sch2.click();
        WebElement update_mode2 = driver.findElement(
            By.xpath( Utility.CHANGEMERGE ) );
        update_mode2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "dim_run" ) ) );
        WebElement search = driver.findElement( By.xpath( Utility.SAVEMERGE ) );
        search.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement select_mode = driver.findElement(
            By.xpath( "//*[@class= 'ListItem-module_contentText__b6_eH']//div[contains(text(), 'adjustment_key')]" ) );
        select_mode.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        WebElement btn_merge_save = driver.findElement( By.xpath( Utility.SAVEREPLACE ) );
        btn_merge_save.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        // step to save scheduling setting
        WebElement btn_scheduling_save2 = driver.findElement( By.xpath( Utility.SAVECHANGEUPDATESETTING ) );
        btn_scheduling_save2.click();
        // step to run query on the script
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement i_click2 = driver.findElement( By.xpath( Utility.BEFORERUNNOW ) );
        i_click2.click();
        WebElement run_query2 = driver.findElement( By.xpath( Utility.RUNNOW ) );
        run_query2.click();
    }

    public void dim_agreement() throws InterruptedException, IOException
    {
        driver.get( Utility.SECONDURL );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
//        driver.findElement( By.linkText( Utility.DATA ) ).click();
//        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
//        driver.findElement( By.linkText( Utility.DATA ) ).click();
//        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement field = driver.findElement( By.id( Utility.SUPERSEARCH1 ) );
        field.sendKeys( Keys.CONTROL + "a" );
        field.sendKeys( Keys.DELETE );
        field.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        field.sendKeys( Utility.getProperties().getProperty( "environment" ) + "_" + Utility.getProperties().getProperty( "dim_agreement" ) );
        field.sendKeys( Keys.ENTER );
        field.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to open staging_dim_agency_job
        WebElement w = driver.findElement( By.xpath(
            Utility.SEARCHDATANAME + Utility.getProperties().getProperty( "environment" ) + "_" + Utility.getProperties().getProperty( "dim_agreement" ) +
            "')]" ) );
        w.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to open settings tab
        WebElement settings = driver.findElement( By.xpath( Utility.SETTINGS ) );
        settings.click();
        WebElement details = driver.findElement( By.xpath( Utility.DETAILS ) );
        details.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement q = driver.findElement( By.xpath( Utility.DETAILSINPUT1 ) );
        q.click();
        WebElement query = driver.findElement(
            By.xpath( Utility.CHANGEQUERY ) );
        query.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement txt = driver.findElement( By.xpath( Utility.QUERYTEXT ) );
        txt.clear();
        txt.sendKeys( "SELECT *\n" +
                      "FROM domo.dim_agreement  \n" +
                      "ORDER BY data_warehouse_change_date ;" );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to save setting details
        WebElement btn = driver.findElement( By.xpath( Utility.SAVEDETAILS ) );
        btn.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement scheduling = driver.findElement( By.xpath( Utility.SCHEDULING ) );
        scheduling.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        driver.findElement( By.xpath( Utility.BASICSCHEDULLING ) ).click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement change_update_setting_query = driver.findElement( By.xpath( Utility.CHANGEUPDATESETTING ) );
        change_update_setting_query.click();

        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement sch = driver.findElement( By.xpath( Utility.SELECTREPLACE ) );
        sch.click();
        WebElement update_mode = driver.findElement(
            By.xpath( Utility.CHANGEREPLACE ) );
        update_mode.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement btn_replace_save = driver.findElement( By.xpath( Utility.SAVEREPLACE ) );
        btn_replace_save.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        // step to save scheduling setting
        WebElement btn_scheduling_save = driver.findElement( By.xpath( Utility.SAVECHANGEUPDATESETTING ) );
        btn_scheduling_save.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        // step to run query on the script
        WebElement i_click = driver.findElement( By.xpath( Utility.BEFORERUNNOW ) );
        i_click.click();
        WebElement run_query = driver.findElement( By.xpath( Utility.RUNNOW ) );
        run_query.click();
        // step -1 completed and wait to 30 sec.
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "dim_run" ) ) );
        // step-2 reverse script to run
        WebElement details2 = driver.findElement( By.xpath( Utility.DETAILS ) );
        details2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement q2 = driver.findElement( By.xpath( Utility.DETAILSINPUT1 ) );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        q2.click();
        WebElement queryPara = driver.findElement( By.xpath( Utility.CHANGEQUERYPARAMETER ) );
        queryPara.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement txt2 = driver.findElement( By.xpath( Utility.QUERYTEXT ) );
        txt2.clear();
        txt2.sendKeys( "SELECT * FROM domo.dim_agreement  \n" +
                       "WHERE data_warehouse_change_date >  !{lastvalue:data_warehouse_change_date}!\n" +
                       "ORDER BY data_warehouse_change_date;" );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to save setting details
        WebElement btn2 = driver.findElement( By.xpath( Utility.SAVEDETAILS ) );
        btn2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement scheduling2 = driver.findElement( By.xpath( Utility.SCHEDULING ) );
        scheduling2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        driver.findElement( By.xpath( Utility.BASICSCHEDULLING ) ).click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement change_update_setting_query2 = driver.findElement( By.xpath( Utility.CHANGEUPDATESETTING ) );

        change_update_setting_query2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement sch2 = driver.findElement( By.xpath( Utility.SELECTREPLACE1 ) );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        sch2.click();
        WebElement update_mode2 = driver.findElement(
            By.xpath( Utility.CHANGEMERGE ) );
        update_mode2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "dim_run" ) ) );
        WebElement search = driver.findElement( By.xpath( Utility.SAVEMERGE ) );
        search.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement select_mode = driver.findElement(
            By.xpath( "//*[@class= 'ListItem-module_contentText__b6_eH']//div[contains(text(), 'agreement_key')]" ) );
        select_mode.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        WebElement btn_merge_save = driver.findElement( By.xpath( Utility.SAVEREPLACE ) );
        btn_merge_save.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        // step to save scheduling setting
        WebElement btn_scheduling_save2 = driver.findElement( By.xpath( Utility.SAVECHANGEUPDATESETTING ) );
        btn_scheduling_save2.click();
        // step to run query on the script
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement i_click2 = driver.findElement( By.xpath( Utility.BEFORERUNNOW ) );
        i_click2.click();
        WebElement run_query2 = driver.findElement( By.xpath( Utility.RUNNOW ) );
        run_query2.click();
    }

    public void dim_date() throws InterruptedException, IOException
    {
        driver.get( Utility.SECONDURL );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
//        driver.findElement( By.linkText( Utility.DATA ) ).click();
//        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
//        driver.findElement( By.linkText( Utility.DATA ) ).click();
//        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement field = driver.findElement( By.id( Utility.SUPERSEARCH1 ) );
        field.sendKeys( Keys.CONTROL + "a" );
        field.sendKeys( Keys.DELETE );
        field.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        field.sendKeys( Utility.getProperties().getProperty( "environment" ) + "_" + Utility.getProperties().getProperty( "dim_date" ) );
        field.sendKeys( Keys.ENTER );
        field.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to open staging_dim_agency_job
        WebElement w = driver.findElement( By.xpath(
            Utility.SEARCHDATANAME + Utility.getProperties().getProperty( "environment" ) + "_" + Utility.getProperties().getProperty( "dim_date" ) +
            "')]" ) );
        w.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to open settings tab
        WebElement settings = driver.findElement( By.xpath( Utility.SETTINGS ) );
        settings.click();

        WebElement i_click2 = driver.findElement( By.xpath( Utility.BEFORERUNNOW ) );
        i_click2.click();
        WebElement run_query2 = driver.findElement( By.xpath( Utility.RUNNOW ) );
        run_query2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
    }

    public void dim_deposit() throws InterruptedException, IOException
    {
        driver.get( Utility.SECONDURL );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
//        driver.findElement( By.linkText( Utility.DATA ) ).click();
//        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
//        driver.findElement( By.linkText( Utility.DATA ) ).click();
//        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement field = driver.findElement( By.id( Utility.SUPERSEARCH1 ) );
        field.sendKeys( Keys.CONTROL + "a" );
        field.sendKeys( Keys.DELETE );
        field.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        field.sendKeys( Utility.getProperties().getProperty( "environment" ) + "_" + Utility.getProperties().getProperty( "dim_deposit" ) );
        field.sendKeys( Keys.ENTER );
        field.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to open staging_dim_agency_job
        WebElement w = driver.findElement( By.xpath(
            Utility.SEARCHDATANAME + Utility.getProperties().getProperty( "environment" ) + "_" + Utility.getProperties().getProperty( "dim_deposit" ) +
            "')]" ) );
        w.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to open settings tab
        WebElement settings = driver.findElement( By.xpath( Utility.SETTINGS ) );
        settings.click();
        WebElement details = driver.findElement( By.xpath( Utility.DETAILS ) );
        details.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement q = driver.findElement( By.xpath( Utility.DETAILSINPUT1 ) );
        q.click();
        WebElement query = driver.findElement(
            By.xpath( Utility.CHANGEQUERY ) );
        query.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement txt = driver.findElement( By.xpath( Utility.QUERYTEXT ) );
        txt.clear();
        txt.sendKeys( "SELECT *\n" +
                      "FROM dim.deposit  \n" +
                      "ORDER BY data_warehouse_change_date ;" );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to save setting details
        WebElement btn = driver.findElement( By.xpath( Utility.SAVEDETAILS ) );
        btn.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement scheduling = driver.findElement( By.xpath( Utility.SCHEDULING ) );
        scheduling.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        driver.findElement( By.xpath( Utility.BASICSCHEDULLING ) ).click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement change_update_setting_query = driver.findElement( By.xpath( Utility.CHANGEUPDATESETTING ) );

        change_update_setting_query.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement sch = driver.findElement( By.xpath( Utility.SELECTREPLACE ) );
        sch.click();
        WebElement update_mode = driver.findElement(
            By.xpath( Utility.CHANGEREPLACE ) );
        update_mode.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement btn_replace_save = driver.findElement( By.xpath( Utility.SAVEREPLACE ) );
        btn_replace_save.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        // step to save scheduling setting
        WebElement btn_scheduling_save = driver.findElement( By.xpath( Utility.SAVECHANGEUPDATESETTING ) );
        btn_scheduling_save.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        // step to run query on the script
        WebElement i_click = driver.findElement( By.xpath( Utility.BEFORERUNNOW ) );
        i_click.click();
        WebElement run_query = driver.findElement( By.xpath( Utility.RUNNOW ) );
        run_query.click();
        // step -1 completed and wait to 30 sec.
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "dim_run" ) ) );
        // step-2 reverse script to run
        WebElement details2 = driver.findElement( By.xpath( Utility.DETAILS ) );
        details2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement q2 = driver.findElement( By.xpath( Utility.DETAILSINPUT1 ) );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        q2.click();
        WebElement queryPara = driver.findElement( By.xpath( Utility.CHANGEQUERYPARAMETER ) );
        queryPara.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement txt2 = driver.findElement( By.xpath( Utility.QUERYTEXT ) );
        txt2.clear();
        txt2.sendKeys( "SELECT * FROM dim.deposit  \n" +
                       "WHERE data_warehouse_change_date >  !{lastvalue:data_warehouse_change_date}!\n" +
                       "ORDER BY data_warehouse_change_date;" );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        // step to save setting details
        WebElement btn2 = driver.findElement( By.xpath( Utility.SAVEDETAILS ) );
        btn2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement scheduling2 = driver.findElement( By.xpath( Utility.SCHEDULING ) );
        scheduling2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        driver.findElement( By.xpath( Utility.BASICSCHEDULLING ) ).click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement change_update_setting_query2 = driver.findElement( By.xpath( Utility.CHANGEUPDATESETTING ) );

        change_update_setting_query2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement sch2 = driver.findElement( By.xpath( Utility.SELECTREPLACE1 ) );
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        sch2.click();
        WebElement update_mode2 = driver.findElement(
            By.xpath( Utility.CHANGEMERGE ) );
        update_mode2.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "dim_run" ) ) );
        WebElement search = driver.findElement( By.xpath( Utility.SAVEMERGE ) );
        search.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement select_mode = driver.findElement(
            By.xpath( "//*[@class= 'ListItem-module_contentText__b6_eH']//div[contains(text(), 'deposit_key')]" ) );
        select_mode.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        WebElement btn_merge_save = driver.findElement( By.xpath( Utility.SAVEREPLACE ) );
        btn_merge_save.click();
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "other" ) ) );
        // step to save scheduling setting
        WebElement btn_scheduling_save2 = driver.findElement( By.xpath( Utility.SAVECHANGEUPDATESETTING ) );
        btn_scheduling_save2.click();
        // step to run query on the script
        Thread.sleep( Long.parseLong( Utility.getProperties().getProperty( "common" ) ) );
        WebElement i_click2 = driver.findElement( By.xpath( Utility.BEFORERUNNOW ) );
        i_click2.click();
        WebElement run_query2 = driver.findElement( By.xpath( Utility.RUNNOW ) );
        run_query2.click();
    }

}
