import java.util.Scanner;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.InputMismatchException;
//import java.util.Map;

class Account {
    private static int nextAccountNumber = 1000;
    protected int accountNumber;
    protected String firstName;
    protected String lastName;
    protected String ssn;
    protected int age;
    protected double balance;
    protected boolean isOpen;

    protected ArrayList<Transaction> transactions;

    public Account(String firstName, String lastName, String ssn, int age) {
        if (isValidAgeForAccountType(age)) {
            this.accountNumber = getNextAccountNumber();
            this.firstName = firstName;
            this.lastName = lastName;
            this.ssn = ssn;
            this.age = age;
            this.balance = 0.0;
            this.isOpen = true;
            this.transactions = new ArrayList<>();
        } else {
            System.out.println("Error: Account could not be opened. Account holder is too young.");
            System.out.println("Account will not be created.");
            this.isOpen = false;
        }
    }

    public void deposit(double amount) {
        if (isOpen) {
            balance += amount;
            transactions.add(new Transaction("Credit", amount));
            System.out.println("Deposit successful, the new balance is: " + String.format("$%,.2f", balance));
        } else {
            System.out.println("Deposit failed, the account is closed.");
            if (balance > 0) {
                System.out.println("Deposits are not allowed for closed accounts with a positive balance.");
            }
        }
    }

    public void withdraw(double amount) {
        if (isOpen) {
            if (balance - amount >= 0) {
                balance -= amount;
                transactions.add(new Transaction("Debit", amount));
                System.out.println("Withdrawal successful, the new balance is: " + String.format("$%,.2f", balance));
            } else {
                System.out.println("Withdrawal failed, insufficient funds. The balance is: " + String.format("$%,.2f", balance ));
            }
        } else {
            System.out.println("Withdrawal failed, the account is closed.");
            if (balance < 0) {
                System.out.println("Withdrawals are not allowed for closed accounts with a negative balance.");
            }
        }
    }

    public void closeAccount(Scanner kb) {
        if (isOpen) {
            System.out.println("You are trying to close the account with a balance of "
                    + String.format("$%,.2f", balance));
            if (balance > 0) {
                System.out.println("Do you want to withdraw all money?");
                System.out.println("1 - Yes");
                System.out.println("2 - Cancel and keep the account open");
                System.out.print("Enter your choice:  ");
                int choice = kb.nextInt();

                switch (choice) {
                    case 1:
                        handleWithdrawal(kb);
                        break;
                    case 2:
                        System.out.println("Withdrawal canceled. The account remains open with a balance of "
                                + String.format("$%,.2f", balance));
                        return;
                    default:
                        System.out.println(
                                "Invalid choice. Withdrawal canceled. The account remains open with a balance of "
                                        + String.format("$%,.2f", balance));
                }
            } else if (balance < 0) {
                while (balance < 0) {
                    System.out.println("Do you want to make a deposit to bring the balance to 0?");
                    System.out.println("1 - Yes");
                    System.out.println("2 - Cancel and keep the account open");
                    System.out.print("Enter your choice:  ");
                    int choice = kb.nextInt();

                    switch (choice) {
                        case 1:
                            handleDeposit(kb);
                            break;
                        case 2:
                            System.out.println("Deposit canceled. The account remains open with a balance of "
                                    + String.format("$%,.2f", balance));
                            return;
                        default:
                            System.out.println(
                                    "Invalid choice. Deposit canceled. The account remains open with a balance of "
                                            + String.format("$%,.2f", balance));
                            return;
                    }
                }
            } else {
                if (balance == 0.0) {
                    System.out.println("Account balance is exactly 0. The account will be closed.");
                    handleClosure();
                    balance = 0.0;
                    isOpen = false;
                } else {
                    System.out.println("Account balance is not 0. Cannot close the account unless the balance is 0.");
                }
            }
        } else {
            System.out.println("Account is already closed.");
        }
    }

