package com.elearn.app.start_learn_back.services;

import com.elearn.app.start_learn_back.dtos.CategoryDto;
import com.elearn.app.start_learn_back.dtos.CourseDto;
import com.elearn.app.start_learn_back.dtos.CustomPageResponse;
import com.elearn.app.start_learn_back.dtos.VideoDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {

    //create
    CategoryDto insert(CategoryDto categoryDto);

    CustomPageResponse<CategoryDto> getAll(int pageNumber, int pageSize,String sortBy);

    CategoryDto get(String categoryId);

    void delete(String categoryId);

    CategoryDto update(CategoryDto categoryDto,String categoryId);

    //search
    List<CategoryDto> searchVideos(String keyword);

    public void addCourseToCategory(String catId,String courseId);

    List<CourseDto> getCoursesOfCat(String categoryId);
}
