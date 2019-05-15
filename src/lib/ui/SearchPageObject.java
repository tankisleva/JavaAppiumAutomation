package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']";


    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

     /*template methods*/
    private static String getResultSearhElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    /*template methods*/

    public void initSearchInput(){
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),"Cannot find and click search init element",5);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT),"Cannot find search input after clicking search input element",5);
    }


    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT),search_line,"Cannot find and type into search input", 5);
    }

    public void waitForSearhResult(String substring){
        String SEARCH_RESULT_XPATH = getResultSearhElement(substring);
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_XPATH),"Cannot find search result with substring " + substring);
    }


    public void clickByArticleWithSubstring(String substring){
        String SEARCH_RESULT_XPATH = getResultSearhElement(substring);
        this.waitForElementAndClick(By.xpath(SEARCH_RESULT_XPATH),"Cannot find and search result with substring " + substring,10);
    }



    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON),"Cannot find search cancel button",5);
    }

    public void waitForCancelButtonToDisAppear(){
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON),"Cancel search button is still present",5);
    }

    public void clickCancelSearh(){
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON),"Cannot find and click cancel searh button",5);
    }


    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(By.xpath(SEARCH_RESULT),
                "Cannot find anything by the request",
                15);
        return  this.getAmountOfElements(By.xpath(SEARCH_RESULT));

    }


    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot empty result label by request", 15);
    }


    public void asserThereIsNotResultOfSearch(){
        this.assertElementsNotPresent(By.xpath(SEARCH_RESULT), "No supporsed not to find any result");
    }



}
