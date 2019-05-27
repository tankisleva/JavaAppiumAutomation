package tests.iOS;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome() throws Exception{

        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);

        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.clickNexButton();

        welcomePageObject.waitForNewWaysTorExplore();
        welcomePageObject.clickNexButton();

        welcomePageObject.waitForAddOrEditPreferredLanguages();
        welcomePageObject.clickNexButton();

        welcomePageObject.waitForLearnMoreAboutDataCollectedText();
        welcomePageObject.clickGetStartedButton();
    }


}


