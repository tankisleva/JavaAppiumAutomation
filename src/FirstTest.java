import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
//        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/oleg/Developer/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

        if (driver.getOrientation().toString().equals("LANDSCAPE")){
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
    }



    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "Can not find input Search", 5);
        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15);
    }


    @Test
    public void cancelSearch() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Cannot find search input Search Wikipedia", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "Can not find input Search", 5);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"), "Cannot field clear", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Cannot find id search_close_btn", 5);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "x is still present on the page", 5);
    }


    @Test
    public void compareArticleTitle() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "Can not find input Search", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15);
        WebElement title_element = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "Cannot find article tittle", 15);
        String article_title = title_element.getAttribute("text");
        Assert.assertEquals("We see unexpected title", "Java (programming language)", article_title);

    }


    @Test
    public void searchTextAndCancelSearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "Can not find input Search", 5);
        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                5);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"), "Cannot field clear", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Cannot find id search_close_btn", 5);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "x is still present on the page", 5);


    }


    @Test
    public void verifyResultSearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "Can not find input Search", 5);
        List<WebElement> layouts = driver.findElementsById("org.wikipedia:id/page_list_item_title");
        for (WebElement layout : layouts) {
            Assert.assertTrue(layout.getAttribute("text").contains("Java"));
        }

    }


    @Test
    public void swipeArticle() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "Can not find input Search", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15);
        swipeUp(2000);
        swipeUp(2000);
        swipeUp(2000);
        swipeUp(2000);
        swipeUp(2000);
    }


    @Test
    public void swipeToElement() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Appium", "Can not find input Search", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium'",
                15);
        swipeUpToFindElement(By.xpath("//*[@text='View page in browser']"), 6, "Cannot swipe to 'View page in browser'");


    }


    @Test
    public void addAndDeleteArticleToAddList() {
        String name_of_folder = "Learning programming";
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "Can not find input Search", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15);
        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"), "Cannot find 'More options'", 5);
        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"), "Cannot find 'Add to reading list'", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"), "Connot button Got it", 5);
//        waitForElementAndClick(By.id("org.wikipedia:id/create_button"),"Cannot find Create_button",5);
        waitForElementAndClear(By.id("org.wikipedia:id/text_input"), "Cannot cleat input field to add to reading list", 5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), name_of_folder, "Cannot put text to article folder input", 5);
        waitForElementAndClick(By.id("android:id/button1"), "Cannot press  button ok", 5);
        waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"), "Cannot close article, cannot find X link", 5);
        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"), "Cannot find navigation button to My List", 5);
        waitForElementAndClick(By.xpath("//android.widget.TextView[@text='" + name_of_folder + "']"), "Cannot find title in list", 5);
        swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"), "Cannot article in list item");
        waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"), "Cannot delete saved article", 5);
    }


    @Test
    public void testAmountNotEmptySearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        String search_line = "Linkin Park Disckography";

        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), search_line, "Can not find input Search", 5);
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        waitForElementPresent(By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15);
        int amount_of_search_results = getAmountOfElements(By.xpath(search_result_locator));
        Assert.assertTrue("We found too few results", amount_of_search_results > 0);
    }


    @Test
    public void testAmountOfEmptySearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        String search_line = "kjgdkjgdkjgkdgjdg";

        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), search_line, "Can not find input Search", 5);
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text='No results found']";
        waitForElementPresent(By.xpath(empty_result_label), "Cannot empty result label by request " + search_line, 15);
        assertElementsNotPresent(By.xpath(search_result_locator), "We have found some results by request " + search_line);

    }


    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        String search_line = "Java";

        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), search_line, "Can not find input Search", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                15);
        String tittle_before_rotation = waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15);

        driver.rotate(ScreenOrientation.LANDSCAPE);
        String tittle_after_rotation = waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15);
        Assert.assertEquals("Tittle article have been changed after rotation", tittle_before_rotation, tittle_after_rotation);
        driver.rotate(ScreenOrientation.PORTRAIT);
        String tittle_after_second_rotation = waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15);
        Assert.assertEquals("Tittle article have been changed after rotation", tittle_before_rotation, tittle_after_second_rotation);

    }


    @Test
    public void testCheckSearchArticleInBackground() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        String search_line = "Java";

        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), search_line, "Can not find input Search", 5);
        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Search Wikipedia' input",
                5);
        driver.runAppInBackground(2);
        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find article after returning from background",
                5);


    }

    /*Добавляем в список две статьи, удаляем одну статью, проверяем что удалилась
    * */
    @Test
    public void addOneAndTwoArticlesAndDeleteOneArticleFromList() {

        // search and add to folder Learning Programming first article object-oriented programming
        String name_of_folder = "Learning programming";
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "Can not find input Search", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15);
        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"), "Cannot find 'More options'", 5);
        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"), "Cannot find 'Add to reading list'", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"), "Connot button Got it", 5);
//        waitForElementAndClick(By.id("org.wikipedia:id/create_button"),"Cannot find Create_button",5);
        waitForElementAndClear(By.id("org.wikipedia:id/text_input"), "Cannot cleat input field to add to reading list", 5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), name_of_folder, "Cannot put text to article folder input", 5);
        waitForElementAndClick(By.id("android:id/button1"), "Cannot press  button ok", 5);
        waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"), "Cannot close article, cannot find X link", 5);


        // search and add to folder Learning Programming second article Wikimedia list article
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "Can not find input Search", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Wikimedia list article']"),
                "Cannot find 'Wikimedia list article' topic searching by 'Java'",
                15);
        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"), "Cannot find 'More options'", 5);
        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"), "Cannot find 'Add to reading list'", 5);
        waitForElementAndClick(By.xpath("//*[@text='"+name_of_folder+"']"),"Cannot select " +name_of_folder+" for adding second article",5);
        waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"), "Cannot close article, cannot find X link", 5);


        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"), "Cannot find navigation button to My List", 5);
        waitForElementAndClick(By.xpath("//android.widget.TextView[@text='" + name_of_folder + "']"), "Cannot find created folder", 5);
        swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"), "Cannot article in list item");

        // verify that one article is exist in list and two article not exist
        waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"), "Cannot delete saved article", 5);
        waitForElementPresent(By.xpath("//*[@text='Wikimedia list article']"), "Cannot saved article", 5);
    }


    @Test
    public void  assertTitle() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        String search_line = "Java";

        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), search_line, "Can not find input Search", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Search Wikipedia' input",
                5);
        Assert.assertTrue(assertElementsPresent(By.id("org.wikipedia:id/view_page_title_text")));

    }


    @Test
    public void  screenOrientation() {
        System.out.println(driver.getOrientation().toString());

    }


    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }


    protected void swipeUpQuick() {
        swipeUp(200);
    }


    protected void swipeUpToFindElement(By by, int max_swipes, String errorMessage) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;

        }
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }


    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }


    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }


    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int lower_y = element.getLocation().getY();
        int upper_y = lower_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();


    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementsNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element " + by.toString() + " supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);

        }
    }

    private boolean assertElementsPresent(By by) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements < 0) {
            return false;

        } else
            return true;

    }


    private String waitForElementAndGetAttribute (By by, String attribute, String error_message,long timeoutInSecond)
        {
            WebElement element = waitForElementPresent(by, error_message, timeoutInSecond);
            return element.getAttribute(attribute);
        }


    }


