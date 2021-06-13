package com.github.tmquotebot.searchspider.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = QuoteFilterValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@interface ValidFilter {

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
