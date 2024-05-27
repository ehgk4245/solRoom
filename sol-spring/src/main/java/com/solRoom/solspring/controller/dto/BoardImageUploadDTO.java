package com.solRoom.solspring.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardImageUploadDTO {
    private List<MultipartFile>files;

    public void setFiles(List<MultipartFile> files) {
        // 빈 파일을 제거하는 처리
        List<MultipartFile> nonEmptyFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                nonEmptyFiles.add(file);
            }
        }
        this.files = nonEmptyFiles;
    }

}
