package com.amir.finalproject.controller;

import com.amir.finalproject.model.Video;
import com.amir.finalproject.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping
    public Video uploadVideo(@RequestParam("file") MultipartFile file, String title){
        return videoService.uploadVideo(file, title);
    }


}
