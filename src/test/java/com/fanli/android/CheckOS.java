package com.fanli.android;


public class CheckOS {
    public static void main(String []args) {
        System.out.println(System.getProperty("os.name"));
        if (System.getProperty("os.name").equals("Linux")) {
            System.out.println("linux");
        } else if (System.getProperty("os.name").equals("Windows XP")) {
            System.out.println("windows");
        }
    }
}
