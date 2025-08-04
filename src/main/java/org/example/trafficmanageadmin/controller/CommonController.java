package org.example.trafficmanageadmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.trafficmanageadmin.entity.Result;
import org.example.trafficmanageadmin.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin
@RestController
@Slf4j
public class CommonController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    private Result upload(MultipartFile file){
        if (file == null || file.isEmpty()) {
            log.warn("Uploaded file is empty or null");
            return Result.error("上传文件不能为空");
        }

        try {
            String url = aliOSSUtils.upload(file);
            return Result.success(url);
        } catch (Exception e) {
            log.error("File upload failed", e);
            return Result.error("文件上传失败: " + e.getMessage());
        }
    }
}
