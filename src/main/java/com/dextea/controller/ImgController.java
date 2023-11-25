package com.dextea.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/img")
@CrossOrigin(origins = "*")
public class ImgController {
    @PostMapping("/upload")
    public String uploadImg(MultipartFile file){
        return file.getOriginalFilename();
    }
}
