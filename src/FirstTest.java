import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
//        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/oleg/Developer/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
    }


    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void search(){
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),"Cannot find search input Search Wikipedia",5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),"Java","Can not find input Search",5);
        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
               "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15);
    }



    @Test
    public void cancelSearch(){
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),"Cannot find search input Search Wikipedia",5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),"Java","Can not find input Search",5);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),"Cannot field clear",5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),"Cannot find id search_close_btn",5);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"),"x is still present on the page",5);
    }


    @Test
    public void compareArticleTitle(){
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),"Cannot find search input Search Wikipedia",5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),"Java","Can not find input Search",5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15);
       WebElement title_element =  waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),"Cannot find article tittle",15);
       String article_title = title_element.getAttribute("text");
        Assert.assertEquals("We see unexpected title","Java (programming language)",article_title);

    }


    @Test
    public void searchTextAndCancelSearch(){
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),"Cannot find search input Search Wikipedia",5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),"Java","Can not find input Search",5);
        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                5);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),"Cannot field clear",5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),"Cannot find id search_close_btn",5);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"),"x is still present on the page",5);


    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message +"\n");
        return  wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message +"\n");
        return  wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }



    private WebElement waitForElementPresent(By by, String error_message){
      return waitForElementPresent(by,error_message,5);
    }


    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

}
