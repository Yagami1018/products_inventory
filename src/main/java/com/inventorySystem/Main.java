package com.inventorySystem;

import static com.inventorySystem.Utils.Helpers.loadInventory;
import java.util.Scanner;
import com.inventorySystem.Models.Inventory;
import com.inventorySystem.Models.Product;

public class Main {
    public static void main(String[] args) {
        // Instancio Scanner para entrada de usuario, inventario, luego inicializo el menu y le paso el Scanner y el inventario.
        Scanner sc = new Scanner(System.in);
        Inventory<Product> inventory = new Inventory<>();
        Menu menu = new Menu(sc, inventory);

        //Cargo los primeros productos al inventario con el uso del metodo static loadInventory de la clase Helpers, y muestro el menú al usuario.
        loadInventory(inventory);
        menu.showMenu();
        
        sc.close();
    }
}
