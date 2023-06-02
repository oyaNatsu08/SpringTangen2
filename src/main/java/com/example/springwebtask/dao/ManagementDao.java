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

    Product findByProduct(String id);

    List<CategoryRecord> findCategories();

    int insert(Product productAdd);

    int update(Product productUpdate);

    Product findByProduct(Integer id, String productId);

    int delete(String productId);

}
