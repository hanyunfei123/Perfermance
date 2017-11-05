package com.fanli.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadTimeoutException;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        action.tap(0,0).perform();
        try{
            if(driver.findElementById("com.fanli.android.apps:id/close").isDisplayed()){
                driver.pressKeyCode(AndroidKeyCode.BACK);
            };
        }catch(Exception e){
        }
    }

    @Test
    public void NineTest() throws Exception {
        try{
            if(driver.findElementById("com.fanli.android.apps:id/close").isDisplayed()){
                driver.pressKeyCode(AndroidKeyCode.BACK);
            };
        }catch(Exception e){
        }
        Thread.sleep(500);
        driver.findElementByAndroidUIAutomator("text(\"9块9\")").click();
        Thread.sleep(1500);
//            //获取首页元素
//            List<AndroidElement> lis = driver.findElementsById("com.fanli.android.apps:id/ahead_layout");
//            lis.get(8).click();
//
//            //Thread.sleep(2000);
//            //意见反馈
//            driver.findElementByName("意见反馈").click();
//            driver.pressKeyCode(AndroidKeyCode.BACK);
//            //常用问题
//            driver.findElementByName("常见问题").click();
//            Thread.sleep(5000);
//            driver.findElementById("com.fanli.android.apps:id/iv_left").click();
//            //我的淘宝
//            driver.findElementByName("我的淘宝").click();
//            driver.findElementById("com.fanli.android.apps:id/rl_bottom_button").click();
//            try{
//                if(driver.findElementByName("登录").isDisplayed()){
//                    driver.findElementById("com.fanli.android.apps:id/iv_login_delete").click();
//                    List<AndroidElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
//                    textFieldsList.get(0).sendKeys("hitest");
//                    textFieldsList.get(1).sendKeys("fanli123");
//                    driver.findElementById("com.fanli.android.apps:id/btn_login").click();
//                    Thread.sleep(5000);
//                };
//            }catch(Exception e){
//            }
//            driver.findElementById("com.fanli.android.apps:id/iv_close");
//
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

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