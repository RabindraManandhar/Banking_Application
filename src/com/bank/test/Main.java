package com.bank.test;

import com.bank.test.model.ApplicationMessage;
import com.bank.test.model.User;
import com.bank.test.utility.UserUtility;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the Euro Banking Application");

        Scanner scanner = new Scanner(System.in);

        User user = null;
        UserUtility userUtility = new UserUtility();
        Random random = new Random();

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

                    ApplicationMessage applicationMessage = userUtility.saveUserDetails(user);

                    if (applicationMessage.isSuccess()) {
                        System.out.println("Your account detail is successfully saved.");
                    } else {
                        System.out.println(applicationMessage.getErrorMessage());
                        System.out.println("Error, Please try again.");
                    }

                    System.out.println("Your newly created account's detail is:");
                    System.out.println("Account Number: " + user.getAccountNumber());
                    System.out.println("Name: " + user.getName());
                    System.out.println("Address: " + user.getAddress());
                    System.out.println("Balance: " + user.getAmount());
                    break;

                case 2:
                    System.out.println("Enter your Account Number");
                    long accountNumber = scanner.nextLong();

                    User fetchedUser = userUtility.getUserDetails(accountNumber);

                    if (fetchedUser == null) {

                        System.out.println("Sorry! This account number doesn't exist in our system.");

                    } else {
                        System.out.println("Please enter your deposit amount");
                        double deposit = scanner.nextDouble();

                        fetchedUser.setAmount(deposit + fetchedUser.getAmount());

                        ApplicationMessage depositMessage = userUtility.saveUserDetails(fetchedUser);

                        if (depositMessage.isSuccess()) {
                            System.out.println("You deposited an amount of " + deposit + " successfully.");
                        } else {
                            System.out.println(depositMessage.getErrorMessage());
                            System.out.println("Error, Your deposit was not successful. Please try again.");
                        }

                        System.out.println("Balance: " + fetchedUser.getAmount());
                    }

                    break;

                case 3:
                    System.out.println("Enter your account number");
                    long accountNumber1 = scanner.nextLong();

                    User existingUser = userUtility.getUserDetails(accountNumber1);

                    if (existingUser == null) {

                        System.out.println("Sorry! This account number doesn't exist in our system.");

                    } else {
                        System.out.println("Please enter your withdrawal amount");
                        double withdrawal = scanner.nextDouble();


                        if (existingUser.getAmount() < withdrawal) {
                            System.out.println("You don't have enough balance in your account.");
                        } else {
                            existingUser.setAmount(existingUser.getAmount() - withdrawal);
                            ApplicationMessage withdrawalMessage = userUtility.saveUserDetails(existingUser);
                            System.out.println("Balance: " + existingUser.getAmount());
                        }
                    }

                    break;

                case 4:
                    System.out.println("Enter your Account Number to see the account details.");
                    Long accountNo = scanner.nextLong();
                    userUtility.getUserDetails(accountNo);
                    break;

                case 5:
                    System.out.println("Enter your Account Number that you want to delete.");
                    Long accNo = scanner.nextLong();
                    userUtility.deleteUserDetails(accNo);
                    break;

                default:
                    System.out.println("Invalid Choice!!");
                    System.exit(0);
                    break;
            }
        }
    }
}
