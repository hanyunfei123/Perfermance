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
        String command = null;
        Runtime runtime = Runtime.getRuntime();
        if (System.getProperty("os.name").equals("Mac OS X")){
            command = "adb shell dumpsys meminfo com.fanli.android.apps |grep TOTAL";
        }else if(System.getProperty("os.name").indexOf("Windows")!=-1){
            command = "adb shell \"dumpsys meminfo com.fanli.android.apps |grep TOTAL\"";
        }
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

            String str2=str1.substring(str1.indexOf("TOTAL")-60,str1.indexOf("Objects"));

//            str3=str2.substring(0,10)+"k";


            //exit

//            Runtime rt = java.lang.Runtime.getRuntime();
//            String name = "cmd /c md c:\\Users\\ye.liu exit" ;
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
