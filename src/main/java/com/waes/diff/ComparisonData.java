package com.waes.diff;

/**
 * @author Nishanthan Krishnakumar
 * @version 1.0
 *          <p>
 *          This holds the left and right data which needs to be differentiated
 */
public class ComparisonData<T> {
    private String id;
    private T right;
    private T left;

    public ComparisonData(String id) {
        this.id = id;
    }

    public void setRight(T right) {
        this.right = right;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public String getId() {
        return id;
    }

    public T getRight() {
        return right;
    }

    public T getLeft() {
        return left;
    }

    boolean isRightEmpty() {
        return right == null;
    }

    boolean isLeftEmpty() {
        return left == null;
    }
}
