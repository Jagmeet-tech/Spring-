package com.elearn.app.start_learn_back.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {

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

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", addedDate=" + addedDate +
                ", courses=" + courses +
                '}';
    }

    @Id
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(name = "description")
    private String desc;

    private Date addedDate;

    @ManyToMany(mappedBy = "categoryList",cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();


    // nested resource
    public void addCourse(Course course){
        //Since we are handling bidirectional mapping , so when we are adding course inside a "category" entity,  then we need to take care of adding catgeory to that "course" entity as well.
        // (so that we fetch related entities info. through whichever entity we have).
        this.courses.add(course);
        course.getCategoryList().add(this);     // bidirectional mapping.
    }

    public void removeCourse(Course course){
        this.courses.remove(course);
        course.getCategoryList().remove(this);  // bidirectional mapping
    }
}
