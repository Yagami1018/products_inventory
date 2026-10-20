package com.inventorySystem.Models;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Inventory<T extends Product> {
    private Map<String, T> products;
    /*Defino un comparator para reordenar en los métodos de salida que muestran los productos al usuario, esto debido 
    a que hashmap no mantiene orden de inserción ni ordena alfabeticamente por defecto*/ 
    private Comparator<T> compareByCode = (a,b) -> a.getCode().compareTo(b.getCode());

    public Inventory() {
        products = new HashMap<>();
    }

    public Map<String, T> getProducts() {
        return products;
    }

    public void addProduct(T product) {
        products.put(product.getCode(), product);
    }

    @SafeVarargs
    public final void addProducts(T... products) {
        Arrays.stream(products).forEach(this::addProduct);
    }

    public boolean removeProduct(String code) {
        return products.remove(code, products.get(code));
    }

    public T findByCode(String code) {
        return products.get(code);
    }

    public Map<String, Double> calculateInventoryTotalValue() {
        Map<String, Double> values = products.values().stream().collect(Collectors.groupingBy(T::getCategory,
                Collectors.summingDouble(p -> p.calculateFinalPrice() * p.getStock())));

        double total = values.values().stream().reduce(0.0, (a, b) -> a + b);
        values.put("total", total);

        return values;
    }

    public List<T> listAvailables() {
        return products.values().stream().filter(T::isAvailable).sorted(compareByCode).toList();
    }

    public void showProducts() {
        Map<String, String> byCategory = products.values().stream().sorted(compareByCode).collect(Collectors.groupingBy(T::getCategory,
                Collectors.mapping(T::toString, Collectors.joining("\n", "", "\n"))));

        IO.println("""
                === Inventory ===

                Electronic Products:
                %s
                Cleaning Products:
                %s
                Foods:
                %s""".formatted(byCategory.getOrDefault("electronics", ""),
                byCategory.getOrDefault("cleaning", ""),
                byCategory.getOrDefault("foods", "")));
    }
}
