package com.example.springwebtask.service;

import com.example.springwebtask.dao.ManagementDao;
import com.example.springwebtask.entity.CategoryRecord;
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
    public List<ProductRecord> findAll() {
        return mgmtDao.findAll();
    }

    @Override
    public List<ProductRecord> findByName(String name) {
        return mgmtDao.findByName(name);
    }

    @Override
    public List<CategoryRecord> findCategories() {
        return mgmtDao.findCategories();
    }

   @Override
   public int insert(ProductRecord productAddRecord) { return mgmtDao.insert(productAddRecord); }
}
