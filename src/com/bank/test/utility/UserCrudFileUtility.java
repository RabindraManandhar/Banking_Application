package com.bank.test.utility;

import com.bank.test.model.ApplicationMessage;
import com.bank.test.model.User;

import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

/*
    This Class is used for saving User object to File.
    Deals with File I/O operation.
 */
public class UserCrudFileUtility implements UserCrudUtility {
    private String dbPath;

    // default Constructor
    public UserCrudFileUtility() {
        dbPath = "C:\\qitmaterials\\java\\BankingApplication\\src\\BankDb\\";
    }

    @Override
    public ApplicationMessage saveUserDetails(User user) {

        ApplicationMessage applicationMessage = new ApplicationMessage();

        try {
            FileWriter fileWriter = new FileWriter(dbPath + user.getAccountNumber());

            fileWriter.write("Account:" + user.getAccountNumber() + System.lineSeparator());
            fileWriter.write("Name:" + user.getName() + System.lineSeparator());
            fileWriter.write("Address:" + user.getAddress() + System.lineSeparator());
            fileWriter.write("Balance:" + user.getAmount());

            fileWriter.close();

            //success
            applicationMessage.setSuccess(true);
            applicationMessage.setErrorMessage(null);
            applicationMessage.setData(user);

        } catch (IOException e) {
            applicationMessage.setSuccess(false);
            applicationMessage.setErrorMessage(e.getMessage());
            applicationMessage.setData(null);
        }

        return applicationMessage;
    }

    @Override
    public User getUserDetails(Long accountNumber) {
        // 1. Open file related to this account number
        // 2. Read file
        // 3. Parse the file data line by line
        // 4. Set the value to the User Object
        // 5. Return User Object

        User user = null;

        try {
            FileReader fileReader = new FileReader(dbPath + accountNumber);

            BufferedReader bf = new BufferedReader(fileReader);

            String line = null;
            user = new User();

            while ((line = bf.readLine()) != null) {
                String lines[] = line.split(":");
                System.out.println(lines[0]);
                System.out.println(lines[1]);

                if (lines[0].equals("Account")) { // .equals and .equalsIgnoreCase make comparison between strings with different lower or upper case
                    user.setAccountNumber(Long.parseLong(lines[1])); // parseLong is used to convert data type i.e. string to long
                } else if (lines[0].equals("Name")) {
                    user.setName(lines[1]);
                } else if (lines[0].equals("Address")) {
                    user.setAddress(lines[1]);
                } else if (lines[0].equals("Balance")) {
                    user.setAmount(Double.parseDouble(lines[1])); // parseDouble is used to convert data type i.e. string to Double
                } else {
                    // do nothing
                }
            }

            System.out.println(line);
            fileReader.close();
            bf.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void deleteUser(User user) {
        // 1. Delete File related to this account number.
        try {
            Files.deleteIfExists(Paths.get(dbPath + user.getAccountNumber()));

        } catch (NoSuchFileException e) {
            System.out.println("No such file/directory exists.");
        } catch (DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty");
        } catch (IOException e) {
            System.out.println("Invalid Permission");
        }
        System.out.println("Deletion successful");
    }
}
