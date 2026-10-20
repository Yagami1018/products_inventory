package com.inventorySystem.Models.Products;

import java.time.LocalDate;

import com.inventorySystem.Contracts.Discountable;
import com.inventorySystem.Errors.ArgumentException;
import com.inventorySystem.Models.Product;

public class Food extends Product implements Discountable {
    private LocalDate expireDate;

    public Food(String code, String name, double basePrice, int stock, LocalDate expireDate) {
        super(code, name, basePrice, stock);
        this.expireDate = expireDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public void applyDiscount(double percent) {
        if (percent <= 0) {
            throw new ArgumentException("Error: Invalid argument, percent can't be 0 or negative.");
        }
        double discount = getBasePrice() * percent / 100;
        setBasePrice(getBasePrice() - discount);
    }

    @Override
    public double calculateTaxes() {
        return getBasePrice() * 0.1;
    }

    @Override
    public String toString() {
        return String.format("%s | Expiration Date: %s", super.toString(), expireDate);
    }

}
