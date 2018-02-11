package com.fanli.android.handleData;

public class Cpu extends GetData {
    @Override
    public String command() {
        return null;
    }

    @Override
    public String parseInfo(String data) {
        return null;
    }

//    @Override
//    public String execCommand() throws IOException{
//        String command = null;
//        if (osName.equals("Mac OS X")){
//            command = "adb shell top -m 8 -n 1 -d 1";
//        }else if(osName.indexOf("Windows")!=-1){
//            command = "adb shell \"top -m 8 -n 1 -d 1\" ";
//        }
//        String cpu = null;
//        Runtime runtime = Runtime.getRuntime();
//        Process proc = runtime.exec(command);
//        try {
//            if (proc.waitFor() != 0) {
//                System.err.println("exit value = " + proc.exitValue());
//            }
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    proc.getInputStream()));
//            StringBuffer stringBuffer = new StringBuffer();
//            String line = null;
//            while ((line = in.readLine())!=null) {
//                if((line.indexOf("com.fanli.android.apps")!=-1)&&(line.indexOf("com.fanli.android.apps:push")==-1)){
//                    stringBuffer.append(line+" ");
//                }
//            }
//            String data=stringBuffer.toString();
//            System.out.println(data);
//
//            String reg="\\s+[0-9]+%\\s+";
//            Pattern p = Pattern.compile(reg);
//            Matcher m = p.matcher(data);
//            if(m.find()){
//                cpu = m.group().trim();
//            }
//
//        } catch (InterruptedException e) {
//            System.err.println(e);
//        }finally{
//            try {
//                proc.destroy();
//            } catch (Exception e2) {
//            }
//        }
//        return cpu;
//    }
}
