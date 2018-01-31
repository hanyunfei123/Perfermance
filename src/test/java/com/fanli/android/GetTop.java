package com.fanli.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetTop {


    public static void main(String []args) throws IOException
    {
        for(int i = 0;i<1;i++)
        {
            execCommand();
//            System.out.println(execCommand());

        }
    }

    public static String execCommand() throws IOException {
        String str3 = null;
        String command = null;
        Runtime runtime = Runtime.getRuntime();
        if (System.getProperty("os.name").equals("Mac OS X")){
//            command = "adb shell top -m 8 -n 1 -d 1 | grep com.fanli.android.apps";
            command = "adb shell top -m 8 -n 1 -d 1";
        }else if(System.getProperty("os.name").indexOf("Windows")!=-1){
            command = "adb shell \"top -m 8 -n 1 -d 1\" ";
        }
        System.out.println(command);
        Process proc = runtime.exec(command);
        try {
            if (proc.waitFor() != 0) {
                System.err.println("exit value = " + proc.exitValue());
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = in.readLine())!=null) {
                if(line.indexOf("com.fanli.android.apps")!=-1){
                    stringBuffer.append(line+" ");
                }
            }
            String str1=stringBuffer.toString();
            System.out.println(3);
            System.out.println(str1);
//            System.out.println(str1.indexOf("fg"));
//            str3=str1.substring(str1.indexOf("fg")-23,str1.indexOf("fg"));

        } catch (InterruptedException e) {
            System.err.println(e);
        }finally{
            try {
                proc.destroy();
            } catch (Exception e2) {
            }
        }

        return str3+"%";
    }
}
