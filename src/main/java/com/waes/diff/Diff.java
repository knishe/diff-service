package com.waes.diff;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

class Diff {
    private int length;
    private int offset;

    public Diff() {
    }

    public Diff(int length, int offset) {
        this.length = length;
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
