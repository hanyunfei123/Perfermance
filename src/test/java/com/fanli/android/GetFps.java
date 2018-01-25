package com.fanli.android;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.hssf.usermodel.*;

public class GetFps {

    public static void main(String []args) throws IOException, InterruptedException {
//        String fps = execCommand();
//        System.out.println(fps);
//        System.out.println();
//        handleData(fps);
        saveToExcel();
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

    public static List<Map> handleData(String fps){
        String str = "Total frames rendered: 20 Janky frames: 10 (333%)";
        String Total1 = str.substring(str.indexOf("rendered:")+10, str.indexOf("Janky")-1);
        String Janky1 = str.substring(str.indexOf("Janky frames:")+14, str.indexOf("(")-1);
        String percent1 = str.substring(str.indexOf("(")+1, str.indexOf(")"));
        Map map1 = new HashMap();
        map1.put("Total",Total1);
        map1.put("Janky", Janky1);
        map1.put("percent", percent1);

        String Total = fps.substring(fps.indexOf("rendered:")+10, fps.indexOf("Janky")-1);
        String Janky = fps.substring(fps.indexOf("Janky frames:")+14, fps.indexOf("(")-1);
        String percent = fps.substring(fps.indexOf("(")+1, fps.indexOf(")"));

        Map map = new HashMap();
        map.put("Total",Total);
        map.put("Janky", Janky);
        map.put("percent", percent);

        List<Map> maps=new ArrayList<Map>();
        maps.add(map);
        maps.add(map1);
        return maps;
    }

    public static void saveToExcel() throws IOException, InterruptedException{
        String fps = execCommand();
        System.out.println(fps);
        System.out.println();
        List<Map> fps1 = handleData(fps);
        int size = fps1.size();
        System.out.println(size);

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String path = "/Users/Roger/Desktop/"+"FPS-"+dateFormat.format(now)+".xls";
        File file = new File(path);
        FileOutputStream fOut = null;

        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Test_Table");

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
                System.out.println(fps1.get(rowNum));
                row = sheet.createRow((short) rowNum+1);
                cell = row.createCell(0);
                cell.setCellValue(fps1.get(rowNum).get("Total").toString());
                cell = row.createCell(1);
                cell.setCellValue(fps1.get(rowNum).get("Janky").toString());
                cell = row.createCell(2);
                cell.setCellValue(fps1.get(rowNum).get("percent").toString());

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
