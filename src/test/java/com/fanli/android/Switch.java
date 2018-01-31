package com.fanli.android;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class Switch {
    volatile public static boolean start = false;
    volatile public static boolean end = false;


    public static void main(String[] args) {
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        String desktopPath = desktopDir.getAbsolutePath();
        System.out.println(desktopPath);
    }
}
