package com.lp.f2000.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

@Component
@PropertySource(value = "classpath:application.yml", ignoreResourceNotFound = true)
public class AliOSSUtil {
	@Value("${ali.end_point}")
    private static String END_POINT;
	@Value("${ali.access_key_id}")
    private static String ACCESS_KEY_ID;
	@Value("${ali.access_key_secret}")
    private static String ACCESS_KEY_SECRET;
	@Value("${ali.bucket_name}")
    private static String BUCKET_NAME;
	@Value("${ali.file_host}")
    private static String FILE_HOST;


    /**
     * 上传文件到bucket
     *
     * @param file     本地文件
     * @param dir      bucket存放目录(末尾要加/)
     * @param fileName 上传文件名
     * @return 访问地址
     */
    public static String uploadLocalFile(File file, String dir, String fileName) {
        if (file == null || !file.exists()) {
            return null;
        }
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(BUCKET_NAME, dir + fileName, file));
            if (null != result) {
                return FILE_HOST + dir + fileName;
            } else {
                return null;
            }
        } catch (OSSException | ClientException oe) {
            oe.printStackTrace();
            return null;
        } finally {
            // 关闭OSS服务
            ossClient.shutdown();
        }
    }

    /**
     * 上传文件到bucket
     *
     * @param file 本地文件
     * @param dir  bucket目录
     * @return 访问地址
     */
    public static String uploadLocalFile(File file, String dir) {
        if (file == null) {
            return null;
        }
        String filePath = dir + file.getName();
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(BUCKET_NAME, filePath, file));
            if (null != result) {
                // 拼装访问地址
                return FILE_HOST + filePath;
            } else {
                return null;
            }
        } catch (OSSException | ClientException oe) {
            oe.printStackTrace();
            return null;
        } finally {
            // 关闭OSS服务
            ossClient.shutdown();
        }
    }
}
