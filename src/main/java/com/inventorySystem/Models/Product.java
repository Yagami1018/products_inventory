package com.inventorySystem.Models;

import com.inventorySystem.Contracts.Sellable;
import com.inventorySystem.Errors.ArgumentException;

public abstract class Product implements Sellable {
    private String code;
    private String name;
    private double basePrice;
    private int stock;

    public Product(String code, String name, double basePrice, int stock) {
        if (basePrice <= 0 || stock <= 0) {
            String errType = basePrice <= 0 ? "Price" : "Stock";
            throw new ArgumentException("Error: Invalid Argument, %s can't be 0 or negative.".formatted(errType));
        }
        this.code = code;
        this.name = name;
        this.basePrice = basePrice;
        this.stock = stock;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code.isBlank()) {
            throw new ArgumentException("Error: Invalid Argument, code can't be empty.");
        }
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isBlank() || name.length() <= 2) {
            String err = name.isBlank() ? "Error: Invalid Argument, name can't be empty." : "Error: Name is too short.";
            throw new ArgumentException(err);
        }
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        if (basePrice <= 0) {
            throw new ArgumentException("Error: Invalid Argument, base price can't be 0 or negative");
        }
        this.basePrice = basePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock <= 0) {
            throw new ArgumentException("Error: Invalid Argument, stock can't be 0 or negative");
        }
        this.stock = stock;
    }

    public abstract double calculateTaxes();

    public double calculateFinalPrice() {
        return basePrice + calculateTaxes();
    }

    public boolean isAvailable() {
        return stock > 0 ? true : false;
    }

    @Override
    public String toString() {
        return String.format("Type of Product: %s | Code: %s | Name: %s | Final Price: $%.2f | Quantity: %d",
                this.getClass().getSimpleName(), code, name, calculateFinalPrice(), stock);
    }
}
