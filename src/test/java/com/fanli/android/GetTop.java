package com.fanli.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetTop {


    public static void main(String []args) throws IOException
    {
        for(int i = 0;i<1;i++)
        {
            System.out.println(execCommand());

        }
    }

    public static String execCommand() throws IOException {
        String top = null;
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
            String data=stringBuffer.toString();
            System.out.println(data);

            String reg="\\s+[0-9]+%\\s+";
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(data);
            if(m.find()){
                top = m.group().trim();
            }

        } catch (InterruptedException e) {
            System.err.println(e);
        }finally{
            try {
                proc.destroy();
            } catch (Exception e2) {
            }
        }
        return top;
    }
}
