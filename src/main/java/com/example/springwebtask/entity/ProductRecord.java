package com.example.springwebtask.entity;

public record ProductRecord(Integer id, String productId, String category, String name,
                            Integer price, String imgPath, String description,
                            String createdRd, String createdUd) { }
