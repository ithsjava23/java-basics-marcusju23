package org.example;

import java.util.Scanner;

public class App {
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String menu = """
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta
                """;
        System.out.println(menu);
        System.out.println("Välj ett alternativ");
        String menuOption = sc.nextLine();

//        test


        switch (menuOption) {
            case '1' -> {
            }
            // code block
            case '2' -> {
            }
            case '3' -> {

            }
            case '4' -> {

            }
            case 'e' -> {

            }
            // code block
            default -> {
            }
            // code block
        }
    }
}
