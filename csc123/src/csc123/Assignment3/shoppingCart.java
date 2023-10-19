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
        int choice;

        while (isShopping) {
            System.out.println("\nShopping Cart Menu:\n1. Add Publication" +
                    "\n2. Add Food\n3. Add General Grocery Item\n4. View Cart Contents" +
                    "\n5. Calculate Total Cost\n6. Exit\n");
            System.out.println("Select an option: ");
            if (kb.hasNextInt()) {
                choice = kb.nextInt();
                kb.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter the name of the publication: ");
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
                        System.out.print("Enter the weight(lbs): ");
                        double itemWeight = kb.nextDouble();
                        kb.nextLine();
                        Item groceryItem = new Item(itemName, itemVendor, itemPrice, itemCost, itemWeight);
                        cart.addItem(groceryItem);
                        System.out.println("General Grocery Item added to the cart.");
                        System.out.println("--------------------------------------------");
                        break;
                    case 4:
                        System.out.print("Items in the Shopping Cart:");
                        cart.printCartContents();
                        System.out.println("--------------------------------------------");
                        break;
                    case 5:
                        String totalCost = cart.calculateTotalCost();
                        System.out.println("--------------------------------------------");
                        System.out.println("Total Cost of Items in the Cart: $" + totalCost);
                        System.out.println("--------------------------------------------");
                        break;
                    case 6:
                        System.out.println("Exiting the program.");
                        kb.close();
                        isShopping = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a valid option.");
                        System.out.println("--------------------------------------------");
                        break;
                }
            } else {
                break;
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

    public Item(String name, String vendor, double price, double cost, double weight) {
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
    private int numPages;

    public Publication(String name, String vendor, double price, double cost, double weight,
            String author, String publicationMonth, int numPages) {
        super(name, vendor, price, cost, weight);
        this.author = author;
        this.publicationMonth = publicationMonth;
        this.numPages = numPages;
    }

    @Override
    public String toString() {
        return super.toString() + ", Author: " + author + ", Publication Month: " + publicationMonth
                + ", Number of Pages: " + numPages;
    }
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
