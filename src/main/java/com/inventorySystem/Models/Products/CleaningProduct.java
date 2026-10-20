package com.inventorySystem.Models.Products;

import com.inventorySystem.Contracts.Discountable;
import com.inventorySystem.Errors.ArgumentException;
import com.inventorySystem.Models.Product;

public class CleaningProduct extends Product implements Discountable {
    private boolean isFlammable;

    public CleaningProduct(String code, String name, double basePrice, int stock, boolean isFlammable) {
        super(code, name, basePrice, stock);
        this.isFlammable = isFlammable;
    }

    public boolean isFlammable() {
        return isFlammable;
    }

    public void setFlamable(boolean isFlammable) {
        this.isFlammable = isFlammable;
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
        return getBasePrice() * 0.15;
    }

    @Override
    public String toString() {
        return String.format("%s | Flammable: %s", super.toString(), isFlammable() ? "Yes" : "No");
    }

}
