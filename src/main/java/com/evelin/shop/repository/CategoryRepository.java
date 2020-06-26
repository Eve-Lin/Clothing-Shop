package com.evelin.shop.repository;

import com.evelin.shop.model.entity.Category;
import com.evelin.shop.model.entity.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByName(CategoryName categoryName);
}
