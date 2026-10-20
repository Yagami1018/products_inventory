package com.inventorySystem.Models.Products;

import com.inventorySystem.Contracts.Discountable;
import com.inventorySystem.Errors.ArgumentException;
import com.inventorySystem.Models.Product;

public class Electronic extends Product implements Discountable {
    private int warrantyMonths;

    public Electronic(String code, String name, double basePrice, int stock, int warrantyMonths) {
        if (warrantyMonths < 0) {
            throw new ArgumentException("Error: Invalid argument, warranty months value can't be negative");
        }
        super(code, name, basePrice, stock);
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        if (warrantyMonths < 0) {
            throw new ArgumentException("Error: Invalid argument, warranty months value can't be negative");
        }
        this.warrantyMonths = warrantyMonths;
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
        return getBasePrice() * 0.21;
    }

    @Override
    public String toString() {
        return String.format("%s | Warranty: %d", super.toString(), warrantyMonths);
    }
}
