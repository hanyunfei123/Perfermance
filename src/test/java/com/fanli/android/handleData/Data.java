package com.fanli.android.handleData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data{
    public static List<String> getData() throws IndexOutOfBoundsException, IOException {
        List<String> infos = new ArrayList<String>();
        infos.add(new Memory().handleData());
        return infos;
    }
}
