package com.amir.finalproject.service;

import com.amir.finalproject.model.Video;
import com.amir.finalproject.repository.VideoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    //Get all videos
    public List<Video> findAllVideos(){
        return videoRepository.findAll();
    }

    //Get a video by id
    public Video findById(Long id){
        Optional<Video> video = videoRepository.findById(id);
        return video.isPresent() ? video.get() : null; //ternary operator in Java
    }


    //Download file
    public Resource downloadFile(Long id, HttpServletRequest request){
        Optional<Video> video = videoRepository.findById(id);
        Resource resource = fileStorageService.loadFileAsResource(video.get().getUrl());

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(contentType == null){
            contentType = "application/octet-stream";
        }


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"", resource.getFilename() + "\"")
                .body(resource).getBody();

    }
}
