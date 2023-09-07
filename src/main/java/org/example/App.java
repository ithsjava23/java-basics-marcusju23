package org.example;

import java.util.Scanner;

public class App {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            System.out.print("\nVälj ett alternativ ");
            String menuOption = sc.nextLine().toLowerCase();

            switch (menuOption) {
                case "1" -> inputData();
                case "2" -> minMaxMid();
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

    public static void inputData() {
        int[] electricityPrices = new int[24];

        // eletricPricesMockdata = {60 65 70 68 72 75 80 85 90 88 82 78 76 75 72 70 68 65 60 58 55 50 48 45};

        for (int hour = 0; hour < 24; hour++) {
            System.out.print("Ange elpriset för timme " + String.format("%02d", hour) + ": ");
            int price = Integer.parseInt(sc.nextLine());
            // int price = sc.nextInt();
        }
    }

    public static void printMinMaxMid(){
        System.out.println("Lägsta pris: 02-03, 1 öre/kWh");
        System.out.println("Högsta pris: 00-01, 100 öre/kWh");
        System.out.println("Medelpris: 13,38 öre/kWh");
    }

    public static void printSortedData(int[] array) {

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
    }
}
