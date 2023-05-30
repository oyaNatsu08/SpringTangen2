package com.example.springwebtask.service;

import com.example.springwebtask.entity.CategoryRecord;
import com.example.springwebtask.entity.ProductRecord;
import com.example.springwebtask.entity.UsersRecord;

import java.util.List;

public interface ManagementService {

    UsersRecord findById(String id);

    List<ProductRecord> findAll();

    List<ProductRecord> findByName(String name);

    List<CategoryRecord> findCategories();

}
