package com.amir.finalproject.service;

import com.amir.finalproject.model.Video;
import com.amir.finalproject.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoService {

    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private VideoRepository videoRepository;

    public Video uploadVideo(MultipartFile file, String title) {
        //get name of file
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Video video = new Video(title, file.getSize(), title);
        videoRepository.save(video);
        return video;
    }
}
