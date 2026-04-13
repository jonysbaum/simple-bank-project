package bank;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public static void main(String[] args) throws LoginException, SQLException {
        System.out.println("Welcome to the Bank. Please login.");
        Menu menu = new Menu();
        menu.scanner = new Scanner(System.in);

        Customer customer = menu.authenticateUser();
        if (customer != null) {
            Account account = DataSource.getAccount(customer.getAccountId());
            menu.showMenu(customer, account);
        }

        menu.scanner.close();
    }
    private Customer authenticateUser() throws LoginException {
        System.out.println("Please enter your username: ");
        String username = scanner.next();
        System.out.println("Please enter your password: ");
        String password = scanner.next();
        Customer customer = null;
        try{
            customer = Authenticator.login(username, password);
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

    private void showMenu(Customer customer, Account account) {
        int selectedOption = 0;
        while (selectedOption != 4 && customer.isAuthenticated()) {
            System.out.println("===============================");
            System.out.println("Please select an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. View Balance");
            System.out.println("4. Logout");
            System.out.println("===============================");

            selectedOption = scanner.nextInt();
            Double amount = scanner.nextDouble();
            switch (selectedOption) {
                case 1:
                    System.out.println("Enter amount to deposit:");
                    account.deposit(amount);
                    break;
                case 2:
                    System.out.println("Enter amount to withdraw:");
                    account.withdraw(amount);
                    break;
                case 3:
                    System.out.println("View current balance:" + account.getBalance());
                    break;
                case 4: Authenticator.logout(customer);
                    System.out.println("You have successfully logged out.");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
