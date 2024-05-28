package com.kenmi.bigevent.api.annotation;

import com.kenmi.bigevent.api.validator.ArticleStateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {ArticleStateValidator.class}
)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ArticleState {
    String message() default "文章状态只能是\"已发布\" 或者 \"草稿\"";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
