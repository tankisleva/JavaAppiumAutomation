package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static  String
            MY_LIST_LINK,
            CLOSE_AUTH_ALERT;



    public NavigationUI(AppiumDriver driver){
        super(driver);
    }

    public void clickMyList() throws Exception{
        this.waitForElementAndClick(MY_LIST_LINK, "Cannot find navigation button to My List", 5);
    }

    public void closeAuthAlert() throws Exception {
        this.waitForElementAndClick(CLOSE_AUTH_ALERT, "Cannot find elemnt close auth alert", 5);
    }


    public boolean assertAuthClose() throws Exception{
        return this.assertElementsPresent(CLOSE_AUTH_ALERT);
    }






}
