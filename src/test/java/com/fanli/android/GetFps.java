package com.fanli.android;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.hssf.usermodel.*;

public class GetFps {

    public static void main(String []args) throws IOException, InterruptedException {
        List<Map> maps=new ArrayList<Map>();
        for(int i=0;i<100;i++){
            String fps = execCommand();
            maps.add(handleData(fps));
        }

        writeExcel(maps);
    }

    public static String execCommand() throws IOException, InterruptedException {

        String FPS = null;
        String command = null;
        Runtime runtime = Runtime.getRuntime();

        if (System.getProperty("os.name").equals("Mac OS X")){
            command = "adb shell dumpsys gfxinfo com.fanli.android.apps reset | grep frames";
        }else if(System.getProperty("os.name").equals("Windows")){
            command = "adb shell \"dumpsys gfxinfo com.fanli.android.apps reset | grep frames\"";
        }

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

        } catch (InterruptedException e) {
            System.err.println(e);
        }finally{
            try {
                proc.destroy();
            } catch (Exception e1) {
                System.err.println(e1);
            }
        }
        return FPS ;
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
