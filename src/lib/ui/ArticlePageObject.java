package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String TITLE = "id:org.wikipedia:id/view_page_title_text",
                                FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
                                OPTION_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
                                OPTION_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
                                ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
                                MY_LIST_INPUT = "id:org.wikipedia:id/text_input",
                                MY_LIST_OK_BUTTON = "id:android:id/button1",
                                CLOSE_ARTICLE_BUTTON = "xpath://*[@content-desc='Navigate up']";



    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }


    public WebElement waitForTittleElement() throws Exception{
        return this.waitForElementPresent(TITLE,"Cannot find tittle element",15);
    }

    public String getTittle() throws Exception{
        WebElement title_element = waitForTittleElement();
        return title_element.getAttribute("text");
    }


    public void swipeToFooter() throws Exception{
        this.swipeUpToFindElement(FOOTER_ELEMENT,20,"Cannot find end of article");
    }


    public void addArticleToMyList(String nameOfFolder) throws Exception{
        this.waitForElementAndClick(OPTION_BUTTON, "Cannot find 'More options'", 5);
        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON, "Cannot find 'Add to reading list'", 5);
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY, "Connot button Got it", 5);
//        waitForElementAndClick(By.id("org.wikipedia:id/create_button"),"Cannot find Create_button",5);
        this.waitForElementAndClear(MY_LIST_INPUT, "Cannot cleat input field to add to reading list", 5);
        this.waitForElementAndSendKeys(MY_LIST_INPUT, nameOfFolder, "Cannot put text to article folder input", 5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON, "Cannot press  button ok", 5);
    }


    public void closeArticle() throws Exception{
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON, "Cannot close article, cannot find X link", 5);
    }


    public boolean assertTittlePresent() throws Exception{
        return this.assertElementsPresent(TITLE);
    }

    public void clickOptionButtonInArticle() throws Exception{
        this.waitForElementAndClick(OPTION_BUTTON, "Cannot find 'More options'", 5);
    }


    public void clickButtonAddToListInArticle() throws Exception{
        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON, "Cannot find 'Add to reading list'", 5);
    }
}
