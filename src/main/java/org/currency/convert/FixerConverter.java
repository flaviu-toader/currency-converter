package org.currency.convert;

import javax.xml.ws.WebServiceException;

import com.jayway.jsonpath.JsonPath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * This service uses the fixer.io api to retrieve the latest currency exchange
 * rates. It requires an access key for fixer.io to be provided via the property
 * `fixer.key`, which defaults to the value of the environment variable
 * "$FIXER_API_KEY"
 */
@Service
public class FixerConverter implements ConvertService {
    private static final Logger log = LoggerFactory.getLogger(FixerConverter.class);

    private static final String URL_LATEST = "http://data.fixer.io/api/latest";

    private static final String SYMBOLS = "symbols=" + String.join(",", SymbolValidator.getValidSymbols());

    @Value("${fixer.key}")
    private String apiKey;

    @Override
    public String getJsonRates() {
        final String uri = URL_LATEST + "?access_key=" + apiKey + "&" + SYMBOLS;
        log.info("Performing the following request to fixer API: [{}]", uri);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        // Fixer replies with a json that has a flag success and an error object when
        // success == false
        if (JsonPath.parse(result).read("$.success", Boolean.class) == false) {
            log.error("An error occurred when calling the Fixer API: {}", result);
            throw new WebServiceException(JsonPath.parse(result).read("$.error.info", String.class));
        }
        return result;
    }

}