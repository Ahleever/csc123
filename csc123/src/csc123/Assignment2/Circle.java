// Oliver Montero (omontero1@toromail.csudh.edu)
public class Circle {
    private final double pi = 3.14159;
    private int radius;
    private String name;

    public Circle(String var, int rad){
        name = var;
        radius = rad;
 
    }


    @Override
    public String toString() {
        double area = pi * radius * radius;
        double circumference = 2 * pi * radius;
    
        String result = name + "\n" +
                        "Radius\t\t: " + radius + "\n" +
                        "Area\t\t: " + String.format("%.2f", area) + "\n" +
                        "Circumference\t: " + String.format("%.2f", circumference) + "\n";
    
        return result;
    }
}
