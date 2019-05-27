package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;


public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticleTitle() throws Exception {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("JAVA");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String article_title = articlePageObject.getTittle();
        assertEquals("We see unexpected title", "Java (programming language)", article_title);

    }

    @Test
    public void testSwipeToArticle() throws Exception {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTittleElement();
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testSwipeArticle() throws Exception {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTittleElement();
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.swipeUp(2000);
        mainPageObject.swipeUp(2000);
        mainPageObject.swipeUp(2000);
        mainPageObject.swipeUp(2000);
        mainPageObject.swipeUp(2000);
    }


    @Test
    public void  testAssertTitle() throws Exception{
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("JAVA");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        assertTrue(articlePageObject.assertTittlePresent());

    }
}
