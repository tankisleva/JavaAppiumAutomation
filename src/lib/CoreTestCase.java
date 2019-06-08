package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CoreTestCase extends TestCase  {


    protected RemoteWebDriver driver;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.openWikiWebPagwForMobileWeb();
    }


    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
        driver.quit();
    }


    protected void rotateScreenPortrait(){

        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPLatformVar());
        }



    }

    protected void rotateScreenLandscape(){

        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPLatformVar());
        }


    }


    protected void backgroundApp(int sec){

        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(sec));
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPLatformVar());
        }
        //driver.runAppInBackground(sec);

    }


    private void skipWelcomePageForIOSApp() throws Exception{
        if (Platform.getInstance().isIos()){
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.clickSkip();
        }
    }


    protected void openWikiWebPagwForMobileWeb(){
        if (Platform.getInstance().isMV()){
            driver.get("https://en.m.wikipedia.org/");
        } else
        {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPLatformVar());
        }
    }

}
