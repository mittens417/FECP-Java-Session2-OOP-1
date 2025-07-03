package org.example;

import java.text.DecimalFormat;

public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double availableBalance;


    public BankAccount(String accountNumber, String accountHolderName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.availableBalance = initialDeposit;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }


    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
        this.availableBalance += amount;
        System.out.println("Deposit successful. New balance: $" + String.format("%.2f", this.availableBalance));
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (this.availableBalance < amount) {
            System.out.println("Insufficient funds. Current balance: $" + String.format("%.2f", this.availableBalance));
            return false;
        }
        this.availableBalance -= amount;
        System.out.println("Withdrawal successful. New balance: $" + String.format("%.2f", this.availableBalance));
        return true;
    }

    public void displayInformation() {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("\n--- Account Information ---");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Available Balance: $" + df.format(availableBalance));
        System.out.println("---------------------------");
    }
}