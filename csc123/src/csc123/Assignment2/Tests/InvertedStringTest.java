package csc123.Assignment2.Tests;

import java.util.ArrayList;
public class InvertedStringTest {
    ArrayList<Character> invert = new ArrayList<>();

    public static void main(String[] args) {
        InvertedStringTest s = new InvertedStringTest("This is a test");
        System.out.println(s);
    }

    public InvertedStringTest(String string) {
        for (int x = string.length()-1; x >= 0; x--){
            invert.add(string.charAt(x));
        }
    }

    @Override
    public String toString() {
    String s ="";
    for (Character val: invert){
        s += val;
    }
    return s;
    }

}
