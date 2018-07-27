package org.currency.convert;

import java.util.Date;

/**
 * ConvertResponse is an immutable that contains the response of any requested conversion.
 */
public final class ConvertResponse {
    /**
     * Timestamp field makes sure that no 2 ConvertResponse instances are equal, 
     * in line with currency conversion.
     * It also provides a possiblity to build a nice timeseries to see the evolution over time of certain currencies.
     */
    private final Date timestamp;
    private final String sourceSymbol;
    private final String targetSymbol;
    // here we use double to increase precision and have fewer rounding errors.
    private final double value;

    public ConvertResponse(Date timestamp, String sourceSymbol, String targetSymbol, double value) {
        this.timestamp = timestamp;
        this.sourceSymbol = sourceSymbol;
        this.targetSymbol = targetSymbol;
        this.value = value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getSourceSymbol() {
        return sourceSymbol;
    }

    public String getTargetSymbol() {
        return targetSymbol;
    }

    public double getValue() {
        return value;
    }
}