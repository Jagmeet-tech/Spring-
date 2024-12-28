package com.elearn.app.start_learn_back.entities;

import jakarta.persistence.*;
import lombok.Data;


// owning side

@Entity
@Table(name = "videos")
@Data
public class Video {

    @Id
    private String videoId;

    private String title;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Column(name = "description",length = 2000)
    private String desc;

    private String filePath;

    private String contentType;

    @ManyToOne
    private Course course;
}
