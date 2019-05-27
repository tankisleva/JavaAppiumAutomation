package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults() throws Exception{

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String title_before_rotacion = articlePageObject.getTittle();


        this.rotateScreenLandscape();
        String title_after_rotacion = articlePageObject.getTittle();
        assertEquals("Tittle article have been changed after rotation", title_before_rotacion, title_after_rotacion);
        this.rotateScreenPortrait();
        String tittle_after_second_rotation = articlePageObject.getTittle();
        assertEquals("Tittle article have been changed after rotation", title_before_rotacion, tittle_after_second_rotation);

    }


    @Test
    public void testCheckSearchArticleInBackground() throws Exception {
        String search_line = "Java";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForSearhResult("Object-oriented programming language");
        this.backgroundApp(2);
        searchPageObject.waitForSearhResult("Object-oriented programming language");


    }
}
