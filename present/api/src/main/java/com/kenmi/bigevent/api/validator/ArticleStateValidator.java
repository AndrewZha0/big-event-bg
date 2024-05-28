package com.kenmi.bigevent.api.validator;


import com.kenmi.bigevent.api.annotation.ArticleState;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ArticleStateValidator implements ConstraintValidator<ArticleState, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.equals("已发布") || s.equals("草稿");
    }
}
