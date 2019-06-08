package lib;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATTFORM_MOBILE_WEB = "mobile_web";
    private static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";


    private static Platform instance;

    private Platform(){}


    public static Platform getInstance(){
        if (instance == null){
            instance = new Platform();
        }
        return instance;
    }




    public RemoteWebDriver getDriver() throws Exception{
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()){
            return new AndroidDriver(URL,this.getAndroidDesireCapabilities());
        } else if (this.isIos()){
            return new IOSDriver(URL,this.getIosDesireCapabilities());
        } else if (this.isMV()){
            return new ChromeDriver(this.getMWChromeOption());
        }else {
            throw new Exception("Cannot detected type of the Driver. Platform value: " +this.getPLatformVar());
        }

    }


    public boolean isAndroid(){
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIos(){
        return isPlatform(PLATFORM_IOS);
    }


    public boolean isMV(){
        return isPlatform(PLATTFORM_MOBILE_WEB);
    }


    private DesiredCapabilities getAndroidDesireCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
//        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        //capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", "/Users/oleg/Developer/JavaAppiumAutomation/apks/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIosDesireCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone X");
        capabilities.setCapability("platformVersion", "11.3");
        capabilities.setCapability("app", "/Users/oleg/Developer/JavaAppiumAutomation/apks/Wikipedia.app");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("autoAcceptAlerts", true);
//        capabilities.setCapability("language", "en");
//        capabilities.setCapability("locale", "fr_FR");
        return capabilities;
    }


    private ChromeOptions getMWChromeOption(){
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String,Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340,640");
        return chromeOptions;
    }

    private boolean isPlatform(String my_platform){
        String platform = this.getPLatformVar();
        return my_platform.equals(platform);
    }

    public String getPLatformVar(){
        return System.getenv("PLATFORM");
    }


}
