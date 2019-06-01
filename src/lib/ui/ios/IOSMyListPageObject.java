package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class IOSMyListPageObject extends MyListPageObject {

    static {
        FOLDER_BY_ARTICLE_TPL = "xpath://XCUIElementTypeLink[contains(@name='{TITTLE_ARTICLE}')]";
        TITTLE_ON_LIST_1 = "id:Java (programming language) Object-oriented programming language";
        XPATH_IN_LIST = "xpath://XCUIElementTypeLink";

    }



    public IOSMyListPageObject(AppiumDriver driver){
        super(driver);
    }
}
