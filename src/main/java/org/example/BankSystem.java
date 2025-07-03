package org.example;
import java.util.*;

public class BankSystem {
    private ArrayList<BankAccount> accounts;
    private Scanner scanner;

    public BankSystem() {
        accounts = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        BankSystem bank = new BankSystem();
        bank.run();
    }

    public void run() {
        int choice;
        boolean returnToMenu = true;

        do {
            if (returnToMenu) {
                displayMenu();
                choice = getUserChoice();
            } else {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    viewAllAccounts();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    deposit();
                    break;
                case 5:
                    withdraw();
                    break;
                case 6:
                    System.out.println("\nThank you for banking with us! Goodbye!");
                    returnToMenu = false;
                    break;
                default:
                    if (returnToMenu) {
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    }
                    break;
            }

            if (choice >= 1 && choice <= 5) {
                returnToMenu = askToReturnToMenu();
            }

            System.out.println();
        } while (returnToMenu);

        scanner.close();
    }

    private void displayMenu() {
        System.out.println("=== Bank Menu ===");
        System.out.println("1. Create Account");
        System.out.println("2. View All Accounts");
        System.out.println("3. Check Balance");
        System.out.println("4. Deposit");
        System.out.println("5. Withdraw");
        System.out.println("6. Exit");
        System.out.print("Enter choice (1-6): ");
    }

    private int getUserChoice() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return -1;
        } finally {
            scanner.nextLine();
        }
    }

    private void createAccount() {
        System.out.println("\n--- Create New Account ---");
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();

        if (findAccount(accNum) != null) {
            System.out.println("Account with this number already exists. Please choose a different number.");
            return;
        }

        System.out.print("Enter Holder Name: ");
        String holderName = scanner.nextLine();

        double initialDeposit = 0;
        System.out.print("Initial deposit? (yes/no): ");
        String response = scanner.nextLine().toLowerCase();

        if (response.equals("yes")) {
            boolean validInput = false;
            while (!validInput) {
                try {
                    System.out.print("Enter initial deposit amount: ");
                    initialDeposit = scanner.nextDouble();
                    if (initialDeposit < 0) {
                        System.out.println("Initial deposit cannot be negative. Please enter a non-negative amount.");
                    } else {
                        validInput = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number for the amount.");
                    scanner.next();
                } finally {
                    scanner.nextLine();
                }
            }
        }

        BankAccount newAccount = new BankAccount(accNum, holderName, initialDeposit);
        accounts.add(newAccount);
        System.out.println("Account created successfully!");
    }

    private void viewAllAccounts() {
        System.out.println("\n--- All Accounts ---");
        if (accounts.isEmpty()) {
            System.out.println("No accounts to display yet.");
            return;
        }
        for (BankAccount account : accounts) {
            account.displayInformation();
        }
    }

    private void checkBalance() {
        System.out.println("\n--- Check Account Balance ---");
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();

        BankAccount account = findAccount(accNum);
        if (account == null) {
            System.out.println("Account not found.");
        } else {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Holder: " + account.getAccountHolderName());
            System.out.println("Current Balance: $" + String.format("%.2f", account.getAvailableBalance()));
        }
    }

    private void deposit() {
        System.out.println("\n--- Deposit Funds ---");
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();

        BankAccount account = findAccount(accNum);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        double amount = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter amount to deposit: ");
                amount = scanner.nextDouble();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for the amount.");
                scanner.next();
            } finally {
                scanner.nextLine();
            }
        }
        account.deposit(amount);
    }

    private void withdraw() {
        System.out.println("\n--- Withdraw Funds ---");
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();

        BankAccount account = findAccount(accNum);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        double amount = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter amount to withdraw: ");
                amount = scanner.nextDouble();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for the amount.");
                scanner.next();
            } finally {
                scanner.nextLine();
            }
        }
        account.withdraw(amount);
    }

    private BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    private boolean askToReturnToMenu() {
        System.out.print("Would you like to return to the menu? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }
}