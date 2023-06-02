package com.example.springwebtask.dao;

import com.example.springwebtask.entity.CategoryRecord;
import com.example.springwebtask.entity.Product;
import com.example.springwebtask.entity.ProductRecord;
import com.example.springwebtask.entity.UsersRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MgmtDao implements ManagementDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public UsersRecord findById(String id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        var list = jdbcTemplate.query("SELECT id, login_id, password, name, role, created_rd, created_ud FROM users WHERE login_id = :id", param,
                new DataClassRowMapper<>(UsersRecord.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Product> findAll() {
        var list = jdbcTemplate.query("SELECT p.id, p.product_id AS productId, c.name AS category, p.name, p.price, " +
                        "p.image_path, p.description, p.created_rd, p.created_ud FROM products AS p " +
                        "LEFT OUTER JOIN categories AS c ON p.category_id = c.id",
                new DataClassRowMapper<>(Product.class));
        return list;
    }

    @Override
    public List<Product> findByName(String name) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", "%" + name + "%");
        var list = jdbcTemplate.query("SELECT p.id, p.product_id AS productId, p.name, p.price, c.name AS category," +
                        "p.image_path, p.description, p.created_rd, p.created_ud FROM products AS p " +
                "LEFT OUTER JOIN categories AS c ON p.category_id = c.id WHERE p.name LIKE :name", param,
                new DataClassRowMapper<>(Product.class));
        return list;
    }

    @Override
    public Product findByProduct(String id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        List<Product> list = jdbcTemplate.query("SELECT id, product_id, category_id AS category, name, price, " +
                "image_path, description, created_rd, created_ud FROM products WHERE product_id = :id", param,
                new DataClassRowMapper<>(Product.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<CategoryRecord> findCategories() {
        return jdbcTemplate.query("SELECT id, name, created_rd, created_ud FROM categories", new DataClassRowMapper<>(CategoryRecord.class));
    }

    @Override
    public int insert(Product productAdd) {
        //category_idを受け取る
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("category", productAdd.getCategory());

        Integer categoryId = jdbcTemplate.queryForObject("SELECT id FROM categories WHERE name = :category", param, Integer.class);
        //System.out.println(categoryId);

        param = new MapSqlParameterSource();
        param.addValue("id", productAdd.getProductId());
        param.addValue("name", productAdd.getName());
        param.addValue("categoryId", categoryId);
        param.addValue("price", productAdd.getPrice());
        param.addValue("description", productAdd.getDescription());
        param.addValue("img", productAdd.getimagePath());

        return jdbcTemplate.update("INSERT INTO products(product_id, category_id, name, price, image_path, description, created_rd, created_ud)" +
                "VALUES(:id, :categoryId, :name, :price, :img, :description, now(), now())", param);

    }

    @Override
    public int update(Product productUpdate) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("category", productUpdate.getCategory());

        Integer categoryId = jdbcTemplate.queryForObject("SELECT id FROM categories WHERE name = :category", param, Integer.class);
        //System.out.println(categoryId);

        param = new MapSqlParameterSource();
        param.addValue("id", productUpdate.getId());
        param.addValue("productId", productUpdate.getProductId());
        param.addValue("name", productUpdate.getName());
        param.addValue("categoryId", categoryId);
        param.addValue("price", productUpdate.getPrice());
        param.addValue("description", productUpdate.getDescription());
        param.addValue("img", productUpdate.getimagePath());

        return jdbcTemplate.update("UPDATE products SET product_id = :productId, category_id = :categoryId, name = :name, price = :price, " +
                "image_path = :img, description = :description WHERE id = :id", param);
    }

    @Override
    public Product findByProduct(Integer id, String productId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        //System.out.println(id);
        param.addValue("id", id);
        param.addValue("productId", productId);
        List<Product> list = jdbcTemplate.query("SELECT id, product_id, category_id AS category, name, price, " +
                        "image_path, description, created_rd, created_ud FROM products WHERE id <> :id AND product_id = :productId",
                        param, new DataClassRowMapper<>(Product.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int delete(String productId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("productId", productId);
        return jdbcTemplate.update("DELETE FROM products WHERE product_id = :productId", param);
    }

}
