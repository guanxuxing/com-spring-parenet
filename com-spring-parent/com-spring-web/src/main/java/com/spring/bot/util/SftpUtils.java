package com.spring.bot.util;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.util.*;

/**
 * java 链接sftp
 <dependency>
 <groupId>com.jcraft</groupId>
 <artifactId>jsch</artifactId>
 <version>0.1.53</version>
 </dependency>
 */
@Slf4j
public class SftpUtils {

    /*****
     * @author gxx
     * @param url  sftp地址
     * @param port   端口号
     * @param userName  用户名
     * @param passWord   密码
     *   连接sftp
     */
    public static ChannelSftp connect(String url,int port,String userName,String passWord) throws Exception {
        ChannelSftp sftpConnet=null;
        try {
            JSch jSch=new JSch();
            Session sshSession=jSch.getSession(userName,url,port);
            sshSession.setPassword(passWord);
            Properties sshConfig=new Properties();
            sshConfig.put("StrictHostKeyChecking","no");
            sshSession.setConfig(sshConfig);
            sshSession.connect(20000);
            Channel channel=sshSession.openChannel("sftp");
            channel.connect();
            sftpConnet=(ChannelSftp) channel;

        } catch (JSchException e) {
            throw e;
        }
        catch (Exception ex){
            throw ex;
        }
        return sftpConnet;
    }


    /******
     * @author gxx
     * @param path  sftp目录
     * @param fileName   上传文件的名称
     * @param inputStream  输入流
     * @return
     * 上传文件(sftp)
     */
    public static boolean uploadFile(String path, String fileName, InputStream inputStream,ChannelSftp channelSftp) throws Exception {
        boolean uploadFlag=false;
        try {
            channelSftp.cd(path);
            channelSftp.put(inputStream, fileName);
            uploadFlag=true;
        } catch (SftpException e) {
            throw  e;
        }catch (Exception ex){
            throw  ex;
        }
            return uploadFlag;
    }

    /**
     * 下载单个文件
     *
     * @param path
     *            下载目录
     * @param pathFile
     *            下载的文件
     * @param savePath
     *            存在本地的路径
     *
     * @throws Exception
     */
    public static boolean downLoadFile(String path, String pathFile, String savePath,ChannelSftp channelSftp) throws Exception {
        boolean downLoadFalg=false;
        try {
            String saveFile = savePath + "//" + pathFile;
            channelSftp.cd(path);
            File file = new File(saveFile);
            channelSftp.get(pathFile, new FileOutputStream(file));
            downLoadFalg=true;
        } catch (SftpException e) {
            throw e;
        } catch (FileNotFoundException ex) {
            throw ex;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
            return downLoadFalg;
    }

    /**
     * @author gxx
     * 删除文件
     *
     * @param path
     *            要删除文件所在目录
     * @param deleteFile
     *            要删除的文件
     *
     * @throws Exception
     */
    public static boolean deleteFile(String path, String deleteFile,ChannelSftp channelSftp) {
        boolean deleteFlag=false;
        try {
            channelSftp.cd(path);
            channelSftp.rm(deleteFile);
            deleteFlag=true;
        } catch (SftpException e) {
            e.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            return deleteFlag;
        }
    }

    /**
     * @author gxx
     * 下载目录下全部文件
     *
     * @param path
     *            下载目录
     *
     * @param savePath
     *            存在本地的路径
     *
     * @throws Exception
     */
    public static boolean downLoadAllFiles(String path, String savePath,ChannelSftp channelSftp) {
        String downloadFile = "";
        List<String> downloadFileList = listFiles(path,channelSftp);
        if(downloadFileList==null||downloadFileList.size()==0){
            return false;
        }
        try {
            Iterator<String> it = downloadFileList.iterator();
            while(it.hasNext())
            {
                downloadFile = it.next().toString();
                if(downloadFile.toString().indexOf(".")<0){
                    continue;
                }
                downLoadFile(path, downloadFile, savePath,channelSftp);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * 列出目录下的文件
     *
     * @param path
     *            要列出的目录
     *
     * @return list 文件名列表
     *
     * @throws Exception
     */
    public static List<String> listFiles(String path,ChannelSftp channelSftp) {
        Vector fileList;
        try {
            List<String> fileNameList = new ArrayList();
            fileList = channelSftp.ls(path);
            Iterator it = fileList.iterator();
            while(it.hasNext())
            {
                String fileName = ((ChannelSftp.LsEntry)it.next()).getFilename();
                if(".".equals(fileName) || "..".equals(fileName)){
                    continue;
                }
                fileNameList.add(fileName);
            }
            return fileNameList;
        } catch (SftpException e) {
            e.printStackTrace();
            return null;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
