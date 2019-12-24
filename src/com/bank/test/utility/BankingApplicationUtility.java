package com.bank.test.utility;

import com.bank.test.model.ApplicationMessage;
import com.bank.test.model.User;

public class BankingApplicationUtility {

    private UserCrudUtility userCrudUtility;

    public BankingApplicationUtility() {
//        userCrudUtility = new UserCrudFileUtility();
        userCrudUtility = new UserCrudDatabaseUtility();
    }

    public ApplicationMessage createAccount(User user) {
        return userCrudUtility.saveUserDetails(user);
    }

    public void depositAmount(Long accountNumber, Double deposit) {
        User fetchedUser = userCrudUtility.getUserDetails(accountNumber);

        if (fetchedUser == null) {

            System.out.println("Sorry! This account number doesn't exist in our system.");

        } else {
            fetchedUser.setAmount(deposit + fetchedUser.getAmount());

            ApplicationMessage depositMessage = userCrudUtility.saveUserDetails(fetchedUser);

            if (depositMessage.isSuccess()) {
                System.out.println("You deposited an amount of " + deposit + " successfully.");
            } else {
                System.out.println(depositMessage.getErrorMessage());
                System.out.println("Error, Your deposit was not successful. Please try again.");
            }

            System.out.println("Balance: " + fetchedUser.getAmount());
        }
    }

    public void withdrawAmount(Long accountNumber, Double withdrawal) {
        User existingUser = userCrudUtility.getUserDetails(accountNumber);

        if (existingUser == null) {

            System.out.println("Sorry! This account number doesn't exist in our system.");

        } else {

            if (existingUser.getAmount() < withdrawal) {
                System.out.println("You don't have enough balance in your account.");
            } else {
                existingUser.setAmount(existingUser.getAmount() - withdrawal);
                ApplicationMessage withdrawalMessage = userCrudUtility.saveUserDetails(existingUser);
                System.out.println("Balance: " + existingUser.getAmount());
            }
        }
    }

    public User getAccountDetails(Long accountNumber) {
        return userCrudUtility.getUserDetails(accountNumber);
    }

    public void closeAccount(Long accountNumber) {
        User user = getAccountDetails(accountNumber);

        if (user == null) {
            System.out.println("Sorry! This account doesn't exist in our system.");
        } else {
            userCrudUtility.deleteUser(user);
        }
    }
}
