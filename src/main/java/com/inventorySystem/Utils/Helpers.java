package com.inventorySystem.Utils;

import static com.inventorySystem.Utils.Validators.validateOption;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.inventorySystem.Models.Inventory;
import com.inventorySystem.Models.Product;
import com.inventorySystem.Models.Products.CleaningProduct;
import com.inventorySystem.Models.Products.Electronic;
import com.inventorySystem.Models.Products.Food;

public class Helpers {

    private Helpers(){}

    //Método para cargar 2 productos de cada tipo al inventario.
    public static void loadInventory(Inventory<Product> inventory) {
        Product phone = new Electronic("1", "Poco M5S", 150.00, 20, 12);
        Product tv = new Electronic("2", "LG 41\"TV", 300.00, 31, 18);

        Product tomato = new Food("3", "Tomato", 2.00, 400, LocalDate.now().plusDays(21));
        Product chicken = new Food("4", "Whole Raw Chicken", 10.00, 243, LocalDate.now().plusYears(1));

        Product soap = new CleaningProduct("5", "Bath Soap", 5.00, 23, false);
        Product acetone = new CleaningProduct("6", "Acetone", 6.00, 39, true);

        inventory.addProducts(phone, tv, tomato, chicken, soap, acetone);
    }

    //Submenú para aplicar descuentos al tipo de productos que se desee.
    public static void applyDiscounts(Scanner sc, List<Product> products) {
        System.out.println("""
                === Apply discounts: ===
                1) Cleaning products
                2) Electronic products
                3) Food products
                4) Back
                """);
        int discountOption = validateOption(sc, "Products to discount: ", 1, 4);
        if (discountOption == 4) {
            return;
        }
        int quantToDiscount = validateOption(sc, "Apply Discount in (1-99) percent: ", 1, 99);
        for (Product product : products) {
            switch (discountOption) {
                case 1:
                    if (product instanceof CleaningProduct cp) {
                        cp.applyDiscount(quantToDiscount);
                    }
                    break;
                case 2:
                    if (product instanceof Electronic e) {
                        e.applyDiscount(quantToDiscount);
                    }
                    break;
                case 3:
                    if (product instanceof Food f) {
                        f.applyDiscount(quantToDiscount);
                    }
                    break;
            }
        }
        System.out.println("Discounts applied succesfully.\n");
    }
}
