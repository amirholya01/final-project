package com.amir.finalproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "video-seq")
    @SequenceGenerator(name = "video-seq", sequenceName = "video-seq", allocationSize = 1)
    private Long id;
    private String title;
    private double size;
    private String url;
    private Date created;
    private Date modified;

    public Video() {
    }

    public Video(Long id, String title, double size, String url, Date created, Date modified) {
        this.id = id;
        this.title = title;
        this.size = size;
        this.url = url;
        this.created = created;
        this.modified = modified;
    }

    public Video(String title, double size, String url) {
        this.title = title;
        this.size = size;
        this.url = url;
    }
}
