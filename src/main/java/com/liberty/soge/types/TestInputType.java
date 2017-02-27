package com.liberty.soge.types;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

import com.liberty.soge.annotation.Validatable;

import lombok.Data;

@Data
@Validatable
public class TestInputType {
    
    @NotNull
    @Email
    private String message;
    
    @Range(min = 3, max = 9)
    private int num;
    
}
