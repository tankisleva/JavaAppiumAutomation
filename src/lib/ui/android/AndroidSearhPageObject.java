package lib.ui.android;


import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;


public class AndroidSearhPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
                SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]";
                SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
                SEARCH_RESULT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
                SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
                BUTTON_CLOSE_SEARCH = "id:org.wikipedia:id/search_close_btn";
    }

    public AndroidSearhPageObject(AppiumDriver driver){
        super(driver);
    }



}
