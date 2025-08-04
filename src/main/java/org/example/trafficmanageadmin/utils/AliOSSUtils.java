package org.example.trafficmanageadmin.utils;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.example.trafficmanageadmin.config.AliyunOSSProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */
@Component  //加入到IOC
public class AliOSSUtils {

    @Autowired
    private AliyunOSSProperties aliyunOSSProperties; //你的Bucket 名称

    /**
     * 实现上传图片到OSS
     */
    public String upload(MultipartFile file) throws IOException {
        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName;
        if (originalFilename != null && originalFilename.lastIndexOf(".") > 0) {
            // 如果文件有扩展名，则保留扩展名
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            fileName = UUID.randomUUID().toString() + fileExtension;
        } else {
            // 如果文件没有扩展名，则使用默认扩展名或者不添加扩展名
            fileName = UUID.randomUUID().toString();
        }

        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build("https://" + aliyunOSSProperties.getEndpoint(), aliyunOSSProperties.getAccessKeyId(), aliyunOSSProperties.getAccessKeySecret());
        ossClient.putObject(aliyunOSSProperties.getBucketName(), fileName, inputStream);

        //文件访问路径
        String url;
        if (aliyunOSSProperties.getEndpoint().contains("://")) {
            // 如果endpoint已经包含了协议部分
            String[] parts = aliyunOSSProperties.getEndpoint().split("://", 2);
            url = parts[0] + "://" + aliyunOSSProperties.getBucketName() + "." + parts[1] + "/" + fileName;
        } else if (aliyunOSSProperties.getEndpoint().contains("//")) {
            // 如果endpoint包含了//但没有协议
            String[] parts = aliyunOSSProperties.getEndpoint().split("//", 2);
            url = parts[0] + "//" + aliyunOSSProperties.getBucketName() + "." + parts[1] + "/" + fileName;
        } else {
            // 如果endpoint只包含了域名部分
            url = "https://" + aliyunOSSProperties.getBucketName() + "." + aliyunOSSProperties.getEndpoint() + "/" + fileName;
        }

        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }

}