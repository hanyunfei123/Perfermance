package com.fanli.android.handleData;

import java.io.IOException;
import java.util.List;

public class Data{
    public static List<String> getData() throws IOException, InterruptedException {
        List<String> infos = new Memory().handleData();

        return infos;
    }
}
