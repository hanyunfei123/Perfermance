package com.fanli.android;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetMemory {

    public static void main(String []args) throws IOException, InterruptedException {
        for (int i =0;i<2;i++) {
            System.out.println(execCommand("com.fanli.android.apps"));
        }
    }

    public static String execCommand(String packageName) throws IOException, InterruptedException {

        String memory=null;
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
            String data=stringBuffer.toString().trim();
            System.out.println(data);
            String reg="\\s+[^\\s]+\\s+";
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(data);
            if(m.find()){
                memory = m.group(0).trim();
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }finally{
            try {
                proc.destroy();
            } catch (Exception e2) {
            }
        }
        return memory ;
    }

}
