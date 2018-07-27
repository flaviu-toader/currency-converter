package org.currency.convert;

import java.util.Date;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import org.springframework.stereotype.Service;

/**
 * We implement this interface to either read conversion rates from a fixture
 * file (for testing) or from fixer.io.
 */
@Service
public interface ConvertService {

    String getJsonRates();

    default ConvertResponse convert(String source, String target, double amount) {
        String jsonRates = getJsonRates();
        if(jsonRates == null || jsonRates.isEmpty()) {
            return null;
        }
        ReadContext ctx = JsonPath.parse(jsonRates);
        Double sourceRate = ctx.read("$.rates." + source, Double.class);
        Double targetRate = ctx.read("$.rates." + target, Double.class);
        double convertedValue = (amount * targetRate) / sourceRate;
        return new ConvertResponse(new Date(), source, target, convertedValue);
    }
}