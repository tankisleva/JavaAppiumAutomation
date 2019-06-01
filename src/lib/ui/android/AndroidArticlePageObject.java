package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

      static {
            TITLE = "id:org.wikipedia:id/view_page_title_text";
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
            OPTION_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
            OPTION_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']";
            ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
            MY_LIST_INPUT = "id:org.wikipedia:id/text_input";
            MY_LIST_OK_BUTTON = "id:android:id/button1";
            CLOSE_ARTICLE_BUTTON = "xpath://*[@content-desc='Navigate up']";
      }

   public AndroidArticlePageObject(AppiumDriver driver){
          super(driver);
   }

}
