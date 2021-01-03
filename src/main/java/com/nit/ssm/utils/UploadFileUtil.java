package com.nit.ssm.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UploadFileUtil {
    private static final String PATH_HEAD = "http://localhost:9111/";
    private static final String PHYSICAL_PATH = "D:\\javaweb\\3180434038\\";

    //获取流文件
    private static void inputStreamToFile(InputStream ins, FileOutputStream os) throws IOException {
        int bytesRead = 0;
        int len = 10 * 1024 * 1024;
        byte[] buffer = new byte[len];
        while ((bytesRead = ins.read(buffer, 0, len)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
    }

    /**
     * 上传文件到服务器的指定路径
     *
     * @return 文件最终地址
     */
    public static String uploadFile(MultipartFile multipartFile, String fileName) throws IOException {
        File file;
        if (multipartFile != null) {
            InputStream is = multipartFile.getInputStream();
            file = new File(PHYSICAL_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            inputStreamToFile(is, new FileOutputStream(PHYSICAL_PATH + fileName));
            is.close();
        }
        return PATH_HEAD + fileName;
    }


    /**
     * 上传文件到服务器的指定路径
     *
     * @return 文件最终地址
     */
    public static String uploadImg(MultipartFile multipartFile, String fileName) throws IOException {
        File file;
        if (multipartFile != null) {
            InputStream is = multipartFile.getInputStream();
            file = new File(PHYSICAL_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            inputStreamToFile(is, new FileOutputStream(PHYSICAL_PATH + fileName));
            is.close();
        }
        return PATH_HEAD + fileName;
    }


    /**
     * 删除本地临时文件
     */
    public static void deleteTempFile(String fileURL) throws NullPointerException {
        if (fileURL != null) {
            int index = fileURL.indexOf(PATH_HEAD);
            if (index != -1) {
                fileURL = fileURL.substring(0, index) +
                        PHYSICAL_PATH +
                        fileURL.substring(index + PATH_HEAD.length());
            }
            File file = new File(fileURL);
            file.delete();
        } else {
            throw new NullPointerException();
        }
    }

    public static void main(String[] args) throws Exception {
//        deleteTempFile("http://127.0.0.1:8555/file/小a1-1-11/1/1.txt");
    }
}
