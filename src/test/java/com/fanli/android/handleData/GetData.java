package com.fanli.android.handleData;

import com.fanli.android.Switch;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class GetData implements WriteExcel{

    public static String osName = System.getProperty("os.name");

    public abstract String command();

    //处理cmd命令行获取的数据
    public abstract String handleCmd(String data);

    public abstract List<String> handleData();
//            System.out.println("收集数据开始...");
//            List<String> data = new ArrayList<String>();
//            while (!Switch.memoryEnd){
//                System.out.println("收集数据中...");
//                String memory=execCommand(command());
//                Thread.sleep(4000);
//                if(memory!=null){
//                    data.add(memory);
//                }
//            }
//            return data;

    public String execCommand(String command) throws IOException{
        String data = null;
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
                stringBuffer.append(line+" ");
            }
            String str=stringBuffer.toString();
            System.out.println(str);
            data = handleCmd(str);

        } catch (InterruptedException e) {
            System.err.println(e);
        }finally{
            try {
                proc.destroy();
            } catch (Exception e1) {
                System.err.print(e1);
            }
        }
        return data;
    }

    public void toExcel(List<String> dataMaps, String dataType) {
        int size = dataMaps.size();
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        String desktopPath = desktopDir.getAbsolutePath();
        String path = "/Users/Roger/Desktop/"+dataType+"-"+dateFormat.format(now)+".xls";
        File file = new File(path);
        FileOutputStream fOut = null;

        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(dataType);

            // 行标
            int rowNum;
            // 列标
//            int colNum;

            HSSFRow row = sheet.createRow(0);
            // 单元格
            HSSFCell cell = null;
            row.createCell(0).setCellValue(dataType);

            for (rowNum=0; rowNum<size; rowNum++){
                row = sheet.createRow((short) rowNum+1);
                row.createCell(0).setCellValue(dataMaps.get(rowNum));
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

    @Override
    public void writeExcel() throws IOException, InterruptedException {
        toExcel(handleData(),"app");
    }
}
