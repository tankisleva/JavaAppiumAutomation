package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidSearhPageObject;
import lib.ui.ios.IOSSearchPageObject;
import lib.ui.mobile_web.MwSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObjectFactory {

    public static SearchPageObject get(RemoteWebDriver driver){
       if(Platform.getInstance().isAndroid()){
           return new AndroidSearhPageObject(driver);
       } else if (Platform.getInstance().isIos()) {
           return new IOSSearchPageObject(driver);
       } else {
           return new MwSearchPageObject(driver);
       }
    }
}
