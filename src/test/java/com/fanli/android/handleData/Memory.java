package com.fanli.android.handleData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Memory extends GetData{
    @Override
    public String execCommand() throws IOException{

        String command = null;
        if (osName.equals("Mac OS X")){
            command = "adb shell dumpsys meminfo com.fanli.android.apps |grep TOTAL";
        }else if(osName.indexOf("Windows")!=-1){
            command = "adb shell \"dumpsys meminfo com.fanli.android.apps |grep TOTAL\"";
        }

        String memory=null;
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
