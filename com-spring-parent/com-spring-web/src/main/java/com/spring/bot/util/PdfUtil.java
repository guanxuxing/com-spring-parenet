package com.spring.bot.util;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Slf4j
public class PdfUtil {

    public static void main(String[] args){
        PdfUtil pdfUtil = new PdfUtil();
        pdfUtil.generateDoc();
    }

    public File generateDoc(){
        File file = null;
        PdfReader reader = null; // 模版文件目录
        PdfStamper ps = null; // 生成的输出流
        String destPath = System.getProperty("java.io.tmpdir") + "/" + UUID.randomUUID() + ".pdf";
        try {
            URL resource = this.getClass().getResource("/bestsign/contract5.pdf");
            reader = new PdfReader(resource);
            file = new File(destPath);
            ps = new PdfStamper(reader, new FileOutputStream(file));
            BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            AcroFields s = ps.getAcroFields();
            s.setField("applyuser", "张三");    // field为pdf文件的一个可编辑表格单元， 表格单元的字段命令为applyuser
            s.setField("idnumber", "loanReg.getIdNumber()");
            s.setField("bankname", "中国工商银行");
            s.setField("cardno", "loanReg.getCardNo()");
            s.setField("phone", "loanReg.getPhone()");
            s.setField("email", "");
            s.setField("eAccount", "eAccNo");
            s.setField("amount", "amountstr");
            s.setField("bigAmount", "value");
            s.setField("bdate", "bdate");
            s.setField("edate", "edate");
            s.setField("days", "days");
            s.setField("by", "byear.toString()");
            s.setField("bm", "bmonth.toString()");
            s.setField("bd", "bday.toString()");
            ps.close();
            reader.close();
            log.info("生成合同成功: " + destPath);
        } catch (IOException | DocumentException e) {
            log.error("生成合同pdf失败", e);
        }
        return file;
    }
}
