package com.example.springwebtask.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginForm {

    @NotEmpty
    @Length(min = 1, max = 20)
    private String id;

    @NotEmpty
    @Length(min = 1, max = 50)
    private String pass;

}
