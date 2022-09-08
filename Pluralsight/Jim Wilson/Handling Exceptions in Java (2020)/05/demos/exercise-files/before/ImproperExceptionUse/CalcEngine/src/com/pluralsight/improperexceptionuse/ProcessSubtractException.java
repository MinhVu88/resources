package com.pluralsight.improperexceptionuse;

/*
This exception is being used IMPROPERLY in the app code because
the exception class is not used to indicate an error or other
exceptional scenario.

Rather this exception is used to indicate when an SUBTRACT OPERATION
needs to perform (a valid and non-exceptional part of the
applications standard behavior).

This exception is used to perform standard control flow within
the normal behavior of the app. This is NOT THE RIGHT WAY to use exceptions.
 */
public class ProcessSubtractException extends Exception {
    private int leftVal;
    private int rightVal;

    public ProcessSubtractException(int leftVal, int rightVal) {
        this.leftVal = leftVal;
        this.rightVal = rightVal;
    }
    public int getLeftVal() {
        return leftVal;
    }

    public int getRightVal() {
        return rightVal;
    }


}
