package com.waes.diff;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author Nishanthan Krishnakumar
 * @version 1.0
 */
public class Result {
    private boolean areEqual;
    private boolean areSizeEqual;
    private List<Diff> diffList;

    public Result() {
    }

    public Result(boolean areEqual, boolean areSizeEqual) {
        this.areEqual = areEqual;
        this.areSizeEqual = areSizeEqual;
    }

    public void setDiffList(List<Diff> diffList) {
        this.diffList = diffList;
    }

    public boolean isAreEqual() {
        return areEqual;
    }

    public boolean isAreSizeEqual() {
        return areSizeEqual;
    }

    public List<Diff> getDiffList() {
        return diffList;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
