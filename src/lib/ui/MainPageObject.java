package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
//import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.Position;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {

        this.driver = driver;
    }


    public void swipeUp(int timeOfSwipe) {


        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);
            //action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
            action.press(new PointOption().withCoordinates(x, start_y)).waitAction().moveTo(new PointOption().withCoordinates(x, end_y)).release().perform();
        } else {
            System.out.println("Method swipeUp() do nothing for platform " + Platform.getInstance().getPLatformVar());
        }

    }


    public void swipeUpQuick() {
        swipeUp(200);
    }

    public boolean isElementPresent(String locator) throws Exception {
        return getAmountOfElements(locator) > 0;
    }


    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) throws Exception {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) throws Exception {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }


    public WebElement waitForElementPresent(String locator, String error_message) throws Exception {
        return waitForElementPresent(locator, error_message, 5);
    }


    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) throws Exception {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) throws Exception {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) throws Exception {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }


    public void swipeElementToLeft(String locator, String error_message) throws Exception {
        WebElement element = waitForElementPresent(locator, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        if (driver instanceof AppiumDriver) {

            TouchAction action = new TouchAction((AppiumDriver) driver);
//        action.press(right_x, middle_y)
//                .waitAction(300)
//                .moveTo(left_x, middle_y)
//                .release()
//                .perform();
            action.press(new PointOption()
                    .withCoordinates(right_x, middle_y));
            action.waitAction();

            if (Platform.getInstance().isAndroid()) {
                action.moveTo(new PointOption()
                        .withCoordinates(left_x, middle_y));
            } else {
                int offset_x = (-1 * element.getSize().getWidth());
                action.moveTo(new PointOption()
                        .withCoordinates(offset_x, 0));
            }
            action.release();
            action.perform();
        } else {
            System.out.println("Method swipeUp() do nothing for platform " + Platform.getInstance().getPLatformVar());
        }

    }

    public int getAmountOfElements(String locator) throws Exception {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementsNotPresent(String locator, String error_message) throws Exception {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_message = "An element " + locator + " supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);

        }
    }

    public boolean assertElementsPresent(String locator) throws Exception {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements < 0) {
            return false;

        } else
            return true;

    }


    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSecond)
            throws Exception {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSecond);
        return element.getAttribute(attribute);
    }


    private By getLocatorByString(String locator_with_type) throws Exception {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else if (by_type.equals("css")) {
            return By.cssSelector(locator);
        } else {
            throw new IllegalAccessException("Cannot get typ of locator. Locaator: " + locator_with_type);
        }
    }


    public void swipeUpToFindElement(String locator, int max_swipes, String errorMessage) throws Exception {
        int already_swiped = 0;
        By by = this.getLocatorByString(locator);
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;

        }
    }


    public void swipeUpTittleElementAppear(String locator, String error_message, int max_swipes) throws Exception {
        int already_swiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)) {
            if (already_swiped > max_swipes) {
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }

    }


    public boolean isElementLocatedOnTheScreen(String locator) throws Exception {
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot fine element by locator", 1).getLocation().getY();
        if (Platform.getInstance().isMV()) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            Object js_result = javascriptExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getWidth();
        return element_location_by_y < screen_size_by_y;
    }

    public void clickElemntToTheRightUpperCorner(String locator, String error_message) throws Exception {
        WebElement element = this.waitForElementPresent(locator + "/..", error_message);
        int left_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        int width = element.getSize().getWidth();

        int point_to_click_x = (left_x + width) - 3;
        int point_to_click_y = middle_y;

        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.tap(new PointOption().withCoordinates(point_to_click_x, point_to_click_y)).perform();
        } else {
            System.out.println("Method swipeUp() do nothing for platform " + Platform.getInstance().getPLatformVar());
        }
    }

    public void scrollWebPageTittleElementNotVisible(String locator, String error_message, int max_swipe) throws Exception {
        int already_swiped = 0;

        WebElement element = this.waitForElementPresent(locator, error_message);

        while (!this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped > max_swipe) {
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }


    public void scrollWebPageUp() {
        if (Platform.getInstance().isMV()) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("window.scrollBy(0, 250)");
        } else {
            System.out.println("Method scrollWebPageUp() do nothing for platform " + Platform.getInstance().getPLatformVar());
        }
    }


    public void tryClickelementWithFewAttempts(String locator, String error_message, int amount_of_attempts) throws Exception{
        int current_attemps = 0;
        boolean need_more_attemps = true;

        while (need_more_attemps) {
            try {
                this.waitForElementAndClick(locator, error_message, 1);
                need_more_attemps = false;
            } catch (Exception e) {
                if (current_attemps > amount_of_attempts) {
                    {
                        this.waitForElementAndClick(locator, error_message, 1);
                    }
                }
                ++current_attemps;
            }
        }
    }

}
