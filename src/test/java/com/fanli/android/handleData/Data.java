package com.fanli.android.handleData;

import java.util.ArrayList;
import java.util.List;

public class Data{
    public List<GetData> fpsMaps(){
        List<GetData> infos = new ArrayList<GetData>();
        infos.add(new Cpu());
        return infos;
    }
}
