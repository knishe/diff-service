package com.waes.diff;

/**
 * Exception to throw when a comparison data not exist by the id
 *
 * @author Nishanthan Krishnakumar
 * @version 1.0
 */
public class ComparisonNotFoundException extends Exception {
    private String id;
    public ComparisonNotFoundException(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return "Comparison data not found for :" + id;
    }
}
