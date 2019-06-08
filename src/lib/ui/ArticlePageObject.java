package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class ArticlePageObject extends MainPageObject {

    protected static String TITLE,
                            FOOTER_ELEMENT,
                            OPTION_BUTTON,
                            OPTION_ADD_TO_MY_LIST_BUTTON,
                            ADD_TO_MY_LIST_OVERLAY,
                            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                            MY_LIST_INPUT,
                            MY_LIST_OK_BUTTON,
                            CLOSE_ARTICLE_BUTTON;



    public ArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }


    public WebElement waitForTittleElement() throws Exception{
        return this.waitForElementPresent(TITLE,"Cannot find tittle element",15);
    }

    public String getTittle() throws Exception{
        WebElement title_element = waitForTittleElement();

        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIos()){
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }


    public void swipeToFooter() throws Exception{
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, 40, "Cannot find end of article");
        }
        else if (Platform.getInstance().isIos()){
            this.swipeUpTittleElementAppear(FOOTER_ELEMENT,"Cannot fine the end of article",40);
        } else {
            this.scrollWebPageTittleElementNotVisible(FOOTER_ELEMENT,"Cannot find the end of article",40);
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
        if (Platform.getInstance().isMV()){
            return;
        }
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
        if(Platform.getInstance().isMV()){
            this.removeArticleFromSavedIfItAd();
        }
        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON,"Cannot fiend option to add article to reading list",5);
    }

    public void removeArticleFromSavedIfItAd() throws Exception{
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,"Cannot click button to remove an article from saved",1);
        }

        this.waitForElementPresent(OPTION_ADD_TO_MY_LIST_BUTTON,"Cannot find button to add article to saved list after removing it from this before");
    }
}
