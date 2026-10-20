package com.inventorySystem.Utils;

import static com.inventorySystem.Utils.Helpers.applyDiscounts;
import static com.inventorySystem.Utils.Validators.validateOption;

import com.inventorySystem.Models.Inventory;
import com.inventorySystem.Models.Product;

public class Menu {
    private Inventory<Product> inventory;

    public Menu(Inventory<Product> inventory) {
        this.inventory = inventory;
    }

    public void showMenu() {
        int option = 6;
        do {
            IO.println("""
                    === Menu ===
                    1) Show products
                    2) Find by code
                    3) Show inventory total value
                    4) Apply discounts
                    5) Show available only
                    6) Show low stock only
                    7) Exit
                    """);

            option = validateOption("Choose an option: ", 1, 7);

            switch (option) {
                case 1 -> inventory.showProducts();
                case 2 -> {
                    Product p1 = inventory.findByCode(IO.readln("Find By Code: "));
                    String msg = p1 != null ? "%nProduct found %n%s %n%n"
                            : "%nSorry, product not found.%n%n";
                    IO.print(String.format(msg, p1));
                }
                case 3 -> {
                    var vals = inventory.calculateInventoryTotalValue();
                    IO.print("""
                            Electronics total value: $%.2f
                            Cleaning total value: $%.2f
                            Foods total value: $%.2f
                            Clothes total value: $%.2f
                            Inventory total value: $%.2f.%n
                            """.formatted(vals.get("electronics"), vals.get("cleaning"),
                            vals.get("foods"),
                            vals.get("clothes"),
                            vals.get("total")));
                }
                case 4 -> applyDiscounts(inventory.getProducts());
                case 5 -> {
                    IO.println("=== Availables ===");
                    inventory.listAvailables().forEach(IO::println);
                    IO.println();
                }
                case 6 -> {
                    IO.println("=== Low Stock (below 5 units) ===");
                    inventory.listLowStock().forEach(IO::println);
                    IO.println();
                }
            }
        } while (option != 7);
        IO.println("Thanks for your visit!!\n");
    }
}
