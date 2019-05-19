package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String TITLE = "org.wikipedia:id/view_page_title_text",
                                FOOTER_ELEMENT = "//*[@text='View page in browser']",
                                OPTION_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
                                OPTION_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
                                ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
                                MY_LIST_INPUT = "org.wikipedia:id/text_input",
                                MY_LIST_OK_BUTTON = "android:id/button1",
                                CLOSE_ARTICLE_BUTTON = "//*[@content-desc='Navigate up']";



    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }


    public WebElement waitForTittleElement(){
        return this.waitForElementPresent(By.id(TITLE),"Cannot find tittle element",15);
    }

    public String getTittle(){
        WebElement title_element = waitForTittleElement();
        return title_element.getAttribute("text");
    }


    public void swipeToFooter(){
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),20,"Cannot find end of article");
    }


    public void addArticleToMyList(String nameOfFolder){
        this.waitForElementAndClick(By.xpath(OPTION_BUTTON), "Cannot find 'More options'", 5);
        this.waitForElementAndClick(By.xpath(OPTION_ADD_TO_MY_LIST_BUTTON), "Cannot find 'Add to reading list'", 5);
        this.waitForElementAndClick(By.id(ADD_TO_MY_LIST_OVERLAY), "Connot button Got it", 5);
//        waitForElementAndClick(By.id("org.wikipedia:id/create_button"),"Cannot find Create_button",5);
        this.waitForElementAndClear(By.id(MY_LIST_INPUT), "Cannot cleat input field to add to reading list", 5);
        this.waitForElementAndSendKeys(By.id(MY_LIST_INPUT), nameOfFolder, "Cannot put text to article folder input", 5);
        this.waitForElementAndClick(By.id(MY_LIST_OK_BUTTON), "Cannot press  button ok", 5);
    }


    public void closeArticle(){
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON), "Cannot close article, cannot find X link", 5);
    }


    public boolean assertTittlePresent(){
        return this.assertElementsPresent(By.id(TITLE));
    }

    public void clickOptionButtonInArticle(){
        this.waitForElementAndClick(By.xpath(OPTION_BUTTON), "Cannot find 'More options'", 5);
    }


    public void clickButtonAddToListInArticle(){
        this.waitForElementAndClick(By.xpath(OPTION_ADD_TO_MY_LIST_BUTTON), "Cannot find 'Add to reading list'", 5);
    }
}