    private void handleWithdrawal(Scanner kb) {
        System.out.println("Account balance is positive: " + String.format("$%,.2f", balance));

        do {
            System.out.print("Enter the withdrawal amount: ");

            if (kb.hasNextDouble()) {
                double withdrawalAmount = kb.nextDouble();

                if (withdrawalAmount >= 0 && withdrawalAmount <= balance) {
                    withdraw(withdrawalAmount);

                    System.out.println("Remaining balance: " + String.format("$%,.2f", balance));

                    if (balance == 0) {
                        System.out.println("The account is now closed, and the closing balance is "
                                + String.format("$%,.2f", balance));
                        isOpen = false;
                        return;
                    }
                } else {
                    System.out.println("Invalid withdrawal amount. Please enter a valid amount.");
                    kb.nextLine();
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                kb.nextLine();
            }
        } while (balance > 0);
    }

    private void handleDeposit(Scanner kb) {
        do {
            System.out.println("Account balance is negative: -" + String.format("$%,.2f", balance));
            System.out.print("Enter the deposit amount: ");

            if (kb.hasNextDouble()) {
                double depositAmount = kb.nextDouble();

                if (depositAmount >= Math.abs(balance)) {
                    double refundAmount = depositAmount - Math.abs(balance);
                    deposit(Math.abs(balance));

                    if (refundAmount > 0) {
                        System.out.println(
                                "Deposit successful. The account is now closed, and the closing balance is "
                                        + String.format("$%,.2f", balance));
                        System.out.println("Refund of " + String.format("$%,.2f", refundAmount) + " has been issued.");
                        isOpen = true;
                    } else {
                        System.out.println(
                                "Deposit successful. The account is now closed, and the closing balance is "
                                        + String.format("$%,.2f", balance));
                        isOpen = false;
                    }
                    break;
                } else {
                    deposit(depositAmount);
                    System.out.println(
                            "Deposit successful. Account balance is still negative: -"
                                    + String.format("$%,.2f", balance));
                    isOpen = true;

                }

                System.out.println("Do you want to deposit more?");
                System.out.println("1 - Yes");
                System.out.println("2 - Cancel and keep the account open");
                System.out.print("Enter your choice:  ");
                kb.nextLine();
                int choice;

                if (kb.hasNextInt()) {
                    choice = kb.nextInt();
                } else {
                    System.out.println("Invalid choice. Deposit canceled. The account remains open with a balance of "
                            + String.format("$%,.2f", balance));
                    return;
                }

                switch (choice) {
                    case 1:
                        break;
                    case 2:
                        System.out.println("Deposit canceled. The account remains open with a balance of "
                                + String.format("$%,.2f", balance));
                        return;
                    default:
                        System.out
                                .println(
                                        "Invalid choice. Deposit canceled. The account remains open with a balance of "
                                                + String.format("$%,.2f", balance));
                        return;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                kb.nextLine();
            }
        } while (balance < 0);
    }

    private void handleClosure() {
        Scanner kb = new Scanner(System.in);

        int choice = -1;

        while (choice < 1 || choice > 2) {
            System.out.println("1 - Yes");
            System.out.println("2 - Cancel and keep the account open");
            System.out.print("Enter your choice:  ");

            try {
                choice = kb.nextInt();

                switch (choice) {
                    case 1:
                        System.out
                                .println("Account closed, the closing balance is " + String.format("$%,.2f", balance));
                        balance = 0.0;
                        isOpen = false;
                        break;
                    case 2:
                        System.out.println("Account closure canceled. The account remains open.");
                        isOpen = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Account closure canceled.");
                        choice = -1;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                kb.nextLine();
                choice = -1;
            }
        }
    }

    public void displayAccountInfo() {
        System.out.println(accountNumber + " : " + getAccountType() + " : " + firstName + " " + lastName +
                " : " + ssn + " : " + String.format("$%,.2f", balance) + " : "
                + (isOpen ? "Account Open" : "Account Closed"));
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    protected String getAccountType() {
        return "Generic";
    }

    private static int getNextAccountNumber() {
        return nextAccountNumber++;
    }

    protected boolean isValidAgeForAccountType(int age) {
        return true;
    }

}

class CheckingAccount extends Account {
    public static final int MIN_AGE = 16;
    private double overdraftLimit;

    public CheckingAccount(String firstName, String lastName, String ssn, double overdraftLimit, int age) {
        super(firstName, lastName, ssn, age);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    protected String getAccountType() {
        return "Checking";
    }

    @Override
    public void withdraw(double amount) {
        if (isOpen && (balance - amount >= -overdraftLimit)) {
            balance -= amount;
            transactions.add(new Transaction("Debit", amount));
            System.out.println("Withdrawal successful, the new balance is: " + String.format("$%,.2f", balance));
        } else {
            System.out.println("Withdrawal failed, insufficient funds or the account is closed.");
        }
    }

    protected boolean isValidAgeForAccountType(int age) {
        return age >= MIN_AGE;
    }
}

class SavingAccount extends Account {
    public static final int MIN_AGE = 5;

    public SavingAccount(String firstName, String lastName, String ssn, int age) {
        super(firstName, lastName, ssn, age);
    }

    @Override
    protected String getAccountType() {
        return "Saving";
    }

    @Override
    public void deposit(double amount) {
        if (isOpen) {
            super.deposit(amount);
        } else {
            System.out.println("Deposit failed, account is closed");
        }
    }

    protected boolean isValidAgeForAccountType(int age) {
        return age >= MIN_AGE;
    }

}

class Transaction {
    private static int nextTransactionId = 1;
    private int transactionId;
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.transactionId = getNextTransactionId();
        this.type = type;
        this.amount = amount;
    }

    public String getTransactionDetails() {
        return transactionId + " : " + type + " : " + amount;
    }

    private static int getNextTransactionId() {
        return nextTransactionId++;
    }
}

class Bank {
    private static ArrayList<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public void openCheckingAccount(String firstName, String lastName, String ssn, double overdraftLimit, int age) {
        CheckingAccount checkingAccount = new CheckingAccount(firstName, lastName, ssn, overdraftLimit, age);
        if (checkingAccount.getAccountNumber() > 999) {
            accounts.add(checkingAccount);
            System.out.println("Thank you, the account number is " + checkingAccount.getAccountNumber());
        }
    }

    public void openSavingAccount(String firstName, String lastName, String ssn, int age) {
        SavingAccount savingAccount = new SavingAccount(firstName, lastName, ssn, age);
        if (savingAccount.getAccountNumber() > 999) {
            accounts.add(savingAccount);
            System.out.println("Thank you, the account number is " + savingAccount.getAccountNumber());
        }
    }

    public void listAccounts() {
        for (Account account : accounts) {
            account.displayAccountInfo();
        }
    }

    public void accountStatement(int accountNumber) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Account Statement for Account Number " + accountNumber + ":");
            for (Transaction transaction : account.transactions) {
                System.out.println(transaction.getTransactionDetails());
            }
            System.out.println("Balance: " + account.balance);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void depositFunds(int accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdrawFunds(int accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount);
            if (!account.isOpen) {
                accounts.remove(account);
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void closeAccount(int accountNumber, Scanner kb) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.closeAccount(kb);
            if (account.balance == 0.0) {
                accounts.remove(account);
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}

public class BankingApplication {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Bank bank = new Bank();

        int choice;
        do {
            System.out.println("1 - Open Checking account");
            System.out.println("2 - Open Saving Account");
            System.out.println("3 - List Accounts");
            System.out.println("4 - Account Statement");
            System.out.println("5 - Deposit funds");
            System.out.println("6 - Withdraw funds");
            System.out.println("7 - Close an account");
            System.out.println("8 - Exit");
            System.out.print("Please enter your choice: ");

            try {

                if (kb.hasNextInt()) { 
                    choice = kb.nextInt();
                    kb.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter first name: ");
                            String firstName = kb.next();
                            System.out.print("Enter last name: ");
                            String lastName = kb.next();
                            System.out.print("Enter social security number: ");
                            String ssn = kb.next();
                            System.out.print("Enter your age: ");
                            int age = kb.nextInt();
                            System.out.print("Enter overdraft limit: ");
                            double overdraftLimit = kb.nextDouble();
                            bank.openCheckingAccount(firstName, lastName, ssn, overdraftLimit, age);
                            break;
                        case 2:
                            System.out.print("Enter first name: ");
                            firstName = kb.next();
                            System.out.print("Enter last name: ");
                            lastName = kb.next();
                            System.out.print("Enter social security number: ");
                            ssn = kb.next();
                            System.out.print("Enter your age: ");
                            age = kb.nextInt();
                            bank.openSavingAccount(firstName, lastName, ssn, age);
                            break;
                        case 3:
                            bank.listAccounts();
                            break;
                        case 4:
                            System.out.print("Enter account number: ");
                            int accountNumberForStatement = kb.nextInt();
                            bank.accountStatement(accountNumberForStatement);
                            break;
                        case 5:
                            System.out.print("Enter account number: ");
                            int accountNumberForDeposit = kb.nextInt();
                            System.out.print("Enter the amount to deposit: ");
                            double depositAmount = kb.nextDouble();
                            bank.depositFunds(accountNumberForDeposit, depositAmount);
                            break;
                        case 6:
                            System.out.print("Enter account number: ");
                            int accountNumberForWithdrawal = kb.nextInt();
                            System.out.print("Enter the withdrawal amount: ");
                            double withdrawalAmount = kb.nextDouble();
                            bank.withdrawFunds(accountNumberForWithdrawal, withdrawalAmount);
                            break;
                        case 7:
                            System.out.print("Enter account number to close: ");
                            int accountNumberToClose = kb.nextInt();
                            bank.closeAccount(accountNumberToClose, kb);
                            break;
                        case 8:
                            System.out.println("Thank you for using our banking manager program.");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    kb.nextLine();
                    choice = -1;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                kb.nextLine();
                choice = -1;
            }

        } while (choice != 8);

        kb.close();
    }
}
