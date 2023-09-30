//Oliver Montero (omontero1@toromail.csudh.edu)

import java.util.ArrayList;

public class InvString {
    ArrayList<Character> invert = new ArrayList<>();
    public static void main(String[] args) {
        InvString s = new InvString("This is a test");
        System.out.println(s);
    }

    public InvString(String var){

        for(int x = var.length()-1; x >= 0; x-- ) {
           invert.add(var.charAt(x));
        }
    }

    @Override
    public String toString(){
        String s = "";
        for (Character val : invert) {
            s += val;
        }
        return s;
    }

}   