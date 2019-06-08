package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListPageObject extends MainPageObject {

    protected static  String
            FOLDER_BY_NAME_TPL,
            FOLDER_BY_ARTICLE_TPL,
            TITTLE_ON_LIST_1,
            XPATH_IN_LIST,
            REMOVE_FROM_SAVED_BUTTON;


    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",name_of_folder);
    }


    private static String getTittleArticleXpathByName(String tittle_article){
        return FOLDER_BY_ARTICLE_TPL.replace("{TITTLE_ARTICLE}",tittle_article);
    }

    private static String getRemoveButtonByTittle(String tittle_article){
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITTLE_ARTICLE}",tittle_article);
    }



    public MyListPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void openFolderByName(String nameOfFolder) throws Exception{
        String folder_name_xpath = getFolderXpathByName(nameOfFolder);
        this.waitForElementAndClick(folder_name_xpath, "Cannot find folder by name "+nameOfFolder, 5);
    }

    public void swipeArticleToDelete(String articleTittle) throws Exception{
        if (Platform.getInstance().isAndroid()) {
        this.wairForArticleToAppearByTitle(articleTittle);}

        String tittle_article_name_xpath = getTittleArticleXpathByName(articleTittle);

        if ((Platform.getInstance().isIos() || Platform.getInstance().isAndroid())) {

         this.swipeElementToLeft(tittle_article_name_xpath,"Cannot article in list item");}

         else {
             String remove_locator = getRemoveButtonByTittle(articleTittle);
             this.waitForElementAndClick(remove_locator,"Cannot click button to remove article from saved",10);
        }
        if (Platform.getInstance().isIos()) {
            this.clickElemntToTheRightUpperCorner(tittle_article_name_xpath, "Cannot find " +tittle_article_name_xpath +" for delete");
        }

        if (Platform.getInstance().isMV()){
            driver.navigate().refresh();
        }
        this.wairForArticleToDisappearByTitle(articleTittle);
    }

    public void wairForArticleToDisappearByTitle(String articleTittle) throws Exception{
        String tittle_article_name_xpath = getTittleArticleXpathByName(articleTittle);
        this.waitForElementNotPresent(tittle_article_name_xpath, "Cannot find saved article by title " +articleTittle, 15);
    }


    public void wairForArticleToAppearByTitle(String articleTittle) throws Exception{
        String tittle_article_name_xpath = getTittleArticleXpathByName(articleTittle);
        this.waitForElementPresent(tittle_article_name_xpath, "Saved article still present with tittle " +articleTittle, 15);
    }


    public boolean getContainsTextArticleInListIos(String text) throws Exception{

        if (this.waitForElementAndGetAttribute(XPATH_IN_LIST,"name", "Cannot find "+XPATH_IN_LIST+" with attrubute name",
                15).contains(text)){
            return true;
        } else return false;
    }



}
