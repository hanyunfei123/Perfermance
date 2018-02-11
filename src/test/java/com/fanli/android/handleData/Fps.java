package com.fanli.android.handleData;

public class Fps extends GetData {
    @Override
    public String command() {
        return null;
    }

    @Override
    public String parseInfo(String data) {
        return null;
    }
//    @Override
//    public String execCommand()throws IOException{
//        String command = null;
//        if (osName.equals("Mac OS X")){
//            command = "adb shell dumpsys gfxinfo com.fanli.android.apps reset | grep frames";
//        }else if(osName.indexOf("Windows")!= -1){
//            command = "adb shell \"dumpsys gfxinfo com.fanli.android.apps reset | grep frames\"";
//        }
//        String FPS = null;
//        Runtime runtime = Runtime.getRuntime();
//
//        Process proc = runtime.exec(command);
//        try {
//            if (proc.waitFor() != 0) {
//                System.err.println("exit value = " + proc.exitValue());
//            }
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    proc.getInputStream()));
//            StringBuffer stringBuffer = new StringBuffer();
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                stringBuffer.append(line+" ");
//            }
//            FPS = stringBuffer.toString();
//            System.out.println(FPS);
//        } catch (InterruptedException e) {
//            System.err.println(e);
//        }finally{
//            try {
//                proc.destroy();
//            } catch (Exception e1) {
//                System.err.println(e1);
//            }
//        }
//        return FPS;
//    }
}
