package com.spring.bot.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * <pdfbox.version>2.0.2</pdfbox.version>
 *  <dependency>
 <groupId>org.apache.pdfbox</groupId>
 <artifactId>pdfbox</artifactId>
 <version>${pdfbox.version}</version>
 </dependency>
 */
@Slf4j
public class PDFBoxUtils {
    /**
     * @param pdfSource pdf文件路径
     * @param imagePath pdf转图片后保存的路径
     * @param fileName  pdf转图片 图片名称
     * @throws Exception
     */
    public static void pdfToImage(String pdfSource, String imagePath, String fileName) throws Exception {
        PDDocument document=PDDocument.load(new File(pdfSource));
        PDFRenderer pdfRenderer=new PDFRenderer(document);
        log.info("begin  pdf render image:[{}]",pdfSource);
        for(int page=0;page<document.getNumberOfPages();++page){
            BufferedImage bim=pdfRenderer.renderImageWithDPI(page,120, ImageType.RGB);
            try{
                String endsSeperate=imagePath.endsWith("/")?"":"/";
                String imageFileName=imagePath+endsSeperate+fileName+page+".jpg";
                File baos=new File(imageFileName);
                ImageIO.write(bim,"jpg",baos);
            }catch (Exception e){
                log.error("pdf render image error",e);
            }
        }
        log.info("end  pdf render image:[{}],[{}]",pdfSource);
        document.close();
    }

    public static void main(String[] args) throws Exception{
        pdfToImage("E:\\testProject\\com-spring-parent\\com-spring-web\\src\\main\\resources\\bestsign\\contract5.pdf",
                "C:\\Users\\Administrator\\Desktop\\temp", "pdf");
    }
}
