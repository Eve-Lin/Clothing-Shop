package com.evelin.shop.service.serviceImpl;

import com.evelin.shop.model.entity.Category;
import com.evelin.shop.model.entity.CategoryName;
import com.evelin.shop.repository.CategoryRepository;
import com.evelin.shop.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        this.categoryRepository.save(new Category(categoryName,
                                String.format("Description for %s",
                                        categoryName.name())));
                    });
        }
    }
}
