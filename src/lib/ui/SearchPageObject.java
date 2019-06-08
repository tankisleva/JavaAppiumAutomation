package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public  class SearchPageObject extends MainPageObject {

     protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            BUTTON_CLOSE_SEARCH;



    public SearchPageObject(RemoteWebDriver driver){
        super(driver);
    }

     /*template methods*/
    private static String getResultSearhElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    /*template methods*/

    public void initSearchInput() throws Exception{
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,"Cannot find and click search init element",5);
        this.waitForElementPresent(SEARCH_INPUT,"Cannot find search input after clicking search input element",5);
    }


    public void typeSearchLine(String search_line) throws Exception{
        this.waitForElementAndSendKeys(SEARCH_INPUT,search_line,"Cannot find and type into search input", 5);
    }

    public void waitForSearhResult(String substring) throws Exception{
        String SEARCH_RESULT_XPATH = getResultSearhElement(substring);
        this.waitForElementPresent(SEARCH_RESULT_XPATH,"Cannot find search result with substring " + substring);
    }


    public void clickByArticleWithSubstring(String substring) throws Exception{
        String SEARCH_RESULT_XPATH = getResultSearhElement(substring);
        this.waitForElementAndClick(SEARCH_RESULT_XPATH,"Cannot find and search result with substring " + substring,10);
    }



    public void waitForCancelButtonToAppear() throws Exception{
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,"Cannot find search cancel button",5);
    }

    public void waitForCancelButtonToDisAppear() throws Exception{
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,"Cancel search button is still present",5);
    }

    public void clickCancelSearh() throws Exception{
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Cannot find and click cancel searh button",5);
    }


    public int getAmountOfFoundArticles() throws Exception{
        this.waitForElementPresent(SEARCH_RESULT,
                "Cannot find anything by the request",
                15);
        return  this.getAmountOfElements(SEARCH_RESULT);

    }


    public void waitForEmptyResultsLabel() throws Exception{
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot empty result label by request", 15);
    }


    public void asserThereIsNotResultOfSearch() throws Exception{
        this.assertElementsNotPresent(SEARCH_RESULT, "No supporsed not to find any result");
    }

    public void clearSearchInput() throws Exception{
        this.waitForElementAndClear(SEARCH_INPUT,"Cannot find searh input",10);
    }


    public void closeSearch() throws Exception{
        this.waitForElementAndClick(BUTTON_CLOSE_SEARCH, "Cannot find id search_close_btn", 5);
    }

    public void waitForNotPresentButtonCloseSearch() throws Exception{
        this.waitForElementNotPresent(BUTTON_CLOSE_SEARCH, "x is still present on the page", 5);
    }



}
