package bank;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public static void main(String[] args) {
        System.out.println("Welcome to the Bank. Please login.");
        Menu menu = new Menu();
        menu.scanner = new Scanner(System.in);

        menu.scanner.close();
    }
    private Customer authenticateUser(Customer customer) {
        System.out.println("Please enter your username: ");
        String username = scanner.next();
        System.out.println("Please enter your password: ");
        String password = scanner.next();
        Customer customer = null;
    }
}
