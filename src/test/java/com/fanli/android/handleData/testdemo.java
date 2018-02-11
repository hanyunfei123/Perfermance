package com.fanli.android.handleData;

import com.fanli.android.Switch;

import java.io.IOException;

public class testdemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        int i=0;
        while (i<20){
            i++;
            new Memory().writeExcel();
            if(i==19){
                Switch.memoryEnd = true;
            }
        }
    }
}
