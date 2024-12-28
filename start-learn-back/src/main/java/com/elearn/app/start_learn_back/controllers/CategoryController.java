package com.elearn.app.start_learn_back.controllers;

import com.elearn.app.start_learn_back.config.AppConstants;
import com.elearn.app.start_learn_back.dtos.CategoryDto;
import com.elearn.app.start_learn_back.dtos.CustomMessage;
import com.elearn.app.start_learn_back.dtos.CustomPageResponse;
import com.elearn.app.start_learn_back.entities.Category;
import com.elearn.app.start_learn_back.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //category create

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CategoryDto categoryDto
//            , BindingResult result
    ){

        // "?" is used in case we don;t able to decide which data type to return.
        // BindingResult is used to capture validation errors in the controllers so that we can return response according based on that.

//        if(result.hasErrors()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
//        }
//        System.out.println(categoryDto);

        CategoryDto createdDto = categoryService.insert(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    //get all categories
    @GetMapping
    public CustomPageResponse<CategoryDto> getAll(
            @RequestParam(value = "pageNumber",required = false,defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize",required = false,defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy",required = false,defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy
    ){
        return categoryService.getAll(pageNumber,pageSize,sortBy);
    }

    //get single category
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingle(@PathVariable("categoryId") String categoryId){
        CategoryDto categoryDto = categoryService.get(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
    }

    //delete category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CustomMessage> delete(@PathVariable("categoryId") String categoryId){
        categoryService.delete(categoryId);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("Category deleted");
        customMessage.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(customMessage);
    }

    //update category
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> update(@PathVariable("categoryId") String categoryId,@RequestBody CategoryDto categoryDto){
        CategoryDto updatedCategoryDto = categoryService.update(categoryDto,categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCategoryDto);
    }

}
