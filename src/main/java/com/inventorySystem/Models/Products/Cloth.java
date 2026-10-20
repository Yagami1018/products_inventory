package com.inventorySystem.Models.Products;

import com.inventorySystem.Contracts.Discountable;
import com.inventorySystem.Errors.ArgumentException;
import com.inventorySystem.Models.Product;

public class Cloth extends Product implements Discountable {
    private SIZE size;
    private SEASON season;

    public enum SIZE {
        S, M, L, XL
    }

    public enum SEASON {
        SPRING, SUMMER, AUTUMN, WINTER
    }

    public Cloth(String code, String name, double basePrice, int stock, SIZE size, SEASON season) {
        super(code, name, basePrice, stock);
        setSize(size);
        setSeason(season);
    }

    public String getSize() {
        return switch (size) {
            case S -> "S";
            case M -> "M";
            case L -> "L";
            case XL -> "XL";
            default -> "";
        };
    }

    public void setSize(SIZE size) {
        if (size == null)
            throw new ArgumentException("Error: Invalid argument, size can't be null.");
        this.size = size;
    }

    public String getSeason() {
        return switch (season) {
            case SPRING -> "Spring";
            case SUMMER -> "Summer";
            case WINTER -> "Winter";
            case AUTUMN -> "Autmun";
            default -> "";
        };
    }

    public void setSeason(SEASON season) {
        if (season == null)
            throw new ArgumentException("Error: Invalid argument, season can't be null.");
        this.season = season;
    }

    @Override
    public String getCategory() {
        return "clothes";
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
        return getBasePrice() * 0.08;
    }

    @Override
    public String toString() {
        return String.format(java.util.Locale.US, "%s | Size: %s | Season: %s", super.toString(), getSize(), getSeason());
    }
}
