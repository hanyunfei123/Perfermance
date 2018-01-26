package com.fanli.android;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class GetFps {

    public static void main(String []args) throws IOException, InterruptedException {
        String command = null;
        if (System.getProperty("os.name").equals("Mac OS X")){
            command = "adb shell dumpsys gfxinfo com.fanli.android.apps reset | grep frames";
        }else if(System.getProperty("os.name").indexOf("Windows")!= -1){
            command = "adb shell \"dumpsys gfxinfo com.fanli.android.apps reset | grep frames\"";
        }
        System.out.println("收集数据开始");

        List<Map> data = new ArrayList<Map>();

        while (!Test1.key){
            System.out.println("收集数据中...");
            String fps=execCommand(command);
            Thread.sleep(4000);

            String Total = fps.substring(fps.indexOf("rendered:")+10, fps.indexOf("Janky")-1);
            String Janky = fps.substring(fps.indexOf("Janky frames:")+14, fps.indexOf("(")-1);
            String percent = fps.substring(fps.indexOf("(")+1, fps.indexOf(")"));
            Map fpsMap = new HashMap();
            if (!Total.equals("0")){
                fpsMap.put("Total",Total);
                fpsMap.put("Janky", Janky);
                fpsMap.put("percent", percent);
            }
//            Map fpsMap = new HashMap();
//            fpsMap.put("Total",Total);
//            fpsMap.put("Janky", Janky);
//            fpsMap.put("percent", percent);
            data.add(fpsMap);
        }
        System.out.println("收集数据结束");
        writeExcel(data);
    }

    public static String execCommand(String command) throws IOException, InterruptedException {

        String FPS = null;
        Runtime runtime = Runtime.getRuntime();

        Process proc = runtime.exec(command);
        try {
            if (proc.waitFor() != 0) {
                System.err.println("exit value = " + proc.exitValue());
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line+" ");
            }
            FPS = stringBuffer.toString();
            System.out.println(FPS);
//            String Total = FPS.substring(FPS.indexOf("rendered:")+10, FPS.indexOf("Janky")-1);
//            String Janky = FPS.substring(FPS.indexOf("Janky frames:")+14, FPS.indexOf("(")-1);
//            String percent = FPS.substring(FPS.indexOf("(")+1, FPS.indexOf(")"));
//
//            Map fpsMap = new HashMap();
//            fpsMap.put("Total",Total);
//            fpsMap.put("Janky", Janky);
//            fpsMap.put("percent", percent);
//
//            maps.add(fpsMap);

        } catch (InterruptedException e) {
            System.err.println(e);
        }finally{
            try {
                proc.destroy();
            } catch (Exception e1) {
                System.err.println(e1);
            }
        }
//        String path = "/Users/Roger/Desktop/FPSData2.txt";
//        File file = new File(path);
//        if(file.exists()){
//            FileWriter fw = new FileWriter(file,false);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(FPS);
//            bw.close(); fw.close();
//            System.out.println("test1 done!");
//        }
        return FPS;
    }

    public static Map handleData(String fps){
        String Total = fps.substring(fps.indexOf("rendered:")+10, fps.indexOf("Janky")-1);
        String Janky = fps.substring(fps.indexOf("Janky frames:")+14, fps.indexOf("(")-1);
        String percent = fps.substring(fps.indexOf("(")+1, fps.indexOf(")"));

        Map fpsMap = new HashMap();
        fpsMap.put("Total",Total);
        fpsMap.put("Janky", Janky);
        fpsMap.put("percent", percent);

        return fpsMap;
    }

    public static void writeExcel(List<Map> fpsMaps) throws IOException, InterruptedException{
        int size = fpsMaps.size();

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String path = "/Users/Roger/Desktop/"+"FPS-"+dateFormat.format(now)+".xls";
        File file = new File(path);
        FileOutputStream fOut = null;

        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FPS");

            // 行标
            int rowNum;
            // 列标
//            int colNum;

            HSSFRow row = sheet.createRow(0);
            // 单元格
            HSSFCell cell = null;
            String[] title = {"Total frames rendered", "Janky frames", "percent"};
            for(int i=0;i<title.length;i++){
                cell = row.createCell(i);
                cell.setCellValue(title[i]);
            }

            for (rowNum=0; rowNum<size; rowNum++){
                row = sheet.createRow((short) rowNum+1);
                cell = row.createCell(0);
                cell.setCellValue(fpsMaps.get(rowNum).get("Total").toString());
                cell = row.createCell(1);
                cell.setCellValue(fpsMaps.get(rowNum).get("Janky").toString());
                cell = row.createCell(2);
                cell.setCellValue(fpsMaps.get(rowNum).get("percent").toString());

            }

            // 新建一输出文件流
            fOut = new FileOutputStream(file);
            // 把相应的Excel 工作簿存盘
            workbook.write(fOut);
            fOut.flush();
            // 操作结束，关闭文件
            fOut.close();

            System.out.println("Excel文件生成成功！Excel文件名：" + file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Excel文件" + file.getAbsolutePath() + "生成失败：" + e);
        } finally {
            if (fOut != null) {
                try {
                    fOut.close();
                } catch (IOException e1) {
                }
            }
        }

    }

}
