package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.android.AndroidMyListPageObject;
import lib.ui.android.AndroidNavigationUI;
import lib.ui.ios.IOSMyListPageObject;
import lib.ui.ios.IOSNavigationUI;
import lib.ui.mobile_web.MwMyListPageObject;
import lib.ui.mobile_web.MwNavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUiFactory {

    public static NavigationUI get(RemoteWebDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidNavigationUI(driver);
        } else if (Platform.getInstance().isIos()) {
            return new IOSNavigationUI(driver);
        } else {
            return new MwNavigationUI(driver);
        }
    }
}
