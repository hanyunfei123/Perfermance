package com.fanli.android;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetMemory {

    public static void main(String []args) throws IOException, InterruptedException {
        for (int i =0;i<20;i++) {
            System.out.println(execCommand("com.fanli.android.apps"));
        }
    }

    public static String execCommand(String packageName) throws IOException, InterruptedException {

        String str3=null;
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec("adb shell dumpsys meminfo "+packageName);
        // Thread.sleep(10000);
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

            String str2=str1.substring(str1.indexOf("Objects")-60,str1.indexOf("Objects"));

            str3=str2.substring(0,10)+"k";


            //exit

//            Runtime rt = java.lang.Runtime.getRuntime();
//            String name = "cmd /c md c:\\Users\\yu.li exit" ;
//            Process process = rt.exec(name) ;


        } catch (InterruptedException e) {
            System.err.println(e);
        }finally{
            try {
                proc.destroy();
            } catch (Exception e2) {
            }
        }
        return str3 ;
    }

}
