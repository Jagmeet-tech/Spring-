package com.elearn.app.start_learn_back.services.impl;

import com.elearn.app.start_learn_back.dtos.CategoryDto;
import com.elearn.app.start_learn_back.dtos.CourseDto;
import com.elearn.app.start_learn_back.dtos.CustomPageResponse;
import com.elearn.app.start_learn_back.entities.Category;
import com.elearn.app.start_learn_back.entities.Course;
import com.elearn.app.start_learn_back.exceptions.ResourceNotFoundException;
import com.elearn.app.start_learn_back.repositories.CategoryRepo;
import com.elearn.app.start_learn_back.repositories.CourseRepo;
import com.elearn.app.start_learn_back.services.CategoryService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;
    private ModelMapper modelMapper;

    @Autowired
    private CourseRepo courseRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto insert(CategoryDto categoryDto) {

        // create category id
        String catId = UUID.randomUUID().toString();
        categoryDto.setId(catId);
        categoryDto.setAddedDate(new Date());

        System.out.println("hello--------------------" + categoryDto);
        //convert dto to entity
        Category category = modelMapper.map(categoryDto, Category.class);
        System.out.println("category--------------------" + category);
        Category savedCategory = categoryRepo.save(category);

        System.out.println("done");
        return modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CustomPageResponse<CategoryDto> getAll(int pageNumber,int pageSize,String sortBy) {

        //Sorting
        Sort sort = Sort.by(sortBy);
        sort.ascending();

        // Paging
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sort);

        Page<Category> categoryPage = categoryRepo.findAll(pageRequest);
        List<Category> categoryList = categoryPage.getContent();
        List<CategoryDto> categoryDtoList = categoryList.stream().map((category) -> modelMapper.map(category,CategoryDto.class)).toList();

        CustomPageResponse<CategoryDto> customPageResponse = new CustomPageResponse<>();
        customPageResponse.setContent(categoryDtoList);
        customPageResponse.setPageNumber(pageNumber);
        customPageResponse.setLast(categoryPage.isLast());
        customPageResponse.setTotalElements((int)categoryPage.getTotalElements());
        customPageResponse.setPageSize(categoryPage.getSize());
        customPageResponse.setTotalPages(categoryPage.getTotalPages());

        return customPageResponse;
    }

    @Override
    public CategoryDto get(String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found.."));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public void delete(String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found for deleting.."));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found for updation.."));
        category.setTitle(categoryDto.getTitle());
        category.setDesc(categoryDto.getDesc());
        Category updatedCategory = categoryRepo.save(category);
        return modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> searchVideos(String keyword){
        System.out.println(keyword + "-----------------------------");
        List<Category> categories = categoryRepo.findByTitleIgnoreCaseOrDescContainingIgnoreCase(keyword,keyword);  // find by title or desc ignore case (protocol rule methods)
        return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional   // to maintain active db session.
    public void addCourseToCategory(String catId,String courseId){
        //get catgeory
        Category category = categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category not found.."));

        //get course
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found.."));
        category.addCourse(course);
        categoryRepo.save(category);

        System.out.println("category relationship updated...");
    }

    @Override
    @Transactional
    public List<CourseDto> getCoursesOfCat(String categoryId){
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found.."));
        List<CourseDto> courseList = category.getCourses().stream().map(course -> modelMapper.map(course,CourseDto.class)).collect(Collectors.toList());
        return courseList;
    }

}
