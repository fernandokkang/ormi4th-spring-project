package com.estsoft.springproject.controller;

import com.estsoft.springproject.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tui-editor")
public class FileApiController {

    private final FileService fileService;

    @PostMapping("/image-upload")
    public String fileWrite(@RequestParam MultipartFile image) throws IOException {

        return fileService.fileWrite(image);
    }

    @GetMapping("/image-print")
    public byte[] printEditorImage(@RequestParam String filename) throws FileNotFoundException {

        return fileService.fileLoad(filename);

    }

    /*// 파일을 업로드할 디렉터리 경로
    private final String uploadDir = Paths.get(System.getProperty("user.dir"), "build", "resources", "main", "static", "images", "upload").toString();

    *//**
     * 에디터 이미지 업로드
     *
     * @param image 파일 객체
     * @return 업로드된 파일명
     *//*
    @PostMapping("/image-upload")
    public String uploadEditorImage(@RequestParam final MultipartFile image) throws FileNotFoundException {
        if (image.isEmpty()) {
            return "";
        }

        String orgFilename = image.getOriginalFilename();                                         // 원본 파일명
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");           // 32자리 랜덤 문자열
        String extension = orgFilename.substring(orgFilename.lastIndexOf(".") + 1);  // 확장자
        String saveFilename = uuid + "." + extension;                                             // 디스크에 저장할 파일명
        String fileFullPath = Paths.get(uploadDir, saveFilename).toString();                      // 디스크에 저장할 파일의 전체 경로

        // uploadDir에 해당되는 디렉터리가 없으면, uploadDir에 포함되는 전체 디렉터리 생성
        File dir = new File(uploadDir);
        if (dir.exists() == false) {
            dir.mkdirs();
        }

        try {
            // 파일 저장 (write to disk)
            File uploadFile = new File(fileFullPath);
            image.transferTo(uploadFile);
            return saveFilename;

        } catch (IOException e) {
            // 예외 처리는 따로 해주는 게 좋습니다.
            throw new RuntimeException(e);
        }
    }

    *//**
     * 디스크에 업로드된 파일을 byte[]로 반환
     *
     * @param filename 디스크에 업로드된 파일명
     * @return image byte array
     *//*
    @GetMapping(value = "/image-print", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] printEditorImage(@RequestParam final String filename) {
        // 업로드된 파일의 전체 경로
        String fileFullPath = Paths.get(uploadDir, filename).toString();

        // 파일이 없는 경우 예외 throw
        File uploadedFile = new File(fileFullPath);
        if (uploadedFile.exists() == false) {
            throw new RuntimeException();
        }

        try {
            // 이미지 파일을 byte[]로 변환 후 반환
            byte[] imageBytes = Files.readAllBytes(uploadedFile.toPath());
            return imageBytes;

        } catch (IOException e) {
            // 예외 처리는 따로 해주는 게 좋습니다.
            throw new RuntimeException(e);
        }
    }*/
}