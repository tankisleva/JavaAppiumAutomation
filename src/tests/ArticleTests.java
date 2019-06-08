package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;

import org.junit.Test;


public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticleTitle() throws Exception {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("JAVA");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = articlePageObject.getTittle();
        assertEquals("We see unexpected title", "Java (programming language)", article_title);

    }

    @Test
    public void testSwipeToArticle() throws Exception {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTittleElement();
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testSwipeArticle() throws Exception {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
//        searchPageObject.typeSearchLine("Appium");
//        searchPageObject.clickByArticleWithSubstring("Appium");
//        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
//        articlePageObject.waitForTittleElement();
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.swipeUp(2000);
        mainPageObject.swipeUp(2000);
        mainPageObject.swipeUp(2000);
        mainPageObject.swipeUp(2000);
        mainPageObject.swipeUp(2000);
    }


    @Test
    public void  testAssertTitle() throws Exception{
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("JAVA");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        assertTrue(articlePageObject.assertTittlePresent());

    }
}
