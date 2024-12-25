package com.example.spring_jpa_ecom.controller;

import com.example.spring_jpa_ecom.entities.Category;
import com.example.spring_jpa_ecom.models.CategoryCreateRequest;
import com.example.spring_jpa_ecom.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @RequestMapping("/category")
    public List<Category> allCategories(){
        return categoryService.getAll();
    }

    @RequestMapping("/{categoryId}")
    public Category getOne(@PathVariable("categoryId") int categoryId){
        return categoryService.getById(categoryId);
    }

    // By default takes class level url path and GET http method.
    @RequestMapping("/query")
    public List<Category> getCategoryList(
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNumber",required = false,defaultValue = "0") int pageNumber
    ){
        System.out.println("PageNumber is :- " + pageNumber);
        System.out.println("PageSize is :- " + pageSize);
        return null;
    }

    //create category
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Category createCategory(@RequestBody CategoryCreateRequest categoryCreateRequest){   // Like DTO class object which is holding incoming request json data.
        System.out.println(categoryCreateRequest);
        Category cat = new Category();
        cat.setTitle(categoryCreateRequest.getTitle());
        return categoryService.create(cat);
    }

    @PostMapping("/image")
    public String imageUploader(@RequestParam("file") MultipartFile file){
        System.out.println(file.getOriginalFilename());
        return file.getOriginalFilename() + " uploaded to server successfully..";
    }


    //Controller level exception handler.
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> handleException(RuntimeException ex){
//        System.out.println(ex.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error occured :- " + ex.getMessage());
//    }

}

