package com.inventorySystem.Utils;

import static com.inventorySystem.Utils.Validators.validateOption;

import java.time.LocalDate;
import java.util.Map;

import com.inventorySystem.Contracts.Discountable;
import com.inventorySystem.Models.Inventory;
import com.inventorySystem.Models.Product;
import com.inventorySystem.Models.Products.CleaningProduct;
import com.inventorySystem.Models.Products.Electronic;
import com.inventorySystem.Models.Products.Food;

public class Helpers {

    private Helpers() {
    }

    // Método para cargar 2 productos de cada tipo al inventario.
    public static void loadInventory(Inventory<Product> inventory) {
        Product phone = new Electronic("E01", "Poco M5S", 150.00, 20, 12);
        Product tv = new Electronic("E02", "LG 41\"TV", 300.00, 31, 18);

        Product tomato = new Food("F01", "Tomato", 2.00, 400, LocalDate.now().plusDays(21));
        Product chicken = new Food("F02", "Whole Raw Chicken", 10.00, 243, LocalDate.now().plusYears(1));

        Product soap = new CleaningProduct("C01", "Bath Soap", 5.00, 23, false);
        Product acetone = new CleaningProduct("C02", "Acetone", 6.00, 39, true);

        inventory.addProducts(phone, tv, tomato, chicken, soap, acetone);
    }

    // Submenú para aplicar descuentos al tipo de productos que se desee.
    public static void applyDiscounts(Map<String, Product> products) {
        IO.println("""
                === Apply discounts: ===
                1) Cleaning products
                2) Electronic products
                3) Food products
                4) Back
                """);
        int discountOption = validateOption("Products to discount: ", 1, 4);
        if (discountOption == 4) {
            return;
        }
        int quantToDiscount = validateOption("Apply Discount in (1-99) percent: ", 1, 99);
        String targetCategory = switch (discountOption) {
            case 1 -> "cleaning";
            case 2 -> "electronics";
            case 3 -> "foods";
            default -> "";
        };

        products.values().stream().filter(p -> targetCategory.equals(p.getCategory())).forEach(p -> {
            if (p instanceof Discountable d)
                d.applyDiscount(quantToDiscount);
        });
        IO.println("Discounts applied succesfully.\n");
    }
}
