package com.example.springwebtask.entity;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Product {
    Integer id;

    @NotEmpty(message = "商品IDは必須です")
    @Length(min=1, max=20)
    String productId;

    @NotEmpty(message = "カテゴリ名は必須です")
    String category;

    @NotEmpty(message = "商品名は必須です")
    @Length(min=1, max=255)
    String name;

    @NotNull(message = "単価は必須です")
    @Digits(integer = 7, fraction = 0)
    Integer price;

//    @NotEmpty(message = "画像は必須です")
    String imagePath;

    @Length(max=2000)
    String description;

    String createdRd;

    String createdUd;

    public Product(Integer id, String productId, String category,
                   String name, Integer price, String imagePath, String description,
                   String createdRd, String createdUd) {
        this.id = id;
        this.productId = productId;
        this.category = category;
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.description = description;
        this.createdRd = createdRd;
        this.createdUd = createdUd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getimagePath() {
        return imagePath;
    }

    public void setimagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedRd() {
        return createdRd;
    }

    public void setCreatedRd(String createdRd) {
        this.createdRd = createdRd;
    }

    public String getCreatedUd() {
        return createdUd;
    }

    public void setCreatedUd(String createdUd) {
        this.createdUd = createdUd;
    }
}
