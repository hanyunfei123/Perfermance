package com.fanli.android;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.lang.reflect.Field;

public class Switch {
    volatile public static boolean fpsStart = false;
    volatile public static boolean fpsEnd = false;

    volatile public static boolean cpuStart = false;
    volatile public static boolean cpuEnd = false;

    volatile public static boolean memoryStart = false;
    volatile public static boolean memoryEnd = false;

}
