package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUiFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListTests extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";
    private static final String
    login = "Tanki sleva",
    password = "V1234567";


    @Test
    public void testAddAndDeleteArticleToAddList() throws Exception{
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUiFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("JAVA");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
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

        if (Platform.getInstance().isMV()){
            AuthorizacionPageObject auth = new AuthorizacionPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(login, password);
            auth.submitForm();
            articlePageObject.waitForTittleElement();
            assertEquals("We are not same page after login",articleTittle,articlePageObject.getTittle());

        }

        articlePageObject.closeArticle();

        navigationUI.openNavigation();
        navigationUI.clickMyList();

        articlePageObject.addAtricleToMySaved();


        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openFolderByName(name_of_folder);
        }

        myListPageObject.swipeArticleToDelete("Java (programming language) Object-oriented programming language");
        myListPageObject.wairForArticleToDisappearByTitle("Java (programming language) Object-oriented programming language");
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
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
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

        if (Platform.getInstance().isMV()){
            AuthorizacionPageObject auth = new AuthorizacionPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(login, password);
            auth.submitForm();
            articlePageObject.waitForTittleElement();
            assertEquals("We are not same page after login",articleTittle1,articlePageObject.getTittle());

        }

        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openFolderByName(name_of_folder);
        }
        articlePageObject.closeArticle();

        // search and add to folder Learning Programming second article Wikimedia list article
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("JAVA");
        searchPageObject.clickByArticleWithSubstring("ikimedia list article");
        articlePageObject.waitForTittleElement();
        String articleTittle2 = articlePageObject.getTittle();

        if (Platform.getInstance().isMV()){
            AuthorizacionPageObject auth = new AuthorizacionPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(login, password);
            auth.submitForm();
            articlePageObject.waitForTittleElement();
            assertEquals("We are not same page after login",articleTittle2,articlePageObject.getTittle());

        }

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
        navigationUI.openNavigation();
        navigationUI.clickMyList();


        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openFolderByName(name_of_folder);
        }

        if (Platform.getInstance().isAndroid()){
        myListPageObject.swipeArticleToDelete(articleTittle1);}

        else {
            myListPageObject.swipeArticleToDelete("Java (programming language) Object-oriented programming language");
        }

        if (Platform.getInstance().isAndroid()){
        myListPageObject.wairForArticleToDisappearByTitle(articleTittle1);
        myListPageObject.wairForArticleToAppearByTitle(articleTittle2);}
        else {
            Assert.assertTrue(myListPageObject.getContainsTextArticleInListIos("Wikimedia list article"));
        }
    }



}
