package com.kenmi.bigevent.server.controller;

import com.kenmi.bigevent.api.response.ResultResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping
    public ResultResponse<String> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String filename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        File saveFile = new File("/Users/azh230/workspace/local/data/big-event/files/" + filename);
        file.transferTo(saveFile);
        return ResultResponse.successResult(saveFile.getAbsolutePath());
    }
}
