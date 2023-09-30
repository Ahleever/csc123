//Oliver Montero (omontero1@toromail.csudh.edu)
import java.util.Random;

public class Dice {
    private static int value;
    public static void main(String[] args) {
        Dice d = new Dice();
        d.cast();
        System.out.println(d);
    }

    public Dice(){

    }

    public void cast(){
        Random rand = new Random();
        value = rand.nextInt(6) +1;
    }


    @Override
    public String toString() {
        return "The value is: " + value;
    }


}
