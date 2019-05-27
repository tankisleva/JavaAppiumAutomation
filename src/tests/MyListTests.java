package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListTests extends CoreTestCase {



    @Test
    public void testAddAndDeleteArticleToAddList() throws Exception{
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("JAVA");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTittleElement();
        String articleTittle = articlePageObject.getTittle();
        String name_of_folder = "Learning programming";
        articlePageObject.addArticleToMyList(name_of_folder);
        articlePageObject.closeArticle();
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyList();
        MyListPageObject myListPageObject = new MyListPageObject(driver);
        myListPageObject.openFolderByName(name_of_folder);
        myListPageObject.swipeArticleToDelete(articleTittle);
        myListPageObject.wairForArticleToDisappearByTitle(articleTittle);
    }


    /*Добавляем в список две статьи, удаляем одну статью, проверяем что удалилась
     * */
    @Test
    public void testAddOneAndTwoArticlesAndDeleteOneArticleFromList() throws Exception {

        // search and add to folder Learning Programming first article object-oriented programming
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        MyListPageObject myListPageObject = new MyListPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("JAVA");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTittleElement();
        String articleTittle1 = articlePageObject.getTittle();
        String name_of_folder = "Learning programming";
        articlePageObject.addArticleToMyList(name_of_folder);
        articlePageObject.closeArticle();

        // search and add to folder Learning Programming second article Wikimedia list article
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("JAVA");
        searchPageObject.clickByArticleWithSubstring("Wikimedia list article");
        articlePageObject.waitForTittleElement();
        String articleTittle2 = articlePageObject.getTittle();
        articlePageObject.clickOptionButtonInArticle();
        articlePageObject.clickButtonAddToListInArticle();
        myListPageObject.openFolderByName(name_of_folder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyList();
        myListPageObject.openFolderByName(name_of_folder);
        myListPageObject.swipeArticleToDelete(articleTittle1);
        myListPageObject.wairForArticleToDisappearByTitle(articleTittle1);
        myListPageObject.wairForArticleToAppearByTitle(articleTittle2);
    }



}
