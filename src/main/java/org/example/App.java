package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriceList[] priceLists = new PriceList[24];

        Locale swedishLocale = new Locale("sv", "SE");
        Locale.setDefault(swedishLocale);

        while (true) {
            printMenu();
            System.out.print("\nVälj ett alternativ ");
            String menuOption = sc.nextLine().toLowerCase();
            System.out.print("\n");

            switch (menuOption) {
                case "1" -> inputPrice(priceLists, sc);
                case "2" -> printMinMaxAverage(priceLists);
                case "3" -> sortPrices(priceLists);
                case "4" -> best4Hours(priceLists);
                case "e" -> {
                    System.out.print("Avslutar...\n");
                    System.exit(0);
                }
                default -> System.out.print(menuOption + " är inget alternativ.\n");
            }
        }
    }

    public static void printMenu() {
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

    public static boolean isInputPriceFilled(PriceList[] priceLists) {
        if (priceLists[0] == null) {
            System.out.print("Inga priser har angetts ännu\n");
            return false;
        }
        // If the inputPrice method has been filled with prices
        return true;
    }

    public static void inputPrice(PriceList[] priceLists, Scanner sc) {
        // priceMockData = 60 65 70 68 72 75 80 85 90 88 82 78 76 75 72 70 68 65 60 58 55 50 48 45
        for (int i = 0; i < 24; i++) {
            System.out.printf("Ange pris för timme %02d-%02d: ", i, i + 1);
            int price = sc.nextInt();
            priceLists[i] = new PriceList(String.format("%02d-%02d", i, i + 1), price);
        }
        System.out.print("\n");
        sc.nextLine();
    }

    public static void printMinMaxAverage(PriceList[] priceLists) {
        if (!isInputPriceFilled(priceLists)) {
            return;
        }

        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        int totalValue = 0;

        String minHour = "";
        String maxHour = "";

        for (PriceList hourlyPrice : priceLists) {
            int price = hourlyPrice.getPrice();
            if (price < minValue) {
                minValue = price;
                minHour = hourlyPrice.getHour();
            }
            if (price > maxValue) {
                maxValue = price;
                maxHour = hourlyPrice.getHour();
            }
            totalValue += price;
        }

        double averageValue = (double) totalValue / priceLists.length;

        System.out.print("Lägsta pris: " + minHour + ", " + minValue + " öre/kWh\n");
        System.out.print("Högsta pris: " + maxHour + ", " + maxValue + " öre/kWh\n");
        System.out.print("Medelpris: " + String.format("%.2f", averageValue) + " öre/kWh\n");
        System.out.print("\n");
    }

    public static void sortPrices(PriceList[] priceLists) {
        if (!isInputPriceFilled(priceLists)) {
            return;
        }
        Arrays.sort(priceLists, Comparator.comparingInt(PriceList::getPrice));
        for (PriceList priceList : priceLists) {
            System.out.printf("%s %d öre\n", priceList.getHour(), priceList.getPrice());
        }
        System.out.print("\n");
    }

    public static void best4Hours(PriceList[] priceLists) {
        if (!isInputPriceFilled(priceLists)) {
            return;
        }
        int cheapestTotalValue = Integer.MAX_VALUE;
        int cheapestStartHour = -1;

        for (int startHour = 0; startHour < 21; startHour++) {
            int totalValue = 0;
            for (int i = startHour; i < startHour + 4; i++) {
                totalValue += priceLists[i].getPrice();
            }
            if (totalValue < cheapestTotalValue) {
                cheapestTotalValue = totalValue;
                cheapestStartHour = startHour;
            }
        }
        int cheapestEndHour = cheapestStartHour + 3;
        int averageValue = cheapestTotalValue / 4;

        System.out.printf("Påbörja laddning klockan %02d\n", cheapestStartHour);
        System.out.printf("Medelpris 4h: %.1f öre/kWh\n", (double) averageValue);
        System.out.print("\n");
    }
}