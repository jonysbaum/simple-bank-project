package bank;

import bank.Exceptions.AmountException;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public static void main(String[] args) throws SQLException, AmountException {
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
    private Customer authenticateUser() {
        System.out.println("Please enter your username: ");
        String username = scanner.next();
        System.out.println("Please enter your password: ");
        String password = scanner.next();
        Customer customer = null;
        try {
            customer = Authenticator.login(username, password);
        } catch (LoginException e) {
            System.out.println("There was an error" + e.getMessage());
        }
        return customer;
    }

    private void showMenu(Customer customer, Account account) throws AmountException {
        int selectedOption = 0;
        while (selectedOption != 4 && customer.isAuthenticated()) {
            System.out.println("===============================");
            System.out.println("Please select an option: ");
            System.out.println("1: Deposit");
            System.out.println("2: Withdraw");
            System.out.println("3: View Balance");
            System.out.println("4: Logout");
            System.out.println("===============================");

            selectedOption = scanner.nextInt();
            double amount;
            switch (selectedOption) {
                case 1: System.out.println("Enter amount to deposit:");
                        amount = scanner.nextDouble();
                        account.deposit(amount);
                        break;
                case 2: System.out.println("Enter amount to withdraw:");
                        amount = scanner.nextDouble();
                        account.withdraw(amount);
                        break;
                case 3: System.out.println("Current balance: $" + account.getBalance());
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
