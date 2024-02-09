package com.amir.finalproject.controller;

import com.amir.finalproject.model.Video;
import com.amir.finalproject.service.VideoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping
    public Video uploadVideo(@RequestParam("file") MultipartFile file, String title){
        return videoService.uploadVideo(file, title);
    }


    //Get all video
    @GetMapping
    public List<Video> videos(){
        return videoService.findAllVideos();
    }

    //Get a video by id
    @GetMapping("{id}")
    public Video findById(@PathVariable("id") Long id){
        return videoService.findById(id);
    }

    @GetMapping("/download/{id:.+}") //everything after id
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, HttpServletRequest request){
        return (ResponseEntity<Resource>) videoService.downloadFile(id, request);
    }
}
