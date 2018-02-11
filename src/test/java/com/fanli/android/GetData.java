package com.fanli.android;

import java.io.IOException;

public abstract class GetData {

    public static String osName = System.getProperty("os.name");

    public void toExcel(String data){};

    public abstract String execCommand() throws IOException;

    public void writeToExcel(String data)throws IOException{
        toExcel(execCommand());
    };
}
