package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String
            STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
            STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
            NEXT_LINK = "id:Next",
            GET_STARTED_BUTTON = "id:Get started",
            SKIP = "id:Skip";


    public WelcomePageObject(RemoteWebDriver driver){
        super(driver);
    }

   public void waitForNewWaysTorExplore() throws Exception{
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT,"Cannot find 'New ways to explore' text",10);
    }

    public void waitForLearnMoreLink() throws Exception{
          this.waitForElementPresent(STEP_LEARN_MORE_LINK,"Cannot find 'Learn more about Wikipedia' link",30);
    }

    public void waitForAddOrEditPreferredLanguages() throws Exception{
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK,"Cannot find 'Add or edit preferred languages' link",10);
    }


    public void waitForLearnMoreAboutDataCollectedText() throws Exception{
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,"Cannot find 'Learn more about data collected' link",10);
    }


    public void clickNexButton() throws Exception{
        this.waitForElementAndClick(NEXT_LINK,"Cannot find  and click 'Next' button",10);
//        MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("Next");
//        el1.click();
    }


    public void clickGetStartedButton() throws Exception{
        this.waitForElementAndClick(GET_STARTED_BUTTON,"Cannot find  and click 'Get started' button",10);
    }


    public void clickSkip() throws Exception{
        this.waitForElementAndClick(SKIP,"Cannot find and click skip button",5);
    }


}
