package com.elearn.app.start_learn_back.services.impl;

import com.elearn.app.start_learn_back.dtos.CategoryDto;
import com.elearn.app.start_learn_back.dtos.CustomPageResponse;
import com.elearn.app.start_learn_back.entities.Category;
import com.elearn.app.start_learn_back.exceptions.ResourceNotFoundException;
import com.elearn.app.start_learn_back.repositories.CategoryRepo;
import com.elearn.app.start_learn_back.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;
    private ModelMapper modelMapper;

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
}
