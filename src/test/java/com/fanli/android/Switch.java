package com.fanli.android;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class Switch {
    volatile public static boolean fpsStart = false;
    volatile public static boolean fpsEnd = false;

    volatile public static boolean cpuStart = false;
    volatile public static boolean cpuEnd = false;

    volatile public static boolean memoryStart = false;
    volatile public static boolean memoryEnd = false;

    public static void main(String[] args) {
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        String desktopPath = desktopDir.getAbsolutePath();
        System.out.println(desktopPath);
    }
}
