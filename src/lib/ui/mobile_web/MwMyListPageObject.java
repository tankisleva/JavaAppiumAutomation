package lib.ui.mobile_web;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MwMyListPageObject extends MyListPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://android.widget.TextView[@text='{FOLDER_NAME}']";
        FOLDER_BY_ARTICLE_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITTLE_ARTICLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(), '{TITTLE_ARTICLE}')]/../../div[contains(@class,'watched')]";
    }

    public MwMyListPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
