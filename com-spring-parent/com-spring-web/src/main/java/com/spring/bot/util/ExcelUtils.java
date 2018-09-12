package com.spring.bot.util;

import com.spring.bot.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * excel表格工具
 *
 * 依赖信息
 <dependency>
 <groupId>org.apache.poi</groupId>
 <artifactId>poi</artifactId>
 <version>3.10.1</version>
 </dependency>
 <dependency>
 <groupId>org.apache.poi</groupId>
 <artifactId>poi-ooxml</artifactId>
 <version>3.10.1</version>
 </dependency>
 *
 */
@Slf4j
public class ExcelUtils {

    /**
     * @param labels             第一行标签栏
     * @param cellPropertiesName 表格单元格值名称，以这个序列把相应实体类属性值插入到单元格
     * @param content            需要转换为表格的内容
     * @return
     */
    public static Workbook generateWorkbook(String[] labels, String[] cellPropertiesName, List content) throws Exception {
        if (content == null || content.size() == 0) {
            throw new Exception("content is null");
        }
        if (labels == null || labels.length == 0) {
            throw new Exception(("labels  is null"));
        }
        if (cellPropertiesName == null || cellPropertiesName.length == 0) {
            throw new Exception(("cepropertiesName is null"));
        }
        if (cellPropertiesName.length != labels.length) {
            throw new Exception(("cepropertiesName ,labels must equal"));
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        sheet.setDefaultColumnWidth(18);
        //创建标题栏
        CellStyle labelStyle = workbook.createCellStyle();
        labelStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        labelStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        labelStyle.setAlignment(CellStyle.ALIGN_CENTER);
        labelStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        Row labelRow = sheet.createRow(0);
        Cell labelFirstCell=labelRow.createCell(0);
        labelFirstCell.setCellValue("序号");
        labelFirstCell.setCellStyle(labelStyle);
        for (int i = 0; i < labels.length; i++) {
            Cell cell=labelRow.createCell(i+1);
            cell.setCellValue(labels[i]);
            cell.setCellStyle(labelStyle);
        }

        //创建表格内容
        CellStyle contentCellStyle=workbook.createCellStyle();
        contentCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        contentCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        for(int i=0;i<content.size();i++){
            Row contentRow = sheet.createRow(i+1);
            Cell cellContent_1=contentRow.createCell(0);
            cellContent_1.setCellValue(i+1);
            cellContent_1.setCellStyle(contentCellStyle);
            Object obj=content.get(i);
            //根据单元格获取对象值，插入相应的表格
            for (int j = 0; j < cellPropertiesName.length; j++) {
                String fieldName=cellPropertiesName[j];
                String value="";
                try {
                    value= (String) getFieldValue(fieldName,obj);
                }catch (Exception e){
                    log.debug("[{}] get field[{}] value error",obj.getClass().getName(),fieldName);
                }
                Cell cellContent=contentRow.createCell(j+1);
                cellContent.setCellValue(value);
                cellContent.setCellStyle(contentCellStyle);
            }
        }
        return workbook;
    }

    /**
     * 获取对象属性值
     * @param fieldName
     * @param object
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> Object getFieldValue(String fieldName, T object) throws Exception {
        Class classzz = object.getClass();
        Field field = classzz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }


    public static void main(String args[]) throws Exception {
        // 下载到当前项目的根目录
        UserInfo userInfo = new UserInfo();
        userInfo.setName("zs");
        userInfo.setMobile("186");
        List<UserInfo> list=new ArrayList<>();
        list.add(userInfo);
        String labels[]={"姓名","手机号码"};
        String cell[]={"name","mobile"};
        Workbook workbook=generateWorkbook(labels,cell,list);
        FileOutputStream out=new FileOutputStream("古墓遗书.xlsx");
        workbook.write(out);
        out.close();

        // web页面下载
        /*@RequestMapping(value = "excel")
        public void excel(HttpServletResponse response) throws Exception{
            UserInfo userInfo = new UserInfo();
            userInfo.setName("zs");
            userInfo.setMobile("186");
            List<UserInfo> list=new ArrayList<>();
            list.add(userInfo);
            String labels[]={"姓名","手机号码"};
            String cell[]={"name","mobile"};

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + new Date().getTime() +"_contract"+".xlsx");
            Workbook workbook1= ExcelUtils.generateWorkbook(labels,cell,list);
            OutputStream outputStream=response.getOutputStream();
            workbook1.write(outputStream);
            if(outputStream!=null){
                outputStream.close();
            }
        }*/
    }
}
