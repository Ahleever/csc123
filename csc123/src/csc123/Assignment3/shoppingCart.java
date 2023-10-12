package csc123.Assignment3;

import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart {
    public static String name;
    public static String vendor;
    public static double price;
    public static double cost;
    public static double weight;
    private static double taxRate = 9.5;
    public static boolean addItem = true;

    static ArrayList<Item> cart = new ArrayList<>();
}

class Driver extends ShoppingCart {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        byte choice;

        while (addItem) {
            System.out.print("1 - Add a publication\n2 - Add a food item\n3 - Add a general item" +
                    "\n4 - Calculate total\n5 - My Items\nPlease enter your choice: ");

            if (kb.hasNextByte()) {
                choice = kb.nextByte();
                kb.nextLine();

                while (choice < 1 || choice > 5) {
                    System.out.print("1 - Add a publication\n2 - Add a food item\n3 - Add a general item" +
                            "\n4 - Calculate total\n5 - My Items\nPlease enter your choice: ");
                    choice = kb.nextByte();
                    kb.nextLine();
                }

                if (choice == 1) {
                    Publication publication = null;
                    kb.nextLine();
                    System.out.print("Enter publication name: ");
                    String name = kb.nextLine();
                    System.out.print("Enter publication price: ");
                    double price = kb.nextDouble();
                    System.out.print("Enter author: ");
                    String author = kb.next();
                    System.out.print("Enter publication month: ");
                    String publicationMonth = kb.next();
                    System.out.print("Enter number of pages: ");
                    int numPages = kb.nextInt();
                    publication = new Publication(name, price, author, publicationMonth, numPages);
                    cart.add(publication);
                } else if (choice == 2) {
                    Food foodItem = null;
                    kb.nextLine();
                    System.out.print("Enter food item name: ");
                    String name = kb.nextLine();
                    System.out.print("Enter food item price: ");
                    double price = kb.nextDouble();
                    System.out.print("Enter food item sell by date: ");
                    String sellByDate = kb.nextLine();
                    System.out.print("Enter food item use by date: ");
                    String useByDate = kb.nextLine();
                    foodItem = new Food(name, price, sellByDate, useByDate);
                    cart.add(foodItem);
                } else if (choice == 3) {
                    Item generalItem = null;
                    kb.nextLine();
                    System.out.println();
                } else if (choice == 4) {
                    double total = 0;
                    for (Item item : cart) {
                        total += item.getPrice();
                    }
                    System.out.println("Total: $" + total);
                } else if (choice == 5) {
                    for (Item item : cart) {
                        System.out.println(item.getName());
                    }
                }
            } else {
                kb.nextLine();
            }
        }
    }
}

class Item {
    public String name;
    public double price;

    public Item(String name, double price) {

        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Publication extends Item {
    private String author;
    private String publicationMonth;
    private int numPages;

    public Publication(String name, double price, String author, String publicationMonth, int numPages) {
        super(name, price);
        this.author = author;
        this.publicationMonth = publicationMonth;
        this.numPages = numPages;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublicationMonth() {
        return publicationMonth;
    }

    public int getNumPages() {
        return numPages;
    }
}

class Food extends Item {
    private String sellByDate;
    private String useByDate;

    public Food(String name, double price, String sellByDate, String useByDate) {
        super(name, price);
        this.sellByDate = sellByDate;
        this.useByDate = useByDate;
    }

    public String getSellByDate() {
        return sellByDate;
    }

    public String getUseByDate() {
        return useByDate;
    }
}
