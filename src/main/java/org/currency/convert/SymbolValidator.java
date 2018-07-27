package org.currency.convert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class performs validation of the currency symbols provided as GET
 * parameters to the controller.
 * 
 * Currently we only support conversions between the following currencies:
 * 
 * <ul>
 * <li>EUR - Euros</li>
 * <li>JPY - Japanese Yen</li
 * <li>USD - US dollars</li>
 * </ul>
 */
public class SymbolValidator implements ConstraintValidator<IsValidCurrencySymbol, String> {

    private static final List<String> validSymbols = Collections.unmodifiableList(Arrays.asList("USD", "EUR", "JPY"));

    public static List<String> getValidSymbols() {
        return validSymbols;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validSymbols.contains(value.toUpperCase());
    }

}