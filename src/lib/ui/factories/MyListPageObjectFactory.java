package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.android.AndroidMyListPageObject;
import lib.ui.android.AndroidNavigationUI;
import lib.ui.ios.IOSArticlePageObject;
import lib.ui.ios.IOSMyListPageObject;
import lib.ui.ios.IOSNavigationUI;
import lib.ui.mobile_web.MwArticlePageObject;
import lib.ui.mobile_web.MwMyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListPageObjectFactory {

    public static MyListPageObject get(RemoteWebDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidMyListPageObject(driver);
        } else if (Platform.getInstance().isIos()) {
            return new IOSMyListPageObject(driver);
        } else {
            return new MwMyListPageObject(driver);
        }
    }
}
