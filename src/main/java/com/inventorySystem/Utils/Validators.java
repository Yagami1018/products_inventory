package com.inventorySystem.Utils;

import java.util.Scanner;

public class Validators {

    private Validators() {}

    //Método para validar entrada de usuario, usado en los menús y submenús, para permitir la selección de un rango de opciones.
    public static int validateOption(Scanner sc, String msg, int first, int last) {
        System.out.print(msg);
        while (true) {
            String input = sc.nextLine().trim();
            try {
                int option = Integer.parseInt(input);
                if (option >= first && option <= last) {
                    System.out.println();
                    return option;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException _) {
                System.err.print(
                        "\"%s\" is invalid; input an option between %d and %d please: ".formatted(input, first,
                                last));
            }
        }
    }
}
