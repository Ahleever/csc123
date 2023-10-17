package csc123.Assignment3;

import java.util.ArrayList;
import java.util.Scanner;

class ShoppingCart {
    private ArrayList<Item> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public int getItemCount() {
        return items.size();
    }

    public String calculateTotalCost() {
        double totalCost = 0;
        for (Item item : items) {
            totalCost += item.calculateTotalCost();
        }
        return String.format("%.2f", totalCost);
    }

    public void printCartContents() {
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("There are " + items.size() + " items in the cart");
    }
}

class Driver {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Scanner kb = new Scanner(System.in);
        boolean isShopping = true;

        while (isShopping) {
            System.out.println("\nShopping Cart Menu:");
            System.out.println("1. Add Publication");
            System.out.println("2. Add Food");
            System.out.println("3. Add General Grocery Item");
            System.out.println("4. View Cart Contents");
            System.out.println("5. Calculate Total Cost");
            System.out.println("6. Exit");
            System.out.println("Select an option: ");
            System.out.println("--------------------------------------------");

            int choice = kb.nextInt();
            kb.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the nameof the publication: ");
                    String name = kb.nextLine();
                    System.out.print("Enter the vendor: ");
                    String vendor = kb.nextLine();
                    System.out.print("Enter the price: ");
                    double price = kb.nextDouble();
                    kb.nextLine();
                    System.out.print("Enter the cost: ");
                    double cost = kb.nextDouble();
                    kb.nextLine();
                    System.out.print("Enter the weight(lbs): ");
                    double weight = kb.nextDouble();
                    kb.nextLine();
                    System.out.print("Enter the author: ");
                    String author = kb.nextLine();
                    System.out.print("Enter the publication month: ");
                    String publicationMonth = kb.nextLine();
                    System.out.print("Enter the number of pages: ");
                    int numPages = kb.nextInt();
                    kb.nextLine();
                    Publication publication = new Publication(name, vendor, price, cost, weight, author,
                            publicationMonth, numPages);
                    cart.addItem(publication);
                    System.out.println("Publication added to the cart.");
                    System.out.println("--------------------------------------------");
                    break;
                case 2:
                    System.out.print("Enter the name: ");
                    String foodName = kb.nextLine();
                    System.out.print("Enter the vendor: ");
                    String foodVendor = kb.nextLine();
                    System.out.print("Enter the price: ");
                    double foodPrice = kb.nextDouble();
                    kb.nextLine();
                    System.out.print("Enter the cost: ");
                    double foodCost = kb.nextDouble();
                    kb.nextLine();
                    System.out.print("Enter the weight(lbs): ");
                    double foodWeight = kb.nextDouble();
                    kb.nextLine();
                    System.out.print("Enter the Sell By Date(MM/YY): ");
                    String sellByDate = kb.nextLine();
                    System.out.print("Enter the Use By Date(MM/YY): ");
                    String useByDate = kb.nextLine();
                    Food foodItem = new Food(foodName, foodVendor, foodPrice, foodCost, foodWeight, sellByDate,
                            useByDate);
                    cart.addItem(foodItem);
                    System.out.println("Food item added to the cart.");
                    System.out.println("--------------------------------------------");
                    break;
                case 3:
                    System.out.print("Enter the name: ");
                    String itemName = kb.nextLine();
                    System.out.print("Enter the vendor: ");
                    String itemVendor = kb.nextLine();
                    System.out.print("Enter the price: ");
                    double itemPrice = kb.nextDouble();
                    kb.nextLine();
                    System.out.print("Enter the cost: ");
                    double itemCost = kb.nextDouble();
                    kb.nextLine();
                    System.out.println();
                } else if (choice == 4) {
                    // Calculate total logic here
                } else if (choice == 5) {
                    // My Items logic here
                }
            } else {
                kb.nextLine();
            }
        }
    }
}

class Item {
    public String name;
    public String vendor;
    private double price;
    private double cost;
    private double weight;
    private double taxRate = 9.5;

    public Item(String name, double price) {
        this.name = name;
        this.vendor = vendor;
        this.price = price;
        this.cost = cost;
        this.weight = weight;
    }

    public double calculateTotalCost() {
        return price + (price * taxRate / 100);
    }

    @Override
    public String toString() {
        double totalCost = calculateTotalCost();
        return String.format("%s (Vendor: %s, Price: $%.2f, Tax: %.2f%%, Total Cost: $%.2f)", name, vendor, price,
                taxRate, totalCost);
    }
}

class Publication extends Item {
    private String author;
    private String publicationMonth;
    priva
    public Publication(String name, String vendor, double price, double cost, double weight,
            String author, String publicationMonth, int numPages) {
        super(name, vendor, price, cost, weight);
        this.author = author;
        this.publicationMonth = publicationMonth;
        this.numPages = numPages;
    }

    @Override

           retu

                + ", Number of Pages: " + numPages;
    }

    

class Food extends Item {
    private String sellByDate;
    private String useByDate;

    public Food(String name, String vendor, double price, double cost, double weight, String sellByDate,
            String useByDate) {
        super(name, vendor, price, cost, weight);
        this.sellByDate = sellByDate;
        this.useByDate = useByDate;
    }

    @Override
    public String toString() {
        return super.toString() + ", Sell By Date: " + sellByDate + ", Use By Date: " + useByDate;
    }

}
