package com.example.springwebtask.controller;

import com.example.springwebtask.entity.CategoryRecord;
import com.example.springwebtask.entity.Product;
import com.example.springwebtask.entity.ProductRecord;
import com.example.springwebtask.service.ManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class ManagementRestController {

    @Autowired
    ManagementService mgmtService;

    @GetMapping("/api/product-list")
    public List<Product> getProducts() {
        return mgmtService.findAll();
    }

    @GetMapping("/api/search-list")
    public List<Product> search(@RequestParam("name") String name) {
        return mgmtService.findByName(name);
    }

    @GetMapping("/api/category-list")
    public List<CategoryRecord> findCategories() {
        return mgmtService.findCategories();
    }

    @PostMapping("/api/product")
    public ResponseEntity<String> add(@RequestBody() @Validated Product productAdd, BindingResult bindingResult) {
        //System.out.println(productAdd);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            for (FieldError error : bindingResult.getFieldErrors()) {
                switch (error.getField()) {
                    case "productId" -> errorMessage.append("商品ID：" ).append(error.getDefaultMessage()).append(",");
                    case "name" -> errorMessage.append("商品名：" ).append(error.getDefaultMessage()).append(",");
                    case "price" -> errorMessage.append("単価：" ).append(error.getDefaultMessage()).append(",");
                    //case "imagePath" -> errorMessage.append("画像：" ).append(error.getDefaultMessage()).append(",");
                    case "description" -> errorMessage.append("詳細説明：" ).append(error.getDefaultMessage()).append(",");
                    case "category" -> errorMessage.append("カテゴリ：" ).append(error.getDefaultMessage()).append(",");
                }
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());

        } else {
            //System.out.println("SUCCESS" + productAdd.getimagePath());

            //重複エラーチェック
            var check = mgmtService.findByProduct(productAdd.getProductId());
            if (check == null) {
                mgmtService.insert(productAdd);
                return ResponseEntity.ok("/menu");
            } else {
                return ResponseEntity.badRequest().body("商品IDが重複しています");
            }

        }

    }

    @PostMapping("/api/upload-image")
    public String uploadImage(@RequestParam("image") MultipartFile image,
                              @RequestParam("id") String id) {
        System.out.println("イメージ" + id);

        // 画像を保存するディレクトリのパスを指定します
        String uploadDir = "C:\\Users\\axiz\\IdeaProjects\\springwebtask\\src\\main\\resources\\static\\image";

        try {
            // 画像ファイルを保存します
            File file = new File(uploadDir, id + ".png");
            image.transferTo(file);
            return "画像が正常にアップロードされました";
        } catch (IOException e) {
            e.printStackTrace();
            return "画像のアップロード中にエラーが発生しました";
        }
    }

    @PutMapping("/api/update")
    public ResponseEntity<String> update(@RequestBody() @Validated Product productUpdate, BindingResult bindingResult) {
        //System.out.println(productUpdate);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            for (FieldError error : bindingResult.getFieldErrors()) {
                switch (error.getField()) {
                    case "productId" -> errorMessage.append("商品ID：" ).append(error.getDefaultMessage()).append(",");
                    case "name" -> errorMessage.append("商品名：" ).append(error.getDefaultMessage()).append(",");
                    case "price" -> errorMessage.append("単価：" ).append(error.getDefaultMessage()).append(",");
                    //case "imagePath" -> errorMessage.append("画像：" ).append(error.getDefaultMessage()).append(",");
                    case "description" -> errorMessage.append("詳細説明：" ).append(error.getDefaultMessage()).append(",");
                    case "category" -> errorMessage.append("カテゴリ：" ).append(error.getDefaultMessage()).append(",");
                }
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());

        } else {
            //System.out.println("SUCCESS" + productAdd.getimagePath());

            //選択した商品のid以外で同じ商品IDがある場合(重複エラーチェック)
            var product = mgmtService.findByProduct(productUpdate.getId(), productUpdate.getProductId());

            if (product == null) {  //変更前の商品IDの時は、大丈夫
                mgmtService.update(productUpdate);
                return ResponseEntity.ok("/menu");
            } else {
                return ResponseEntity.badRequest().body("商品IDが重複しています");
            }

        }

    }

    @GetMapping("/api/id")
    public Product getProduct(@RequestParam("productId") String productId) {
        return mgmtService.findByProduct(productId);
    }

    @DeleteMapping("/api/delete")
    public int delete(@RequestParam("id") String productId) {
        return mgmtService.delete(productId);
    }

}
