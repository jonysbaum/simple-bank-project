package bank;

import bank.Exceptions.AmountException;

import java.sql.SQLException;

public class Account {
    private int id;

    private String type;

    private double balance;

    public Account(int id, String type, double balance) {
        this.id = id;
        this.type = type;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) throws AmountException, SQLException {
        if (amount < 1) {
            throw new AmountException("The minimum deposit is $1.00");
        } else  {
            double newBalance = this.balance + amount;
            setBalance(newBalance);
            DataSource.updateAccountBalance( id, newBalance);
        }
    }

    public void withdraw(double amount) throws AmountException, SQLException {
        if (amount < 0) {
            throw new AmountException("Withdraw amount must be greater than zero");
        } else if (amount > getBalance()) {
            throw new AmountException("Insufficient funds");
        } else {
            double newBalance = balance - amount;
            setBalance(newBalance);
            DataSource.updateAccountBalance(id, newBalance);
        }
    }

}
