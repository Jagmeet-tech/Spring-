package com.elearn.app.start_learn_back.services.impl;

import com.elearn.app.start_learn_back.config.AppConstants;
import com.elearn.app.start_learn_back.dtos.CourseDto;
import com.elearn.app.start_learn_back.dtos.ResourceContentType;
import com.elearn.app.start_learn_back.entities.Course;
import com.elearn.app.start_learn_back.exceptions.ResourceNotFoundException;
import com.elearn.app.start_learn_back.repositories.CourseRepo;
import com.elearn.app.start_learn_back.services.CourseService;
import com.elearn.app.start_learn_back.services.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    public CourseServiceImpl(CourseRepo courseRepo, ModelMapper modelMapper) {
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
    }

    private CourseRepo courseRepo;

    private ModelMapper modelMapper;

    @Autowired
    private FileService fileService;


    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        courseDto.setId(UUID.randomUUID().toString());
        courseDto.setCreatedDate(new Date());
        Course course = modelMapper.map(courseDto,Course.class);
        Course savedCourse = courseRepo.save(course);
        return modelMapper.map(savedCourse,CourseDto.class);
    }

    @Override
    public CourseDto updateCourse(String id, CourseDto courseDto){
        Course course = courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found .."));
        modelMapper.map(courseDto,course);
        Course updatedCourse = courseRepo.save(course);
        return modelMapper.map(course,CourseDto.class);
    }

    @Override
    public CourseDto getCourseById(String id){
        Course course = courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found.."));
        return modelMapper.map(course,CourseDto.class);
    }

    @Override
    public void deleteCourse(String id) {
        Course course = courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found !!"));
        courseRepo.delete(course);
    }

    @Override
    public Page<CourseDto> getAllCourses(Pageable pageable) {
        Page<Course> page = courseRepo.findAll(pageable);
        List<CourseDto> dtos = page.getContent().stream().map(course -> modelMapper.map(course,CourseDto.class)).collect(Collectors.toList());

        // Without need to create seperate Dto for CustomPageResponse , we can use existing PageImpl<> class as well for response.
        return new PageImpl<>(dtos,pageable,page.getTotalElements());
    }

    @Override
    public List<CourseDto> searchCourses(String keyword) {
        List<Course> courses = courseRepo.findByTitleIgnoreCaseOrShortDescContainingIgnoreCase(keyword,keyword);  // find by title or desc ignore case (protocol rule methods)
        return courses.stream().map(course -> modelMapper.map(course,CourseDto.class)).collect(Collectors.toList());
    }

    // File related things

    @Override
    public CourseDto saveBanner(MultipartFile file, String courseId) throws IOException {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found !!"));
        String path = fileService.save(file, AppConstants.COURSE_BANNER_UPLOAD_DIR,file.getOriginalFilename());

        course.setBanner(path);
        System.out.println(file.getContentType() + "......................................");
        course.setBannerContentType(file.getContentType());
        Course savedCourse = courseRepo.save(course);
        return modelMapper.map(savedCourse,CourseDto.class);
    }

    @Override
    public ResourceContentType getCourseBannerById(String courseId){
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found !!"));
        String bannerPath = course.getBanner();
        Path path = Paths.get(bannerPath);
        Resource resource = new FileSystemResource(path);
        ResourceContentType resourceContentType = new ResourceContentType();
        resourceContentType.setContentType(course.getBannerContentType());
        resourceContentType.setResource(resource);
        return resourceContentType;
    }

//    public CourseDto entityToDto(Course course){
//        CourseDto courseDto = new CourseDto();
//        courseDto.setId(course.getId());
//        courseDto.setTitle(course.getTitle());
//
//         do rest of the fields.
//         for this problem , we have modelMapping;
//
//
//        CourseDto courseDto = modelMapper.map(course,CourseDto.class);
//
//        return courseDto;
//    }

//    public Course dtoToEntity(CourseDto courseDto){
//        Course course = new Course();
//        course.setId(dto.getId());
//
//         do rest of the fields/.
//         for this problem , we have modelMapping;
//
//        Course course = modelMapper.map(courseDto,Course.class);
//        return course;
//    }
}
