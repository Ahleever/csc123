// Oliver Montero (omontero1@toromail.csudh.edu)
package csc123.Assignment3;

import java.util.ArrayList;

public class shoppingCart {

    public String name;
    public String vendor;
    public double price;
    public double cost;
    public double weight;
    private double taxRate = 9.5;
}

class Item extends shoppingCart {

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
