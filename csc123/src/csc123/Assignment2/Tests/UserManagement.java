package csc123.Assignment2.Tests;

import java.util.ArrayList;
import java.util.Scanner;

public class UserManagement {
    static Scanner kb = new Scanner(System.in);
    private static ArrayList<String> UserInformation = new ArrayList<>();
    public static boolean cont = true;
    
    public static void main(String[] args) {
        byte choice;
        while (cont){
            System.out.print("1 - Register user\n2 - List users\n3 - Exit\nEnter your choice: ");
            if(kb.hasNextByte()){
                choice = kb.nextByte();
                kb.nextLine();
                while( choice <= 1 && choice >= 3){
                    System.out.print("1 - Register User\n2 - List Users\n3 - Exit\nPlease enter your choice: ");
                    choice = kb.nextByte();
                    kb.nextLine();
                }
                if(choice ==1){
                    Register();
                }
                else if (choice ==2){
                    ListUsers();
                }
                else if (choice == 3){
                    Exit();
                }
            }
            else{
                kb.nextLine();
            }
        }
        System.out.println("Thank you for using User Manager, goodbye!");
    }

    private static void Register() {
        System.out.print("Enter the first name: ");
        String first = kb.nextLine();
        System.out.print("Enter the last name: ");
        String last = kb.nextLine();
        System.out.print("Enter the email address: ");
        String email = kb.nextLine();

        String user = last + ", " + first + " (" + email +")";
        UserInformation.add(user);

        System.out.println("\nThank you, user " + first +" " + last + "(" + email +") has been registered.\n");
        
    }
    private static void ListUsers() {
        int count = 1;
        for(String val: UserInformation){
            System.out.println(count + ". " + val);
            count++;
            System.out.println("---------------------------------------------");
        }
        System.out.println("Number of users: " + (count -1) +"\n");
    }

    private static void Exit() {
        cont = false;
    }

    
}
