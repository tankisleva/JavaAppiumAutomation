package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {


    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("JAVA");
        searchPageObject.waitForSearhResult("Object-oriented programming language");

    }


    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearh();
        searchPageObject.waitForCancelButtonToDisAppear();
    }

    @Test
    public void testAmountNotEmptySearch() {
        String search_line = "Linkin Park Disckography";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();
        assertTrue("We found too few results", amount_of_search_results > 0);
    }


    @Test
    public void testAmountOfEmptySearch() {
        String search_line = "kjgdkjgdkjgkdgjdg";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.asserThereIsNotResultOfSearch();
    }


    @Test
    public void testSearchTextAndCancelSearch() {
        String search_line = "Java";
        String result = "Object-oriented programming language";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForSearhResult(result);
        searchPageObject.clearSearchInput();
        searchPageObject.closeSearch();
        searchPageObject.waitForNotPresentButtonCloseSearch();


    }


    @Test
    public void testVerifyResultSearch() {
        String search_line = "Java";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_line);
        List<WebElement> layouts = driver.findElementsById("org.wikipedia:id/page_list_item_title");
        for (WebElement layout : layouts) {
            assertTrue(layout.getAttribute("text").contains("Java"));
        }

    }

}
