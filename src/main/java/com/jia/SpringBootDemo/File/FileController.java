package com.jia.SpringBootDemo.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@RestController
public class FileController {
    // 文件上传
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        File file1 = new File("d:/" + file.getOriginalFilename());
        file1.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return "file is upload success.";
    }

    // 文件下载
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    // 这里演示把文件在页面上展示出来，不是真正的下载
    public ResponseEntity<Object> downFile(HttpServletResponse response) throws IOException {
        String fileName = "d:/Ready to study.txt";
        File file = new File(fileName);
        FileSystemResource fileSystemResource = new FileSystemResource(file);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; " +
                "filename=" + file.getName());
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok().
                headers(headers).
                contentLength(fileSystemResource.contentLength()).
                contentType(MediaType.parseMediaType("application/octet-stream")).
                body(new InputStreamResource(fileSystemResource.getInputStream()));
    }
}
