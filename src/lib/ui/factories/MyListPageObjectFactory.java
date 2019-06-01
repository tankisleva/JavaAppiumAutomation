package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.android.AndroidMyListPageObject;
import lib.ui.android.AndroidNavigationUI;
import lib.ui.ios.IOSMyListPageObject;
import lib.ui.ios.IOSNavigationUI;

public class MyListPageObjectFactory {

    public static MyListPageObject get(AppiumDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidMyListPageObject(driver);
        } else {
            return new IOSMyListPageObject(driver);
        }
    }
}
