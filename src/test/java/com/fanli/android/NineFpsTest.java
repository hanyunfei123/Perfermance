package com.fanli.android;

import com.fanli.android.handleData.Fps;
import io.appium.java_client.TouchAction;
import org.testng.annotations.Test;

import java.io.IOException;

public class NineFpsTest extends Action{

    @Test
    public void fpsMonitor() throws IOException, InterruptedException{
        while (!Switch.fpsStart){
            System.out.println("waiting");
        }
        new Fps().writeExcel("9k9-FPS");
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
}