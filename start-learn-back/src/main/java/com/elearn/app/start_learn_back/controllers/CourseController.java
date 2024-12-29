package com.elearn.app.start_learn_back.controllers;

import com.elearn.app.start_learn_back.dtos.CategoryDto;
import com.elearn.app.start_learn_back.dtos.CourseDto;
import com.elearn.app.start_learn_back.entities.Course;
import com.elearn.app.start_learn_back.services.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
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

}
