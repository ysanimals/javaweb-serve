package com.nit.ssm.utils;

import com.nit.ssm.dto.OpResultDTO;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.poi.ss.usermodel.CellType.STRING;


@RestController
@RequestMapping(value = "/api/data")
public class ImportExport {

    private final Logger logger = LoggerFactory.getLogger(ImportExport.class);

    /**
     * @Description: 导入模板中的数据，并返回JSON字符串，该方法主要上传Excel模板的版本要与POI版本对应
     * @Author: SN
     * @Date: 2019/04/01 18:15
     */
    @ResponseBody
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public OpResultDTO importData(@RequestParam("excelData") MultipartFile[] excelData) {
        OpResultDTO opResult = new OpResultDTO();
        try {
            MultipartFile file = excelData[0];
            //把文件读入
            InputStream inputStream = file.getInputStream();
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            // 取得上传的文件，创建对Excel工作薄文件的引用
            HSSFWorkbook workbook = new HSSFWorkbook(fs);
            // 建立新的sheet对象
            HSSFSheet sheet = workbook.getSheet("sheet1");
            // 获取Excel的所有行
            int rows = sheet.getPhysicalNumberOfRows();
            String importData = "";
            // 遍历行
            for (int i = 0; i < rows; i++) {
                // 读取左上角单元格
                HSSFRow row = sheet.getRow(i);
                // 行不能为空
                if (row != null) {
                    // 获取Excel文件中的所有列
                    int cells = row.getPhysicalNumberOfCells();
                    // 遍历列
                    for (int j = 0; j < cells; j++) {
                        // 获取列的值
                        HSSFCell cell = row.getCell(j);
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case NUMERIC:
                                    importData += cell.getNumericCellValue();
                                    break;
                                case STRING:
                                    importData += cell.getStringCellValue() + ",";
                                    break;
                                case BOOLEAN:
                                    break;
                                case FORMULA:
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
            // 应该将模板中的数据解析，然后插入到数据库，这里只是返回一个字符串
            importData = importData.substring(0, importData.length() - 1);
            String[] tempValue = importData.split(",");
            opResult.setIntResult(tempValue.length);
            opResult.setObjResult(tempValue);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return opResult;
    }

    /**
     * @Description: 从数据库中获取数据导出到EXCEL
     * @Author: SN
     * @Date: 2019/04/01 18:15
     */
    @ResponseBody
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void exportData() {
        //在SpringMVC中获取Response
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        // 输出流的字符编码处理
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=demo.xls");
        try {
            OutputStream os = response.getOutputStream();
            // 创建Excel工作薄
            HSSFWorkbook book = new HSSFWorkbook();
            // 在Excel工作薄中建一张工作表
            HSSFSheet sheet = book.createSheet("员工信息");
            // 设置单元格格式(文本)
            // HSSFCellStyle cellStyle = book.createCellStyle();
            // 第一行为标题行
            HSSFRow row = sheet.createRow(0);// 创建第一行
            HSSFCell cell0 = row.createCell(0);
            HSSFCell cell1 = row.createCell(1);
            HSSFCell cell2 = row.createCell(2);
            HSSFCell cell3 = row.createCell(3);
            HSSFCell cell4 = row.createCell(4);
            // 定义单元格为字符串类型
            cell0.setCellType(STRING);
            cell1.setCellType(STRING);
            cell2.setCellType(STRING);
            cell3.setCellType(STRING);
            cell4.setCellType(STRING);
            // 在单元格中输入数据
            cell0.setCellValue("员工编号");
            cell1.setCellValue("员工姓名");
            cell2.setCellValue("员工性别");
            cell3.setCellValue("出生日期");
            cell4.setCellValue("身份证号");
            // 循环导出数据到excel中
            for (int i = 0; i < 100; i++) {
                // 创建第i行
                HSSFRow rowi = sheet.createRow(i + 1);
                // 在第i行的相应列中加入相应的数据
                rowi.createCell(0).setCellValue("10000" + i);
                rowi.createCell(1).setCellValue("User" + i);
                // 处理性别(M:男 F:女)
                String sex = null;
                if (i % 2 == 0) {
                    sex = "男";
                } else {
                    sex = "女";
                }
                rowi.createCell(2).setCellValue(sex);
                // 对日期的处理
                java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                rowi.createCell(3).setCellValue(df.format(new Date()));
                rowi.createCell(4).setCellValue("360212X#$%^&*()@!" + i);
            }
            // 写入数据，把相应的EXCEL工作簿存盘
            book.write(os);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }
}
