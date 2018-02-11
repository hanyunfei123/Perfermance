package com.fanli.android;

import com.fanli.android.handleData.GetDeviceInfo;
import com.fanli.android.handleData.GetFps;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestDemo {

    private static AndroidDriver<AndroidElement> driver;
    GetDeviceInfo getDeviceInfo = new GetDeviceInfo();
    TouchAction action = new TouchAction(driver);

    @BeforeSuite
    public void setUp() throws Exception {
        //设置apk的路径
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "FanliAndroid-release-fanli.apk");

        //设置自动化相关参数
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("noReset", true);
        //  capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("device","Android");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", getDeviceInfo.getDeviceName());

        //设置安卓系统版本
        capabilities.setCapability("platformVersion", getDeviceInfo.getOsVersion());
        //设置apk路径
        //capabilities.setCapability("app", app.getAbsolutePath());

        //设置app的主包名和主类名
        capabilities.setCapability("appPackage", "com.fanli.android.apps");
        capabilities.setCapability("appActivity", "com.fanli.android.basicarc.ui.activity.SplashActivity");

        //初始化
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//        action.tap(0,0).perform();
//        try{
//            if(driver.findElementById("com.fanli.android.apps:id/close").isDisplayed()){
//                driver.pressKeyCode(AndroidKeyCode.BACK);
//            };
//        }catch(Exception e){
//        }
    }

    @Test
    public void dataMonitor() throws IOException, InterruptedException{
        while (!Switch.fpsStart){
            Thread.sleep(3000);
            System.out.println("waiting");
        }
        GetFps.main(null);
    }

    @Test
    public void NineTest() throws Exception {

        Thread.sleep(8000);


        driver.findElementByAndroidUIAutomator("text(\"9块9\")").click();
        Thread.sleep(2000);

        int width=driver.manage().window().getSize().width;
        int height=driver.manage().window().getSize().height;
        Switch.fpsStart = true;
        for(int i=0;i<=5;i++) {
            Thread.sleep(200);
            for (int i1 = 0; i1 <= 5; i1++) {
                TouchAction action = new TouchAction(driver).press(width / 2, height * 5 / 7).waitAction().moveTo(width / 2, height * 2 / 7).release();
                action.perform();
            }
            Thread.sleep(200);
            for (int i2 = 0; i2 <= 5; i2++) {
                TouchAction action1 = new TouchAction(driver).press(width / 2, height * 2 / 7).waitAction().moveTo(width / 2, height * 5 / 7).release();
                action1.perform();
            }
        }
        Switch.fpsEnd=true;

    }

    @Test
    public void SuperTest() throws InterruptedException,IOException {
        Thread.sleep(8000);
        for (int i=0;i<5;i++){
            driver.findElementByAndroidUIAutomator("text(\"超级返\")").click();
            Thread.sleep(200);
            //        Assert.assertEquals(text,"限量秒杀");
            driver.pressKeyCode(AndroidKeyCode.BACK);
            Thread.sleep(100);
        }
        System.out.println("超级返测试通过");
    }

    public static void clickByText(String text){
        try{
            if(driver.findElementByName(text).isDisplayed()){
                driver.findElementByName(text).click();
            }
        }catch(Exception e){
        }
    }

    @AfterSuite
    public void tearDown() throws Exception {
        driver.quit();
    }
}