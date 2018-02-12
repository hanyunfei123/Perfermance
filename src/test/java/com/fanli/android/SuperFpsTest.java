package com.fanli.android;

import com.fanli.android.handleData.Cpu;
import io.appium.java_client.android.AndroidKeyCode;
import org.testng.annotations.Test;

import java.io.IOException;

public class SuperFpsTest extends Action{

    @Test
    public void cpuMonitor() throws IOException, InterruptedException{
        while (!Switch.cpuStart){
            System.out.println("waiting");
        }
        new Cpu().writeExcel("super-Cpu");
    }

    @Test
    public void SuperTest() throws InterruptedException,IOException {
        Thread.sleep(8000);
        Switch.cpuStart=true;
        for (int i=0;i<5;i++){
            driver.findElementByAndroidUIAutomator("text(\"超级返\")").click();
            Thread.sleep(200);
            driver.pressKeyCode(AndroidKeyCode.BACK);
            Thread.sleep(100);
        }
        Switch.cpuEnd=true;
    }
}