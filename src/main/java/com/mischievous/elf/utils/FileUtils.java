package com.mischievous.elf.utils;

import com.mischievous.elf.constant.FileConstant;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lifang
 */
public class FileUtils {

    /**
     * 读固定前n行数据
     *
     * @param fileName  文件全路径
     * @param lineCount 文件行数
     * @return
     */
    public static List<String> readFileContent(String fileName, int lineCount) {
        File file = null;
        BufferedReader reader = null;
        List<String> fileContent = new ArrayList<String>();

        try {
            file = new File(fileName);
            if (!file.exists()) {
                return fileContent;
            }
            lineCount = lineCount > 0 ? lineCount:1;

            reader = new BufferedReader(new FileReader(file));
            int i = 0;
            String oneLine = null;
            while (i < lineCount && (oneLine = reader.readLine()) != null) {
                fileContent.add(new String(oneLine));
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return fileContent;
    }

    /**
     * 读固定第n行数据
     *
     * @param fileName  文件全路径
     * @param lineNum   文件行数
     * @return
     */
    public static String readFileOneLine(String fileName, int lineNum) {
        File file = null;
        BufferedReader reader = null;
        String fileContent = null;

        try {
            file = new File(fileName);
            if (!file.exists()) {
                return null;
            }
            lineNum = lineNum >= 0 ? lineNum:0;

            reader = new BufferedReader(new FileReader(file));
            int i = 0;
            String oneLine = null;
            while (i <= lineNum && (oneLine = reader.readLine()) != null) {
                i++;
            }
            i--;
            if (i < lineNum) {
                return null;
            }
            fileContent = oneLine;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileContent;
    }

    /**
     * 读全文件
     *
     * @param fileName  文件全路径
     * @return
     */
    public static List<String> readFileAll(String fileName) {
        File file = null;
        BufferedReader reader = null;
        List<String> fileContent = new ArrayList<String>();

        try {
            file = new File(fileName);
            if (!file.exists()) {
                return fileContent;
            }

            reader = new BufferedReader(new FileReader(file));
            String oneLine = null;
            while ((oneLine = reader.readLine()) != null) {
                fileContent.add(new String(oneLine));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return fileContent;
    }

    /**
     * 写文件
     *
     * @param fileName      文件全路径
     * @param fileContent   文件内容
     * @param append        是否尾部追加
     */
    public static void writeFileContent(String fileName, String fileContent, boolean append) {
        File file = null;
        FileWriter writer = null;

        try {
            file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fop = new FileOutputStream(file, append);
            byte[] contentInBytes = fileContent.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入excel
     *
     * @param fileName      文件全路径
     * @param sheetName     sheet页
     * @param fileContent   文件内容
     * @throws IOException
     */
    public static void writeExcelContent(String fileName, String sheetName, List<String> fileContent) throws IOException {
        File file = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            file = new File(fileName);
            if (!file.exists()) {
                return;
            }
            fis = new FileInputStream(file);

            String suffix = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
            if (FileConstant.SUFFIX_XLS.equals(suffix)) {
                workbook = new HSSFWorkbook(fis);
            } else if (FileConstant.SUFFIX_XLSX.equals(suffix)) {
                workbook = new XSSFWorkbook(fis);
            } else {
                return;
            }

            Sheet sheet = workbook.getSheet(sheetName);
            if (null == sheet) {
                sheet = workbook.createSheet(sheetName);
            }
            int i=sheet.getLastRowNum()+1;
            for (String c : fileContent) {
                Row row = sheet.createRow(i);
                String[] cellStrs = c.split(",");
                int cellLength = cellStrs.length;
                for (int j=0; j<cellLength; j++) {
                    Cell cell = row.getCell(j);
                    if (null == cell) {
                        cell = row.createCell(j);
                    }
                    cell.setCellValue(cellStrs[j]);
                }
                i++;
            }
            fos = new FileOutputStream(fileName);
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != fis) {
                    fis.close();
                }
                if (null != fos) {
                    fos.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
    }

}
