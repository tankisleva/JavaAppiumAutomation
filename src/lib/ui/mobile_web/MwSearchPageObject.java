package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MwSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        // SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_INPUT = "css:form > input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        //  SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[@name="Java (programming language) Object-oriented programming language"]";
        SEARCH_RESULT = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
        //BUTTON_CLOSE_SEARCH = "id:org.wikipedia:id/search_close_btn";
    }

    public MwSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
