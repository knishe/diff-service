package com.waes.diff;

/**
 * @author Nishanthan Krishnakumar
 * @version 1.0
 */
public class InCompleteComparisonDataException extends Exception {
    @Override
    public String getMessage() {
        return "Incomplete Comparison data found";
    }
}
