package com.fanli.android.handleData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class GetCpu {


    public static void main(String []args) throws IOException
    {
        for(int i = 0;i<1000;i++)
        {

            System.out.println(execCommand());

        }
    }


    public static String execCommand() throws IOException {
        String str3=null;
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec("adb shell dumpsys cpuinfo  $com.fanli.android.apps");
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

            String str2=str1.substring(str1.indexOf("com.fanli.android.apps"),str1.indexOf("com.fanli.android.apps")+28);
            str3=str2.substring(23,27);

        } catch (InterruptedException e) {
            System.err.println(e);
        }finally{
            try {
                proc.destroy();
            } catch (Exception e2) {
            }
        }
        if(str3.contains("%"))
        {

            return str3 ;
        }
        else
        {

            return str3+"%";

        }
    }

}

