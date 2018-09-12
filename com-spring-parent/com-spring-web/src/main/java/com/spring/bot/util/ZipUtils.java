package com.spring.bot.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.io.*;
import java.util.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

/**
 * <ant.version>1.7.0</ant.version>
 <dependency>
 <groupId>ant</groupId>
 <artifactId>ant</artifactId>
 <version>${ant.version}</version>
 </dependency>
 */
public class ZipUtils {
    public static Logger logger= LoggerFactory.getLogger(ZipUtils.class);

    static final int BUFFER = 8192;

    public static void compress(String zipFile, String srcPathName) {
        File file = new File(srcPathName);
        if (!file.exists())
            throw new RuntimeException(srcPathName + "不存在！");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,
                    new CRC32());
            ZipOutputStream out = new ZipOutputStream(cos);
            String basedir = "";
            compress(file, out, basedir);
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void compress(File file, ZipOutputStream out, String basedir) {
        /* 判断是目录还是文件 */
        if (file.isDirectory()) {
            logger.info("压缩：" + basedir + file.getName());
            compressDirectory(file, out, basedir);
        } else {
            logger.info("压缩：" + basedir + file.getName());
            compressFile(file, out, basedir);
        }
    }

    /**
     * 压缩一个目录
     */
    public static void compressDirectory(File dir, ZipOutputStream out, String basedir) {
        if (!dir.exists())
            return;

        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            /* 递归 */
            compress(files[i], out, basedir + dir.getName() + "/");
        }
    }

    /**
     * 压缩一个文件
     */
    public static void compressFile(File file, ZipOutputStream out, String basedir) {
        if (!file.exists()) {
            return;
        }
        try {
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            ZipEntry entry = new ZipEntry(basedir + file.getName());
            out.putNextEntry(entry);
            int count;
            byte data[] = new byte[BUFFER];
            while ((count = bis.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            bis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * 文件转移
     *
     * @param path1
     */
    public static void IOCopy(String path, String path1) {
        File file = new File(path);
        File file1 = new File(path1);
        if (!file.exists()) {
            System.out.println(file.getName() + "文件不存在");
        } else {
            System.out.println("存在");
        }
        byte[] b = new byte[(int) file.length()];
        if (file.isFile()) {
            try {
                FileInputStream is = new FileInputStream(file);
                FileOutputStream ps = new FileOutputStream(file1);
                is.read(b);
                ps.write(b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (file.isDirectory()) {
            if (!file.exists())
                file.mkdir();
            String[] list = file.list();
            for (int i = 0; i < list.length; i++) {
                IOCopy(path + "/" + list[i], path1 + "/" + list[i]);
            }
        }
    }


    /****
     * 文件压缩和解压缩
     * @param args
     */
    /**
     * 压缩文件(包括子目录)
     *
     * @param baseDir  要压缩的文件夹(物理路径)
     * @param fileName 保存的文件名称(物理文件路径)
     * @throws Exception
     */
    public static void zipFile(String baseDir, String fileName) {
        try {
            List<File> fileList = getSubFiles(new File(baseDir));
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(fileName));
            zos.setEncoding("UTF-8");//设置编码,解决中文乱码的问题
            ZipEntry ze = null;
            byte[] buf = new byte[1024];
            int readLen = 0;
            for (int i = 0; i < fileList.size(); i++) {
                File f = (File) fileList.get(i);
                ze = new ZipEntry(getAbsFileName(baseDir, f));
                ze.setSize(f.length());
                ze.setTime(f.lastModified());
                zos.putNextEntry(ze);
                InputStream is = new BufferedInputStream(new FileInputStream(f));
                while ((readLen = is.read(buf, 0, 1024)) != -1) {
                    zos.write(buf, 0, readLen);
                }
                is.close();
            }
            zos.closeEntry();
            zos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 给定根目录，返回另一个文件名的相对路径，用于zip文件中的路径.
     *
     * @param baseDir      java.lang.String 根目录
     * @param realFileName java.io.File 实际的文件名
     * @return 相对文件名
     */
    private static String getAbsFileName(String baseDir, File realFileName) {
        File real = realFileName;
        File base = new File(baseDir);
        String ret = real.getName();
        while (true) {
            real = real.getParentFile();
            if (real == null)
                break;
            if (real.equals(base))
                break;
            else
                ret = real.getName() + "/" + ret;
        }
        return ret;
    }

    /**
     * 取得指定目录下的所有文件列表，包括子目录.
     *
     * @param baseDir File 指定的目录
     * @return 包含java.io.File的List
     */
    public static List<File> getSubFiles(File baseDir) {
        List<File> ret = new ArrayList<>();
        try {
            File[] tmp = baseDir.listFiles();
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i].isFile()) {
                    ret.add(tmp[i]);
                }
                if (tmp[i].isDirectory()) {
                    ret.addAll(getSubFiles(tmp[i]));

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 解压缩功能. 将ZIP_FILENAME文件解压到ZIP_DIR目录下.
     *
     * @param zipFileName ZIP文件物理路径
     * @param zipDir
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static void unZipFile(String zipFileName, String zipDir) throws Exception {
        InputStream input = null;
        BufferedOutputStream bos = null;
        ZipFile zip = null;
        try {
            zip = new ZipFile(zipFileName);
            Enumeration<ZipEntry> en = zip.getEntries();
            ZipEntry entry = null;
            byte[] buffer = new byte[1024];
            int length = -1;
            File file = null;
            while (en.hasMoreElements()) {
                entry = (ZipEntry) en.nextElement();
                if (entry.isDirectory()) {
                    file = new File(zipDir, entry.getName());
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    continue;
                }

                input = zip.getInputStream(entry);
                file = new File(zipDir, entry.getName());
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                bos = new BufferedOutputStream(new FileOutputStream(file));

                while (true) {
                    length = input.read(buffer);
                    if (length == -1) {
                        break;
                    }
                    bos.write(buffer, 0, length);
                }
                bos.close();
                input.close();
            }
            zip.close();
        } catch (IOException e) {
            logger.info("图片解压缩失败",e);
        } finally {
            bos.close();
            input.close();
            zip.close();
        }
    }

    // 字节数组转图片
    public static String byte2image(byte[] data, String path) {
        try {
            if (data.length < 3 || path.equals("")) return null;
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
            return "success";
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            return null;
        }
    }

    //图片到byte数组
    public static byte[] image2byte(String path) {
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }


    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        boolean flag = true;
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /****
     * @author gxx
     * @param filepath
     * @throws IOException
     * 删除文件夹下面的所有文件信息
     */
    public static void del(String filepath) {
        try {
            File f = new File(filepath);//定义文件路径
            if(f.exists() && f.isDirectory()){//判断是文件还是目录
                if(f.listFiles().length==0){//若目录下没有文件则直接删除
                    f.delete();
                }else{//若有则把文件放进数组，并判断是否有下级目录
                    File delFile[]=f.listFiles();
                    int i =f.listFiles().length;
                    for(int j=0;j<i;j++){
                        if(delFile[j].isDirectory()){
                            del(delFile[j].getAbsolutePath());//递归调用del方法并取得子目录路径
                        }
                        delFile[j].delete();//删除文件
                    }
                }
            }
        } catch (Exception e) {
            logger.error("删除临时目录下文件异常:",e);
        }
    }

}
