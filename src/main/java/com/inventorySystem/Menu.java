package com.inventorySystem;

import static com.inventorySystem.Utils.Helpers.applyDiscounts;
import static com.inventorySystem.Utils.Validators.validateOption;
import java.util.Scanner;
import com.inventorySystem.Models.Inventory;
import com.inventorySystem.Models.Product;

public class Menu {
    private Scanner sc;
    private Inventory<Product> inventory;

    public Menu(Scanner sc, Inventory<Product> inventory) {
        this.sc = sc;
        this.inventory = inventory;
    }

    public void showMenu() {
        int option = 6;
        do {
            System.out.println("""
                    === Menu ===
                    1) Show products
                    2) Find by code
                    3) Show inventory total value
                    4) Apply discounts
                    5) Show available only
                    6) Exit
                    """);

            option = validateOption(sc, "Choose an option: ", 1, 6);

            switch (option) {
                case 1:
                    inventory.showProducts();
                    break;
                case 2:
                    System.out.print("Find by code: ");
                    String code = sc.nextLine();
                    Product p1 = inventory.findByCode(code);
                    String msg = p1 != null ? "%nProduct found %n%s %n%n"
                            : "%nSorry, product not found.%n%n";
                    System.out.printf(msg, p1);
                    break;
                case 3:
                    var vals = inventory.calculateInventoryTotalValue();
                    System.out.printf("""
                            Electronics total value: $%.2f
                            Cleaning total value: $%.2f
                            Foods total value: $%.2f
                            Inventory total value: $%.2f.%n
                            """, vals.get("electronics"), vals.get("cleaning"), vals.get("foods"),
                            vals.get("total"));
                    break;
                case 4:
                    // Uso el metodo static applyDiscounts de la clase Helpers para abrir un submenu
                    // y aplicar descuentos a los productos que se desea.
                    applyDiscounts(sc, inventory.getProducts());
                    break;
                case 5:
                    System.out.println("=== Availables ===");
                    for (Product p : inventory.listAvailables()) {
                        System.out.println(p);
                    }
                    System.out.println();
            }
        } while (option != 6);
        System.out.println("Thanks for your visit!!\n");
    }
}
