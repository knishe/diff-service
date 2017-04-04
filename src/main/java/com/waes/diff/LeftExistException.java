package com.waes.diff;

/**
 *
 * Throw when there is already a data in the left
 * @author Nishanthan Krishnakumar
 * @version 1.0
 */
public class LeftExistException extends Exception {
    @Override
    public String getMessage() {
        return "Left value alread exist";
    }
}
