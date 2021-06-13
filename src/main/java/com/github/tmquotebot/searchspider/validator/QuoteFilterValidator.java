package com.github.tmquotebot.searchspider.validator;

import com.github.tmquotebot.searchspider.dto.QuoteFilter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class QuoteFilterValidator implements
        ConstraintValidator<ValidFilter, QuoteFilter>
{

    @Override
    public boolean isValid(QuoteFilter value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value.getFilter()) || !CollectionUtils.isEmpty(value.getTags());
    }
}
