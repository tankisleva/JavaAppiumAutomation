package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUiFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListTests extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";


    @Test
    public void testAddAndDeleteArticleToAddList() throws Exception{
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUiFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("JAVA");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTittleElement();


        String articleTittle = articlePageObject.getTittle();


        if(Platform.getInstance().isAndroid()){
             articlePageObject.addArticleToMyList(name_of_folder);
         } else {
            articlePageObject.addAtricleToMySaved();
            if (navigationUI.assertAuthClose()){
                navigationUI.closeAuthAlert();
            }
        }

        articlePageObject.closeArticle();

        navigationUI.clickMyList();


        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openFolderByName(name_of_folder);
        }

        myListPageObject.swipeArticleToDelete(articleTittle);
        myListPageObject.wairForArticleToDisappearByTitle(articleTittle);
    }


    /*Добавляем в список две статьи, удаляем одну статью, проверяем что удалилась
     * */
    @Test
    public void testAddOneAndTwoArticlesAndDeleteOneArticleFromList() throws Exception {

        // search and add to folder Learning Programming first article object-oriented programming
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUiFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("JAVA");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTittleElement();
        String articleTittle1 = articlePageObject.getTittle();

        if(Platform.getInstance().isAndroid()){
            articlePageObject.addArticleToMyList(name_of_folder);
        } else {
            articlePageObject.addAtricleToMySaved();
            if (navigationUI.assertAuthClose()){
                navigationUI.closeAuthAlert();
            }
        }

        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openFolderByName(name_of_folder);
        }
        articlePageObject.closeArticle();

        // search and add to folder Learning Programming second article Wikimedia list article
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("JAVA");
        searchPageObject.clickByArticleWithSubstring("Wikimedia list article");
        articlePageObject.waitForTittleElement();
        String articleTittle2 = articlePageObject.getTittle();

        if(Platform.getInstance().isAndroid()){
            articlePageObject.clickOptionButtonInArticle();
            articlePageObject.clickButtonAddToListInArticle();
            myListPageObject.openFolderByName(name_of_folder);
        } else {
            articlePageObject.addAtricleToMySaved();
            if (navigationUI.assertAuthClose()){
                navigationUI.closeAuthAlert();
            }
        }

        articlePageObject.closeArticle();
        navigationUI.clickMyList();


        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openFolderByName(name_of_folder);
        }

        myListPageObject.swipeArticleToDelete(articleTittle1);
        myListPageObject.wairForArticleToDisappearByTitle(articleTittle1);
        myListPageObject.wairForArticleToAppearByTitle(articleTittle2);
    }



}
