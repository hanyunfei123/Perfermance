package com.fanli.android.handleData;

import com.fanli.android.Switch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
    public String handleCmd(String data) {
            String str = null;
            String reg="\\s+[0-9]+%\\s+";
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(data);
            if(m.find()){
                str = m.group().trim();
            }
        return str;
    }

    @Override
    public List<String> handleData() {
        System.out.println("收集数据开始...");
        List<String> data = new ArrayList<String>();
        while (!Switch.memoryEnd){
            System.out.println("收集数据中...");
            String memory=execCommand(command());
            if(memory!=null){
                data.add(memory);
            }
        }
            return data;
    }

//    @Override
//    public void writeExcel() throws IOException, InterruptedException {
//        toExcel(new Memory().handleData(), "Memory");
//    }
}
