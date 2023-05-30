package com.example.springwebtask.controller;

import com.example.springwebtask.entity.CategoryRecord;
import com.example.springwebtask.entity.ProductRecord;
import com.example.springwebtask.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ManagementRestController {

    @Autowired
    ManagementService mgmtService;

    @GetMapping("/api/product-list")
    public List<ProductRecord> getProducts() {
        return mgmtService.findAll();
    }

    @GetMapping("/api/search-list")
    public List<ProductRecord> search(@RequestParam("name") String name) {
        return mgmtService.findByName(name);
    }

    @GetMapping("/api/category-list")
    public List<CategoryRecord> findCategories() {
        return mgmtService.findCategories();
    }

}
