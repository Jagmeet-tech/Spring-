package com.elearn.app.start_learn_back.dtos;

import com.elearn.app.start_learn_back.entities.Course;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDto {

    private String id;

    @NotEmpty(message = "title is required")
    @Size(min = 3,max = 50,message = "title must be between 3 and characters.")
    private String title;

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

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", addedDate=" + addedDate +
                '}';
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    @NotEmpty(message = "Description is required.")
//    @Pattern(regexp = "") could be used for email property.
    private String desc;


    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy--MM--dd hh:mm:ss a",timezone = "IST")
    private Date addedDate;
}
