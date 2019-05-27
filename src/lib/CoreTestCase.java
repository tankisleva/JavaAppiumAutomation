package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CoreTestCase extends TestCase  {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private  static  final String platform = System.getenv("PLATFORM");

    protected AppiumDriver driver;
    private static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        if (platform.equals(PLATFORM_ANDROID)){
            DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
            driver = new AndroidDriver(new URL(AppiumUrl), capabilities);
            driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        }

        else {
            DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
            driver = new IOSDriver(new URL(AppiumUrl), capabilities);
            driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        }

        this.rotateScreenPortrait();
    }


    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
        driver.quit();
    }


    protected void rotateScreenPortrait(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }


    protected void backgroundApp(int sec){
        //driver.runAppInBackground(sec);
        driver.runAppInBackground(Duration.ofSeconds(sec));
    }


    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)){
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
//        capabilities.setCapability("automationName","Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            //capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("app", "/Users/oleg/Developer/JavaAppiumAutomation/apks/org.wikipedia.apk");
        }
        else if (platform.equals(PLATFORM_IOS)){
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone X");
            capabilities.setCapability("platformVersion", "11.3");
            capabilities.setCapability("app", "/Users/oleg/Developer/JavaAppiumAutomation/apks/Wikipedia.app");
            capabilities.setCapability("automationName", "XCUITest");
            capabilities.setCapability("autoAcceptAlerts", true);
            capabilities.setCapability("language", "en");
            capabilities.setCapability("locale", "fr_FR");
        }
        else {
            throw new Exception("Cannot get run platform form env variable. Platform valie " +platform);
        }

        return capabilities;
    }
}
