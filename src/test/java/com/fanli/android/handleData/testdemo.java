package com.fanli.android.handleData;

import java.io.IOException;

public class testdemo {
    public static void main(String[] args) throws IOException, InterruptedException {
//        int i=0;
//        while (i<20){
//            i++;
//            new Memory().writeExcel();
//            if(i==19){
//                Switch.memoryEnd = true;
//            }
//        }
//        new Memory().writeExcel();
//        new Cpu().writeExcel();
        new Fps().writeExcel();
    }

//    public static void main(String[] args) {
//        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
//        String desktopPath = desktopDir.getAbsolutePath();
//        System.out.println(desktopPath);
//    }
}
