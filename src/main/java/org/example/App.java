package org.example;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriceList[] priceLists = new PriceList[24];

        Locale swedishLocale = new Locale("sv", "SE");
        Locale.setDefault(swedishLocale);
        boolean menuAlternative = true;

        while (menuAlternative) {
            printMenu();
            String menuOption = sc.nextLine().toLowerCase();

            switch (menuOption) {
                case "1" -> inputPrice(priceLists, sc);
                case "2" -> printMinMaxAverage(priceLists);
                case "3" -> sortPriceList(priceLists);
                case "4" -> best4Hours(priceLists);
                case "e" -> {
                    System.out.print("Avslutar...\n");
                    menuAlternative = false;
                }
                default -> System.out.print(menuOption + " är inget alternativ.\n");
            }
        }
    }

    public static void printMenu() {
        String menu = """
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta
                """;
        System.out.print(menu);
    }

    public static void inputPrice(PriceList[] priceLists, Scanner sc) {
        for (int i = 0; i < 24; i++) {
            System.out.printf("Ange pris för timme %02d-%02d: ", i, i + 1);
            int price = sc.nextInt();
            priceLists[i] = new PriceList(String.format("%02d-%02d", i, i + 1), price);
        }
        sc.nextLine();
    }

    public static void printMinMaxAverage(PriceList[] priceLists) {
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
    }

    public static void sortPriceList(PriceList[] priceLists) {
        Arrays.sort(priceLists, (a, b) -> Integer.compare(b.getPrice(), a.getPrice()));
        for (PriceList priceList : priceLists) {
            System.out.printf("%s %d öre\n", priceList.getHour(), priceList.getPrice());
        }
    }


    public static void best4Hours(PriceList[] priceLists) {
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
        double averageValue = (double) cheapestTotalValue / 4;

        System.out.printf("Påbörja laddning klockan %02d\n", cheapestStartHour);
        System.out.printf("Medelpris 4h: %.1f öre/kWh\n", averageValue);
    }
}