package com.fanli.android.handleData;

import com.fanli.android.Switch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Memory extends GetData{

    @Override
    public String handleCmd(String result) {
        System.out.println(result);
        String memory = null;
        String reg="\\s+[^\\s]+\\s+";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(result);
        if(m.find()){
            memory = m.group(0).trim();
        }
        System.out.println(memory);
        return memory;
    }

    @Override
    public List<String> handleData() throws IOException {
        String command = null;
        if (osName.equals("Mac OS X")){
            command = "adb shell dumpsys meminfo com.fanli.android.apps |grep TOTAL";
        }else if(osName.indexOf("Windows")!=-1){
            command = "adb shell \"dumpsys meminfo com.fanli.android.apps |grep TOTAL\"";
        }
        System.out.println("Memory收集数据开始...");
        List<String> data = new ArrayList<String>();
        while (!Switch.memoryEnd){
            System.out.println("Memory收集数据中...");
            String memory=execCommand(command);
            if(memory!=null){
                data.add(memory);
            }
        }
        System.out.println("Memory收集数据完成...");
        return data;
    }
}
