package com.elearn.app.start_learn_back.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseDto {

    private String id;

    private String title;

    private String shortDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public List<VideoDto> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoDto> videos) {
        this.videos = videos;
    }

    public List<CategoryDto> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryDto> categoryList) {
        this.categoryList = categoryList;
    }

//    @JsonIgnore
//    @JsonProperty("long_description")
    private String longDesc;

    private double price;

    private boolean live = false;

    public String getBannerContentType() {
        return bannerContentType;
    }

    public void setBannerContentType(String bannerContentType) {
        this.bannerContentType = bannerContentType;
    }

    private double discount;

    private Date createdDate;

    private String banner;

    private String bannerContentType;

    //videos
    private List<VideoDto> videos = new ArrayList<>();

    //categories
    private List<CategoryDto> categoryList = new ArrayList<>();

    public String getBannerUrl(){
        return "http://localhost:8080/api/v1/courses/" + this.id + "/banners";
    }
}
