package com.inventorySystem.Errors;

public class ArgumentException extends IllegalArgumentException {

    public ArgumentException() {
    }

    public ArgumentException(String errMsg) {
        super(errMsg);
    }
}