package com.evelin.shop.service;

import com.evelin.shop.model.entity.Category;
import com.evelin.shop.model.entity.CategoryName;
import com.evelin.shop.model.service.CategoryServiceModel;

public interface CategoryService {
    void initCategories();
    CategoryServiceModel findByCategoryName(CategoryName categoryName);
    Category find(CategoryName categoryName);
}
