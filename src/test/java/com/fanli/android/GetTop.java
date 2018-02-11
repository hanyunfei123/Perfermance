package com.fanli.android;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetTop {


    public static void main(String []args) throws IOException, InterruptedException {
        String command = null;
        if (System.getProperty("os.name").equals("Mac OS X")){
            command = "adb shell top -m 8 -n 1 -d 1";
        }else if(System.getProperty("os.name").indexOf("Windows")!=-1){
            command = "adb shell \"top -m 8 -n 1 -d 1\" ";
        }
        System.out.println("收集数据开始...");
        List<String> data = new ArrayList<String>();

        while (!Switch.cpuEnd){
            System.out.println("收集数据中...");
            String cpu=execCommand(command);
            Thread.sleep(4000);

            if(cpu!=null){
                data.add(cpu);
            }
        }

        System.out.println("收集数据结束");
        writeExcel(data);
    }

    public static String execCommand(String command) throws IOException {
        String top = null;
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
            while ((line = in.readLine())!=null) {
                if((line.indexOf("com.fanli.android.apps")!=-1)&&(line.indexOf("com.fanli.android.apps:push")==-1)){
                    stringBuffer.append(line+" ");
                }
            }
            String data=stringBuffer.toString();
            System.out.println(data);

            String reg="\\s+[0-9]+%\\s+";
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(data);
            if(m.find()){
                top = m.group().trim();
            }

        } catch (InterruptedException e) {
            System.err.println(e);
        }finally{
            try {
                proc.destroy();
            } catch (Exception e2) {
            }
        }
        return top;
    }

    public static void writeExcel(List<String> cpuMaps) throws IOException, InterruptedException{
        int size = cpuMaps.size();
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        String desktopPath = desktopDir.getAbsolutePath();
        String path = "/Users/Roger/Desktop/CPU-"+dateFormat.format(now)+".xls";
        File file = new File(path);
        FileOutputStream fOut = null;

        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("CPU ");

            // 行标
            int rowNum;
            // 列标
//            int colNum;

            HSSFRow row = sheet.createRow(0);
            // 单元格
            HSSFCell cell = null;
            row.createCell(0).setCellValue("CPU");

            for (rowNum=0; rowNum<size; rowNum++){
                row = sheet.createRow((short) rowNum+1);
                row.createCell(0).setCellValue(cpuMaps.get(rowNum));
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
