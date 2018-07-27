package org.currency.convert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * This implementation of ConvertService is purely for testing purposes, as we
 * have a limit on calling fixture.io
 */
@Primary
@Service
public class DummyConverter implements ConvertService {
    private static final Logger log = LoggerFactory.getLogger(DummyConverter.class);

    // let's just read the contents of the fixture file once per instance of dummy
    // converter
    private String jsonRates = "";

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }

    /**
     * Fill in the json representation of the conversion rates
     */
    @PostConstruct
    private void readRates() {
        InputStream inputStream = DummyConverter.class.getResourceAsStream("/fixture.json");
        try {
            jsonRates = readFromInputStream(inputStream);
        } catch (IOException ex) {
            log.error("Could not read json from fixture: {}", ex);
        }
    }

	@Override
	public String getJsonRates() {
		return jsonRates;
	}
}