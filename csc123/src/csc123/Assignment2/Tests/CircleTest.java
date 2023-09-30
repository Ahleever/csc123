package csc123.Assignment2.Tests;

public class CircleTest {
    final static double pi = 3.14;
    private static double radius;
    private static String name;
    public static void main(String[] args) {
        CircleTest Circle = new CircleTest("Circle", 10);
        System.out.println(Circle);
    }
    
    public CircleTest(String string, int i){
        name = string;
        radius = i;
    }

    @Override
    public String toString() {
        double area = pi * radius * radius;
        double circumference = 2 * pi * radius;

        String s = name + 
        "\nRadius:\t\t" + radius +
        "\nArea:\t\t" + String.format("%.2f", area) + 
        "\nCircumference:\t" + String.format("%.2f", circumference);
        return s;
    }

}


