package com.inventorySystem.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.inventorySystem.Models.Products.CleaningProduct;
import com.inventorySystem.Models.Products.Electronic;
import com.inventorySystem.Models.Products.Food;

public class Inventory<T extends Product> {
    private List<T> products;

    public Inventory() {
        products = new ArrayList<>();
    }

    public List<T> getProducts() {
        return products;
    }

    public void addProduct(T product) {
        products.add(product);
    }

    @SafeVarargs
    public final void addProducts(T... products) {
        for (T prod : products) {
            this.products.add(prod);
        }
    }

    public boolean removeProduct(String code) {
        for (T product : products) {
            if (product.getCode().equals(code)) {
                return products.remove(product);
            }
        }
        return false;
    }

    public T findByCode(String code) {
        for (T product : products) {
            if (product.getCode().equals(code)) {
                return product;
            }
        }
        return null;
    }

    public Map<String, Double> calculateInventoryTotalValue() {
        Map<String, Double> values = new HashMap<>();
        double electronicsVal = 0;
        double cleaningVal = 0;
        double foodsVal = 0;
        for (T product : products) {
            switch (product) {
                case Electronic e:
                    electronicsVal += e.calculateFinalPrice() * e.getStock();
                    break;
                case CleaningProduct cp:
                    cleaningVal += cp.calculateFinalPrice() * cp.getStock();
                    break;
                case Food f:
                    foodsVal += f.calculateFinalPrice() * f.getStock();
                    break;
                default:
            }
        }
        values.put("electronics", electronicsVal);
        values.put("cleaning", cleaningVal);
        values.put("foods", foodsVal);
        values.put("total", electronicsVal + cleaningVal + foodsVal);
        return values;
    }

    public List<T> listAvailables() {
        List<T> availables = new ArrayList<>();
        for (T product : products) {
            if (product.isAvailable()) {
                availables.add(product);
            }
        }
        return availables;
    }

    public void showProducts() {
        StringBuilder electronics = new StringBuilder();
        StringBuilder cleaning = new StringBuilder();
        StringBuilder foods = new StringBuilder();

        for (T prod : products) {
            switch (prod) {
                case Electronic e:
                    electronics.append(e).append("\n");
                    break;
                case Food f:
                    foods.append(f).append("\n");
                    break;
                case CleaningProduct cp:
                    cleaning.append(cp).append("\n");
                    break;
                default:
            }
        }

        System.out.println("""
                === Inventory ===

                Electronic Products:
                %s
                Cleaning Products:
                %s
                Foods:
                %s""".formatted(electronics.toString(), cleaning.toString(), foods.toString()));
    }
}
