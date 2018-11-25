package com.lp.f2000.component;

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
	
    private static String END_POINT;
	
    private static String ACCESS_KEY_ID;
	
    private static String ACCESS_KEY_SECRET;
	
    private static String BUCKET_NAME;
	
    private static String FILE_HOST;


    @Value("${ali.end-point}")
    public  void setEND_POINT(String eND_POINT) {
		END_POINT = eND_POINT;
	}
    @Value("${ali.access-key-id}")
	public  void setACCESS_KEY_ID(String aCCESS_KEY_ID) {
		ACCESS_KEY_ID = aCCESS_KEY_ID;
	}
	@Value("${ali.access-key-secret}")
	public  void setACCESS_KEY_SECRET(String aCCESS_KEY_SECRET) {
		ACCESS_KEY_SECRET = aCCESS_KEY_SECRET;
	}
	@Value("${ali.bucket-name}")
	public  void setBUCKET_NAME(String bUCKET_NAME) {
		BUCKET_NAME = bUCKET_NAME;
	}
	@Value("${ali.file-host}")
	public  void setFILE_HOST(String fILE_HOST) {
		FILE_HOST = fILE_HOST;
	}

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
