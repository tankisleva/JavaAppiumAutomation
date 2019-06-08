package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListPageObject extends MyListPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://android.widget.TextView[@text='{FOLDER_NAME}']";
                FOLDER_BY_ARTICLE_TPL = "xpath://*[@text='{TITTLE_ARTICLE}']";
    }

    public AndroidMyListPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
