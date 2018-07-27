package org.currency.convert;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * This annotation is used for the validation of the currency symbols provided
 * for source and target in the GET request
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { SymbolValidator.class })
public @interface IsValidCurrencySymbol {
    String message() default "Bad currency symbol! Expected one of 'USD', 'JPY' or 'EUR'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}