package com.amir.finalproject.service;

import com.amir.finalproject.exception.FileStorageException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private  final Path fileStorageLocation;
    public FileStorageService() {
        this.fileStorageLocation = Paths.get("uploads")
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception ex){
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sory! Filename contains invalid path seq" + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            // TODO: 2/6/24 throw new exp local
            throw new FileStorageException("could not store file " + fileName + ". Pls try again!", ex);
        }
    }
}
