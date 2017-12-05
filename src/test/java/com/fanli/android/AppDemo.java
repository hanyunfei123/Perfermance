package com.fanli.android;

//import driver.ExTestception;
//import driver.Thread;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppDemo {

    private static AndroidDriver<AndroidElement> driver;
    private TouchAction androidDriver;

    @BeforeSuite
    public void setUp() throws Exception {
        //设置apk的路径
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "FanliAndroid-release-fanli.apk");

        //设置自动化相关参数
        DesiredCapabilities capabilities;
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("noReset", true);
        //  capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("device", "Android");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "a2aa8146");

        //设置安卓系统版本
        capabilities.setCapability("platformVersion", "4.4.4");
        //设置apk路径
        //capabilities.setCapability("app", app.getAbsolutePath());
        //输入中文
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");

        //设置app的主包名和主类名
        capabilities.setCapability("appPackage", "com.fanli.android.apps");
        capabilities.setCapability("appActivity", "com.fanli.android.basicarc.ui.activity.SplashActivity");

        //初始化
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    //case1：首页进入超级返
    public void moreTest() throws InterruptedException {

        System.out.println(driver.getPageSource());

        try{
            if (driver.findElementById( "com.fanli.android.apps:id/splash_img").isDisplayed()){
                driver.findElementByName("跳过").click();
            }
        }catch (Exception e){
        }
        Thread.sleep(500);
        for (int i=0;i<5;i++){
            driver.findElementByAndroidUIAutomator("text(\"超级返\")").click();
            Thread.sleep(200);
            //        Assert.assertEquals(text,"限量秒杀");
            driver.pressKeyCode(AndroidKeyCode.BACK);
            Thread.sleep(100);
        }
        System.out.println("超级返测试通过");
    }

    //进入九块九
    @Test
    public void moreTest1() throws Exception{
        System.out.println(driver.getPageSource());
        try{
            if (driver.findElementById( "com.fanli.android.apps:id/splash_img").isDisplayed()){
                driver.findElementByName("跳过").click();
            }
        }catch (Exception e){
        }
        Thread.sleep(500);
        for (int i=0;i<5;i++){
            driver.findElementByAndroidUIAutomator("text(\"9块9\")").click();
            Thread.sleep(200);
            driver.pressKeyCode(AndroidKeyCode.BACK);
            Thread.sleep(200);
        }
        System.out.println("九块九测试通过");
    }

    //超级返首页滑动
    @Test
    public void moretest2() throws Exception {
        //遇到splash，点击跳过
        try{
            if (driver.findElementById( "com.fanli.android.apps:id/content").isDisplayed()){
                driver.findElementByName("跳过").click();
            }
        }catch (Exception e) {
        }
        //如果弹出弹层，关闭弹层
        // try{
//            if (driver.findElementById( "com.fanli.android.apps:id/content").isDisplayed()){
//                driver.findElementByName("跳过").click();
//            }
//        }catch (Exception e) {
//        }
//
        //进入超级返滑动页面
        Thread.sleep(1000);
        driver.findElementByAndroidUIAutomator("text(\"超级返\")").click();
//      Assert.assertTrue(driver.findElementByName("限量秒杀").isDisplayed());
//      System.out.println(111);
        Thread.sleep(500);
        int width=driver.manage().window().getSize().width;
        int height=driver.manage().window().getSize().height;
        System.out.println(width);
        System.out.println(height);
        for(int i=0;i<=10;i++){
            Thread.sleep(200);
            for (int i1=0;i1<=10;i1++) {
                TouchAction action = new TouchAction(driver).press(width / 2, height * 5 / 7).waitAction((1000)).moveTo(width / 2, height * 2 / 7).release();
                action.perform();
            }
            Thread.sleep(200);
            for (int i2=0;i2<=5;i2++) {
                TouchAction action1 = new TouchAction(driver).press(width / 2, height * 2 / 7).waitAction((1000)).moveTo(width / 2, height * 5 / 7).release();
                action1.perform();
            }
//            driver.swipe(540,1486,540,657,2);
//            Thread.sleep(200);
//            driver.swipe(width/2,height*2/7,width/2,height*5/7,2);
//            Thread.sleep(200);
        }
        driver.pressKeyCode(AndroidKeyCode.BACK);
        Thread.sleep(100);

        System.out.println("品牌列表滑动通过");
    }

    //登录
    @Test
    public void moretest3() throws Exception{
        try{
            if (driver.findElementById( "com.fanli.android.apps:id/splash_img").isDisplayed()){
                driver.findElementByName("跳过").click();
            }
        }catch (Exception e){
        }
        Thread.sleep(500);
        driver.findElementByAndroidUIAutomator("text(\"我的\")").click();
        Thread.sleep(500);
        try{
            if (driver.findElementByAndroidUIAutomator("text(\"我的账户\")").isDisplayed()){
                System.out.println("已登录");
            }
        }catch (Exception e){
            if (driver.findElementByAndroidUIAutomator("text(\"登录\")").isDisplayed()) {
                WebElement username = driver.findElementById("com.fanli.android.apps:id/login_username");
                WebElement password = driver.findElementById("com.fanli.android.apps:id/login_password");
                username.sendKeys("15021127629");
                password.sendKeys("qwe123456");
                Thread.sleep(500);
                driver.pressKeyCode(66);
                Thread.sleep(1000);
                driver.findElementById("com.fanli.android.apps:id/btn_login").click();
                Thread.sleep(1000);
                try{
                    if (driver.findElementByAndroidUIAutomator("text(\"我的\")").isDisplayed()) {
                        System.out.println("登录成功");
                    }
                }catch (Exception i){
                    System.out.println("账号密码错误");
                }
            }
        }
    }

    //顶部搜索
    @Test
    public void moretest4() throws Exception {
        try{
            if (driver.findElementById( "com.fanli.android.apps:id/splash_img").isDisplayed()){
                driver.findElementByName("跳过").click();
            }
        }catch (Exception e){
        }
        Thread.sleep(1000);
        for (int i=0;i<5;i++) {
            driver.findElementById("com.fanli.android.apps:id/tv_search_hint").click();
            Thread.sleep(500);
            driver.findElementById("com.fanli.android.apps:id/et_search").sendKeys("蘑菇");
            Thread.sleep(500);
            driver.findElementById("com.fanli.android.apps:id/right_layout").click();
            //driver.findElementByName("搜索").click();
            Thread.sleep(1500);
            driver.pressKeyCode(4);
            Thread.sleep(500);
            driver.pressKeyCode(4);
            Thread.sleep(500);
//        TouchAction action=new TouchAction(driver);
//        action.tap(540,15).perform();
        }
    }

    private void resetApp() {
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
