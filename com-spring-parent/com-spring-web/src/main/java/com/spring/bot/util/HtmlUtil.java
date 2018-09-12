package com.spring.bot.util;

import gui.ava.html.image.generator.HtmlImageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 <html2image.version>0.1.0</html2image.version>
 <dependency>
 <groupId>com.github.xuwei-k</groupId>
 <artifactId>html2image</artifactId>
 <version>${html2image.version}</version>
 </dependency>
 <dependency>
 <groupId>org.apache.commons</groupId>
 <artifactId>commons-io</artifactId>
 <version>1.3.2</version>
 </dependency>
 */
@Slf4j
public class HtmlUtil {

    /****
     * html 转图片(PNG)
     * @param is  html源文件 文件流
     * @param imgPath 图片路径
     * @param imgName 图片名称
     * @throws Exception
     */
    public static void process(InputStream is, String imgPath, String imgName) throws Exception {
        String template = IOUtils.toString(is);  // 这里是html内容
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        imageGenerator.loadHtml(template);
        String image = imgPath + "/" + imgName + ".png";
        imageGenerator.saveAsImage(image);
    }

    /****
     * 图片转换 png转jpg
     * @param oldJpg  需要转换的图片
     * @param newJpg  转换后的图片
     */
    public static void JpgFormate(String oldJpg,String newJpg) {
        BufferedImage bufferedImage;
        try {
            InputStream inputStream=new FileInputStream(new File(oldJpg));
            bufferedImage= ImageIO.read(inputStream);
            //bufferedImage = ImageIO.read(new File(oldJpg));
            log.info("JpgUtils.bufferedImage:"+bufferedImage);
            BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
                    bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
            ImageIO.write(newBufferedImage, "jpg", new File(newJpg));
            //ImageIO.write(newBufferedImage, "jpg", new File("E:\\allFile\\java.jpg"));
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        InputStream is = HtmlUtil.class.getClassLoader().getResourceAsStream("template/template.html");
        String imgPath = "C:\\Users\\Administrator\\Desktop\\temp";
        String imgName = "template";
        process(is, imgPath, imgName);
        JpgFormate(imgPath+"\\"+imgName+".png", imgPath+"\\"+imgName+".jpg");
    }
}
