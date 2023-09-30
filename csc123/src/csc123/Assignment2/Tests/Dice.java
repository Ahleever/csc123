package Tests;

import java.util.Random;

public class Dice {
    private static int value;

	public static void main(String[] args) {
		Dice dice = new Dice();
        dice.cast();    
        System.out.println(dice);   
        
	}

    public Dice() {
    }

    private void cast() {
        Random rand = new Random();
        value = rand.nextInt(6) +1;
    }

    @Override
    public String toString() {
        String s = "The value is: " + value;
        return s;
    }
    


}