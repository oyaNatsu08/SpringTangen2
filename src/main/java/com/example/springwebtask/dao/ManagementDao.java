package com.example.springwebtask.dao;

import com.example.springwebtask.entity.CategoryRecord;
import com.example.springwebtask.entity.Product;
import com.example.springwebtask.entity.ProductRecord;
import com.example.springwebtask.entity.UsersRecord;

import java.util.List;

public interface ManagementDao {

    UsersRecord findById(String id);

    List<Product> findAll();

    List<Product> findByName(String name);

    List<CategoryRecord> findCategories();

    int insert(Product productAdd);

}
