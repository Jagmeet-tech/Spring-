package com.elearn.app.start_learn_back.controllers;

import com.elearn.app.start_learn_back.config.AppConstants;
import com.elearn.app.start_learn_back.dtos.CategoryDto;
import com.elearn.app.start_learn_back.dtos.CourseDto;
import com.elearn.app.start_learn_back.dtos.CustomMessage;
import com.elearn.app.start_learn_back.dtos.ResourceContentType;
import com.elearn.app.start_learn_back.entities.Course;
import com.elearn.app.start_learn_back.services.CourseService;
import com.elearn.app.start_learn_back.services.FileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@CrossOrigin("*")
public class CourseController {


    private CourseService courseService;


    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable("id") String id, @RequestBody CourseDto courseDto){
        return ResponseEntity.ok(courseService.updateCourse(id,courseDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable("id") String id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping     // Taking pageable as argument , you can take pageNumber, pageSize,sortBy seperate as argument. But I have taken pageable because it will automatically hold that values in it.
    public ResponseEntity<Page<CourseDto>> getAllCourses(Pageable pageable){    // returning PageImpl<> as response
        return ResponseEntity.ok(courseService.getAllCourses(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id){
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseDto>> searchCourse(@RequestParam String keyword){
        return ResponseEntity.ok(courseService.searchCourses(keyword));
    }

    // going to create one banner image upload API.
    @PostMapping("/{courseId}/banners")
    public ResponseEntity<?> uploadBanner(@PathVariable String courseId
            , @RequestParam("banner") MultipartFile banner) throws IOException {

        System.out.println("Working controller.................................");

        if(!banner.getContentType().equals("image/png") || !banner.getContentType().equals("image/jpeg")){
            CustomMessage customMessage = new CustomMessage();
            customMessage.setMessage("Invalid file type.");
            customMessage.setSuccess(false);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customMessage);
        }

        CourseDto courseDto = courseService.saveBanner(banner,courseId);
        return ResponseEntity.ok(courseDto);
    }

    @GetMapping("/{courseId}/banners")
    public ResponseEntity<Resource> serveBanner(@PathVariable String courseId, HttpServletRequest httpServletRequest,
                                                @RequestHeader("Content-type") String contentType
                                                ){

        // you can get request object information thorugh HttpServletRequest class injected automatically.
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            System.out.println(header + " : " + httpServletRequest.getHeader(header));
        }


        ResourceContentType resourceContentType = courseService.getCourseBannerById(courseId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(resourceContentType.getContentType())).body(resourceContentType.getResource());
    }

}
