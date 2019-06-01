package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class ArticlePageObject extends MainPageObject {

    protected static String TITLE,
                            FOOTER_ELEMENT,
                            OPTION_BUTTON,
                            OPTION_ADD_TO_MY_LIST_BUTTON,
                            ADD_TO_MY_LIST_OVERLAY,
                            MY_LIST_INPUT,
                            MY_LIST_OK_BUTTON,
                            CLOSE_ARTICLE_BUTTON;



    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }


    public WebElement waitForTittleElement() throws Exception{
        return this.waitForElementPresent(TITLE,"Cannot find tittle element",15);
    }

    public String getTittle() throws Exception{
        WebElement title_element = waitForTittleElement();

        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }


    public void swipeToFooter() throws Exception{
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, 40, "Cannot find end of article");
        }
        else {
            this.swipeUpTittleElementAppear(FOOTER_ELEMENT,"Cannot fine the end of article",40);
        }
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

    public void addAtricleToMySaved() throws Exception{
        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON,"Cannot fiend option to add article to reading list",5);
    }
}
