import io.appium.java_client.TouchAction;
import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FirstTest extends CoreTestCase {

    private MainPageObject mainPageObject;

    protected void setUp() throws Exception{
        super.setUp();

        mainPageObject = new MainPageObject(driver);
    }





    @Test
    public void testSearchTextAndCancelSearch() {
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        mainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "Can not find input Search", 5);
        mainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                5);
        mainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"), "Cannot field clear", 5);
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Cannot find id search_close_btn", 5);
        mainPageObject.waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "x is still present on the page", 5);


    }


    @Test
    public void testVerifyResultSearch() {
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        mainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "Can not find input Search", 5);
        List<WebElement> layouts = driver.findElementsById("org.wikipedia:id/page_list_item_title");
        for (WebElement layout : layouts) {
            assertTrue(layout.getAttribute("text").contains("Java"));
        }

    }









    /*Добавляем в список две статьи, удаляем одну статью, проверяем что удалилась
    * */
    @Test
    public void testAddOneAndTwoArticlesAndDeleteOneArticleFromList() {

        // search and add to folder Learning Programming first article object-oriented programming
        String name_of_folder = "Learning programming";
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        mainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "Can not find input Search", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15);
        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"), "Cannot find 'More options'", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"), "Cannot find 'Add to reading list'", 5);
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"), "Connot button Got it", 5);
//        waitForElementAndClick(By.id("org.wikipedia:id/create_button"),"Cannot find Create_button",5);
        mainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/text_input"), "Cannot cleat input field to add to reading list", 5);
        mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), name_of_folder, "Cannot put text to article folder input", 5);
        mainPageObject.waitForElementAndClick(By.id("android:id/button1"), "Cannot press  button ok", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"), "Cannot close article, cannot find X link", 5);


        // search and add to folder Learning Programming second article Wikimedia list article
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        mainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "Can not find input Search", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Wikimedia list article']"),
                "Cannot find 'Wikimedia list article' topic searching by 'Java'",
                15);
        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"), "Cannot find 'More options'", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"), "Cannot find 'Add to reading list'", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='"+name_of_folder+"']"),"Cannot select " +name_of_folder+" for adding second article",5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"), "Cannot close article, cannot find X link", 5);


        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"), "Cannot find navigation button to My List", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.TextView[@text='" + name_of_folder + "']"), "Cannot find created folder", 5);
        mainPageObject.swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"), "Cannot article in list item");

        // verify that one article is exist in list and two article not exist
        mainPageObject.waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"), "Cannot delete saved article", 5);
        mainPageObject.waitForElementPresent(By.xpath("//*[@text='Wikimedia list article']"), "Cannot saved article", 5);
    }


    @Test
    public void  testAssertTitle() {
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find search input Search Wikipedia", 5);
        String search_line = "Java";

        mainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), search_line, "Can not find input Search", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Search Wikipedia' input",
                5);
       assertTrue(mainPageObject.assertElementsPresent(By.id("org.wikipedia:id/view_page_title_text")));

    }



    }


