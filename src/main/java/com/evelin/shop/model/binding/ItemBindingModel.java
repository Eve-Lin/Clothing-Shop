package com.evelin.shop.model.binding;

import com.evelin.shop.model.entity.Category;
import com.evelin.shop.model.entity.CategoryName;
import com.evelin.shop.model.entity.Gender;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemBindingModel {
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryName category;
    private Gender gender;

    public ItemBindingModel() {
    }

    @Length(min=3, message = "Name must be more than 2 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min=3, message = "Description must be more than 3 characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DecimalMin(value="0", message = "Enter valid price!")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull(message = "Enter valid category name!")
    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }
//    @Length(min=2, message = "Enter valid gender")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
