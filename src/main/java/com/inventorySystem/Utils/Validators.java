package com.inventorySystem.Utils;

public class Validators {

    private Validators() {
    }

    // Método para validar entrada de usuario, usado en los menús y submenús, para
    // permitir la selección de un rango de opciones.
    public static int validateOption(String msg, int first, int last) {
        while (true) {
            String input = IO.readln(msg).trim();
            try {
                int option = Integer.parseInt(input);
                if (option >= first && option <= last) {
                    IO.println();
                    return option;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException _) {
                System.err.print(
                        "\"%s\" is invalid; input an option between %d and %d please: ".formatted(input, first,
                                last));
            } catch (Exception e) {
                System.err.printf(java.util.Locale.US, "Error: Exception %s", e.getMessage());
            }
        }
    }
}
