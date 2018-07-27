package org.currency.convert;

import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The main REST controller, it answers to GET requests under the '/convert'
 * path.
 * 
 * Mandatory GET parameters:
 * <ul>
 * <li>source - the source currency symbol (currently one of ['JPY', 'EUR', or
 * 'USD'])</li>
 * <li>target - the target currency symbol (the same restriction as source, one
 * of ['JPY', 'EUR', or 'USD'])</li>
 * <li>amount - the amount to convert from source to target currency</li>
 * <ul>
 */
@RestController
@Validated
public class ConvertController {
    private static final Logger log = LoggerFactory.getLogger(ConvertController.class);

    @Autowired
    ConvertService converter;

    /**
     * This method is the entry point for the currency conversion.
     * 
     * @param sourceSymbol the Currency Symbol for the source currency to convert
     *                     from. Validated by the SymbolValidator class via the
     *                     IsValidCurrencySymbol annotation.
     * @param targetSymbol the Currency Symbol for the target currency to convert
     *                     to. Validated by the SymbolValidator class via the
     *                     IsValidCurrencySymbol annotation.
     * @param amount       the Amount of currency to convert. Should be a number
     *                     greater than 0.
     * @return a ConvertResponse that has a timestamp, the source and target
     *         currency symbols and the 'value' field containing the result of the
     *         conversion
     */
    @RequestMapping(value = "/convert", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public ConvertResponse doConversion(
            @IsValidCurrencySymbol @RequestParam(value = "source", required = true) String sourceSymbol,
            @IsValidCurrencySymbol @RequestParam(value = "target", required = true) String targetSymbol,
            @Min(value = 0, message = "Amount must be greater than or equal to 0") @RequestParam(value = "amount", required = true) double amount) {
        log.trace("Received a new request for a conversion! From [{}] to [{}] amount [{}] ", sourceSymbol, targetSymbol,
                amount);
        return converter.convert(sourceSymbol, targetSymbol, amount);
    }
}