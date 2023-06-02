package com.example.springwebtask.service;

import com.example.springwebtask.dao.ManagementDao;
import com.example.springwebtask.entity.CategoryRecord;
import com.example.springwebtask.entity.Product;
import com.example.springwebtask.entity.ProductRecord;
import com.example.springwebtask.entity.UsersRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MgmtService implements ManagementService {

    @Autowired
    private ManagementDao mgmtDao;

    @Override
    public UsersRecord findById(String id) {
        return mgmtDao.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return mgmtDao.findAll();
    }

    @Override
    public List<Product> findByName(String name) {
        return mgmtDao.findByName(name);
    }

    @Override
    public Product findByProduct(String id) { return mgmtDao.findByProduct(id);}

    @Override
    public List<CategoryRecord> findCategories() {
        return mgmtDao.findCategories();
    }

    @Override
    public int insert(Product productAdd) { return mgmtDao.insert(productAdd); }

    @Override
    public int update(Product productUpdate) { return mgmtDao.update(productUpdate); }

    @Override
    public Product findByProduct(Integer id, String productId) { return  mgmtDao.findByProduct(id, productId); }

    @Override
    public int delete(String productId) { return mgmtDao.delete(productId); }
}
