package com.lp.f2000.util;

import java.io.File;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

public class AliOSSUtil {
    /**
     * 管理控制台里面获取EndPoint
     */
    private final static String END_POINT = "oss-cn-hangzhou.aliyuncs.com";
    /**
     * 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
     */
    private final static String ACCESS_KEY_ID = "LTAItJjdGxxZzUxL";
    private final static String ACCESS_KEY_SECRET = "OpoWY9S0KJr0QNis1KtIEPhSikeSZF";
    /**
     * 上传的BUCKET名称
     */
    private final static String BUCKET_NAME = "2000f-img";
    /**
     * 管理控制台里面获取的访问域名
     */
    private final static String FILE_HOST = "https://2000f-img.oss-cn-hangzhou.aliyuncs.com/";

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
