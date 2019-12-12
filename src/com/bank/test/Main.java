package com.bank.test;

import com.bank.test.model.User;
import com.bank.test.utility.BankingApplicationUtility;
import com.bank.test.utility.UserUtility;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Long askAccountNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Account Number");
        long accountNumber = scanner.nextLong();

        return accountNumber;
    }

    public static Double askAmount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter amount");
        Double amount = scanner.nextDouble();

        return amount;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        User user = null;
        UserUtility userUtility = new UserUtility();
        Random random = new Random();

        BankingApplicationUtility bankingApplicationUtility = new BankingApplicationUtility();

        System.out.println("Welcome to the Euro Banking Application");

        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Fund");
            System.out.println("3. Withdraw Fund");
            System.out.println("4. View Account Details");
            System.out.println("5. Delete Account Details");

            System.out.println("Enter your choice");
            int userChoice = scanner.nextInt();

            switch (userChoice) {

                case 1:

                    user = new User();

                    System.out.println("Enter your name: ");
                    String name = scanner.next();

                    System.out.println("Enter your address: ");
                    String address = scanner.next();

                    user.setAccountNumber(random.nextLong());
                    user.setName(name);
                    user.setAddress(address);
                    user.setAmount(0);

                    bankingApplicationUtility.createAccount(user);
                    break;

                case 2:
//                    Long accountNumber = askAccountNumber();

//                    Double deposit = askAmount();

                    bankingApplicationUtility.depositAmount(askAccountNumber(), askAmount());

                    break;

                case 3:
//                    Long accountNumber1 = askAccountNumber();

//                    Double withdraw = askAmount();

                    bankingApplicationUtility.withdrawAmount(askAccountNumber(), askAmount());

                    break;

                case 4:
//                    System.out.println("Enter your Account Number to see the account details.");
//                    Long accountNo = scanner.nextLong();
                    bankingApplicationUtility.getAccountDetails(askAccountNumber());
                    break;

                case 5:
//                    System.out.println("Enter your Account Number that you want to delete.");
//                    Long accNo = scanner.nextLong();
                    bankingApplicationUtility.closeAccount(askAccountNumber());
                    break;

                default:
                    System.out.println("Invalid Choice!!");
                    System.exit(0);
                    break;
            }
        }
    }
}
