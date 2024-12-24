package com.codegym.musicplayer.service;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadFileService {
    public void uploadFile(MultipartFile file) throws IOException {
        String folderUpload = "D:\\Desktop\\JV101\\module4\\spring\\music-player\\src\\main\\webapp\\uploadFile\\";
        // lay ten
        String fileName = file.getOriginalFilename();
        // ghep duong dan voi ten file de move file
        FileCopyUtils.copy(file.getBytes(), new File(folderUpload + fileName));
    }
}
