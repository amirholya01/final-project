package com.amir.finalproject.service;

import com.amir.finalproject.model.Video;
import com.amir.finalproject.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private VideoRepository videoRepository;

    //upload a video
    public Video uploadVideo(MultipartFile file, String title) {
        //get name of file
        String fileName = fileStorageService.storeFile(file);

        Video video = new Video(title, file.getSize(), fileName);
        videoRepository.save(video);
        return video;
    }



}
