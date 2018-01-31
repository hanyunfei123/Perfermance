package com.fanli.android;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class GetCpu {


    public static void main(String []args) throws InterruptedException,IOException {
        for(int i = 0;i<2;i++)
        {
            System.out.println(execCommand());
        }
    }


    public static String execCommand() throws InterruptedException, IOException {
        String cpu=null;
        String command = null;
        if (System.getProperty("os.name").equals("Mac OS X")){
            command = "adb shell top -m 8 -n 5 -d 1 -n 1000";
        }else if(System.getProperty("os.name").indexOf("Windows")!= -1){
            command = "adb shell \"top -m 8 -n 1 -d 1\"";
        }
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec(command);

        try {
            if (proc.waitFor() != 0) {
                System.err.println("exit value = " + proc.exitValue());
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line+" ");
            }
            String str1=stringBuffer.toString();
            System.out.println(str1);


        } catch (InterruptedException e) {
            System.err.println(e);
        }finally{
            System.out.println(3);
            try {
                System.out.println(55);
                proc.destroy();
            } catch (Exception e2) {
                System.err.println(e2);
            }
        }
        if(cpu.contains("%"))
        {
            return cpu ;
        }
        else
        {
            return cpu+"%";
        }
    }

}
