package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static  String
            MY_LIST_LINK,
            CLOSE_AUTH_ALERT,
            OPEN_NAVIGATION;



    public NavigationUI(RemoteWebDriver driver){
        super(driver);
    }

    public void clickMyList() throws Exception{

        if(Platform.getInstance().isMV()){
            this.tryClickelementWithFewAttempts(MY_LIST_LINK,"Cannot find navigation button to My List",5);
        }
        this.waitForElementAndClick(MY_LIST_LINK, "Cannot find navigation button to My List", 5);
    }

    public void closeAuthAlert() throws Exception {
        this.waitForElementAndClick(CLOSE_AUTH_ALERT, "Cannot find elemnt close auth alert", 5);
    }


    public boolean assertAuthClose() throws Exception{
        return this.assertElementsPresent(CLOSE_AUTH_ALERT);
    }


    public void openNavigation() throws Exception{
        if (Platform.getInstance().isMV()){
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation button",5);
        } else {
            System.out.println("Method openNavigation() do nothing for platform " +Platform.getInstance().getPLatformVar());
        }
    }





}
