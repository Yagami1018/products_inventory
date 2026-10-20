package com.inventorySystem;

import static com.inventorySystem.Utils.Helpers.loadInventory;

import com.inventorySystem.Models.Inventory;
import com.inventorySystem.Models.Product;
import com.inventorySystem.Utils.Menu;

public class Main {
    void main() {
        Inventory<Product> inventory = new Inventory<>();
        Menu menu = new Menu(inventory);

        // Cargo los primeros productos al inventario con el uso del metodo static
        // loadInventory de la clase Helpers, y muestro el menú al usuario.
        loadInventory(inventory);
        menu.showMenu();
    }
}
