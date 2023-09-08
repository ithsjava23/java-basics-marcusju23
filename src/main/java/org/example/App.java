package org.example;

import java.util.Locale;
import java.util.Scanner;

public class App {
    public Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Locale swedishLocale = new Locale("sv", "SE");
        Locale.setDefault(swedishLocale);
        Scanner sc = new Scanner(System.in);

        while (true) {
            showMenu();
            System.out.print("\nVälj ett alternativ ");
            String menuOption = sc.nextLine().toLowerCase();

            switch (menuOption) {
                case "1" -> inputData(sc);
                case "2" -> "".isEmpty();
                case "3" -> "".isEmpty();
                case "4" -> "".isEmpty();
                case "e" -> {
                    System.out.print("Avslutar...\n");
                    System.exit(0);
                }
                default -> System.out.print(menuOption + " är inget alternativ.\n");
            }
        }
    }

    public static void showMenu() {
        System.out.print("""
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta
                """);
    }

    public static void inputData(Scanner sc) {
        int[] hours = new int[24];
        int[] electricityPrices = new int[24];

        // priceMockData = 60 65 70 68 72 75 80 85 90 88 82 78 76 75 72 70 68 65 60 58 55 50 48 45

        for (int hour = 0; hour < 24; hour++) {
            System.out.print("Ange elpriset för timme " + String.format("%02d", hour) + ": ");
            int price = sc.nextInt();
            electricityPrices[hour] = price;
        }
        System.out.print("Elpriser: ");
        for (int hour = 0; hour < 24; hour++) {
            System.out.print(electricityPrices[hour] + " ");
        }
        sc.nextLine();
    }

    public static void printMinMaxMid(int[] eletricityPrices) {
        int minPrice = eletricityPrices[0];
        int maxPrice = eletricityPrices[0];
        double midPrice;
        int sum = 0;

        for (int price : eletricityPrices) {
            if (price < minPrice) {
                minPrice = price;
            }
            if (price > maxPrice) {
                maxPrice = price;
            }
            sum += price;
        }
        midPrice = (double) sum/eletricityPrices.length;
        System.out.println("Lägsta pris: " );
        System.out.println();
        System.out.println();
    }

    public static void printSortedData(int[] array) {

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
