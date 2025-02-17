package com.yassir.RateMe.Service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploader {
    String upload(MultipartFile multipartFile);
}
