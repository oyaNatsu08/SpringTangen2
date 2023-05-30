package com.example.springwebtask.form;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ProductForm {

    @NotEmpty
    @Length(min=1, max=20)
    private String productId;

    @NotEmpty
    @Length(min=1, max=255)
    private String name;

    @NotEmpty
    @Digits(integer = 7, fraction = 0)
    private Integer price;

    @NotEmpty
    private String category_id;

    @Length(max=2000)
    private String description;

    @NotEmpty
    private String imgPath;

}
