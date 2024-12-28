package com.elearn.app.start_learn_back.services.impl;

import com.elearn.app.start_learn_back.dtos.CourseDto;
import com.elearn.app.start_learn_back.entities.Course;
import com.elearn.app.start_learn_back.exceptions.ResourceNotFoundException;
import com.elearn.app.start_learn_back.repositories.CourseRepo;
import com.elearn.app.start_learn_back.services.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    public CourseServiceImpl(CourseRepo courseRepo, ModelMapper modelMapper) {
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
    }

    private CourseRepo courseRepo;

    private ModelMapper modelMapper;


    @Override
    public CourseDto create(CourseDto courseDto) {
        Course course = courseRepo.save(this.dtoToEntity(courseDto));
        return entityToDto(course);
    }

    @Override
    public List<CourseDto> getAll() {
        List<Course> courseList = courseRepo.findAll();

        // all course into dto
        List<CourseDto> courseDtoList = courseList.stream().map(course -> this.entityToDto(course)).collect(Collectors.toList());
        return courseDtoList;
    }

    @Override
    public CourseDto update(CourseDto courseDto, String courseId) {
        return null;
    }

    @Override
    public void delete(String courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found !!"));
        courseRepo.delete(course);
    }

    @Override
    public List<CourseDto> searchByTitle(String titleKeyword) {
        return List.of();
    }

    public CourseDto entityToDto(Course course){
//        CourseDto courseDto = new CourseDto();
//        courseDto.setId(course.getId());
//        courseDto.setTitle(course.getTitle());

        // do rest of the fields.
        // for this problem , we have modelMapping;


        CourseDto courseDto = modelMapper.map(course,CourseDto.class);

        return courseDto;
    }

    public Course dtoToEntity(CourseDto courseDto){
//        Course course = new Course();
//        course.setId(dto.getId());

        // do rest of the fields/.
        // for this problem , we have modelMapping;

        Course course = modelMapper.map(courseDto,Course.class);
        return course;
    }
}
