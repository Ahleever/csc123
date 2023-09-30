//Oliver Montero (omontero1@toromail.csudh.edu)

import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
    static Scanner kb = new Scanner(System.in);
    private static ArrayList<String> userList = new ArrayList<>();
    static boolean choose = true;
    public static void main(String[] args) {
        byte choice;
        while (choose){
            System.out.print("1 - Register User\n2 - List Users\n3 - Exit\nPlease enter your choice: ");
            if (kb.hasNextByte()){
                choice = kb.nextByte();
                kb.nextLine();

                while (choice <= 1 && choice >= 3){
                        System.out.print("1 - Register User\n2 - List Users\n3 - Exit\nPlease enter your choice: ");
                        choice = kb.nextByte();
                        kb.nextLine();
                    }
                if (choice == 1){
                        RegisterUser();
                    }
                else if (choice == 2){
                        ListUsers();
                    }
                else if (choice == 3){
                        Exit();
                    }
            }
            else {
                kb.nextLine();
            }
    }
}

    public static void RegisterUser(){
        System.out.print("Enter the first name: ");
        String first = kb.nextLine();
        System.out.print("Enter the last name: ");
        String last = kb.nextLine();
        System.out.print("Enter the email address: ");
        String email = kb.nextLine();

        String userInfo = last + ", " + first + " (" + email +") ";

        userList.add(userInfo);

        System.out.println("\nThank you, user " + first + " " +
        last +" (" + email +") has been registered.\n\n");
        choose = true; 
    }

    private static void ListUsers() {
        int count = 1;
        for (String userInfo : userList){
            System.out.println("\n" + count + " - " + userInfo);
            System.out.println("--------------------------------------------");
            count++;
        }
        System.out.println("Total users: " + (count-1) + "\n");
        choose = true;
    }

    public static void Exit(){
        choose = false;
        System.out.println("Thank you for using User Manager, Goodbye.");
    }
}