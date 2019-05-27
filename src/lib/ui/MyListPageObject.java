package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {

    private static final String
            FOLDER_BY_NAME_TPL = "xpath://android.widget.TextView[@text='{FOLDER_NAME}']",
            FOLDER_BY_ARTICLE_TPL = "xpath://*[@text='{TITTLE_ARTICLE}']";


    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",name_of_folder);
    }


    private static String getTittleArticleXpathByName(String tittle_article){
        return FOLDER_BY_ARTICLE_TPL.replace("{TITTLE_ARTICLE}",tittle_article);
    }



    public MyListPageObject(AppiumDriver driver){
        super(driver);
    }

    public void openFolderByName(String nameOfFolder) throws Exception{
        String folder_name_xpath = getFolderXpathByName(nameOfFolder);
        this.waitForElementAndClick(folder_name_xpath, "Cannot find folder by name "+nameOfFolder, 5);
    }

    public void swipeArticleToDelete(String articleTittle) throws Exception{
        this.wairForArticleToAppearByTitle(articleTittle);
        String tittle_article_name_xpath = getTittleArticleXpathByName(articleTittle);
        this.swipeElementToLeft(tittle_article_name_xpath, "Cannot article in list item");
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

}
