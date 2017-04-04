package com.waes.diff;

/**
 * Throw when aleady a data exist on the right
 * @author Nishanthan Krishnakumar
 * @version 1.0
 */
public class RightExistException extends Exception {
    @Override
    public String getMessage() {
        return "Right value is alread exist";
    }
}
