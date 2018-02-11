package com.fanli.android.handleData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Memory extends GetData{

    @Override
    public String command() {
        String command = null;
        if (osName.equals("Mac OS X")){
            command = "adb shell top -m 8 -n 1 -d 1";
        }else if(osName.indexOf("Windows")!=-1){
            command = "adb shell \"top -m 8 -n 1 -d 1\" ";
        }
        return command;
    }

    @Override
    public String execCommand(String command) throws IOException {
        String memory = null;
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
            while ((line = in.readLine())!=null) {
                if((line.indexOf("com.fanli.android.apps")!=-1)&&(line.indexOf("com.fanli.android.apps:push")==-1)){
                    stringBuffer.append(line+" ");
                }
            }
            String str=stringBuffer.toString();
            System.out.println(str);

            String reg="\\s+[0-9]+%\\s+";
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(str);
            if(m.find()){
                memory = m.group().trim();
            }

        } catch (InterruptedException e) {
            System.err.println(e);
        }finally{
            try {
                proc.destroy();
            } catch (Exception e2) {
            }
        }
        return memory;
    }

    @Override
    public String parseInfo(String data) {
            String str = null;
            String reg="\\s+[0-9]+%\\s+";
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(data);
            if(m.find()){
                str = m.group().trim();
            }
        return str;
    }
}
