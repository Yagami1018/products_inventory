package com.inventorySystem.Contracts;

public interface Sellable {
    double calculateFinalPrice();
    boolean isAvailable();
}
