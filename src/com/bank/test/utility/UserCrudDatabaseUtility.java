package com.bank.test.utility;

import com.bank.test.model.ApplicationMessage;
import com.bank.test.model.User;

import java.sql.*;

public class UserCrudDatabaseUtility implements UserCrudUtility {

    @Override
    public ApplicationMessage saveUserDetails(User user) {

        if (user.getId() == 0) {
            return insertUser(user);

        } else {
            return updateUser(user);
        }
    }

    public ApplicationMessage insertUser(User user) {

        ApplicationMessage appMessage = new ApplicationMessage();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_banking_app", "root", "888eight888");

            String insertQuery = "insert into tbl_user\n" +
                    "(account_number, name, address, amount)\n" +
                    "values\n" +
                    "(?,?,?,?);";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setLong(1, user.getAccountNumber());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setDouble(4, user.getAmount());

            boolean flag = preparedStatement.execute();

            appMessage.setSuccess(true);
            appMessage.setErrorMessage(null);
            appMessage.setData(user);

            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            appMessage.setSuccess(false);
            appMessage.setErrorMessage(e.getMessage());
            appMessage.setData(null);
            e.printStackTrace();
        }
        return appMessage;
    }

    public ApplicationMessage updateUser(User user) {

        ApplicationMessage appMessage = new ApplicationMessage();

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_banking_app", "root", "888eight888");

            String updateQuery =
                    "update tbl_user\n" +
                            "set\n" +
                            "amount = ?\n" +
                            "where id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);


            preparedStatement.setDouble(1, user.getAmount());
            preparedStatement.setInt(2, user.getId());

            preparedStatement.execute();

            boolean flag = preparedStatement.execute();

            appMessage.setSuccess(true);
            appMessage.setErrorMessage(null);
            appMessage.setData(user);

            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appMessage;
    }

    @Override
    public User getUserDetails(Long accountNumber) {

        // 1. Open db Connection to mySQL.
        // 2. Hold connection using Connection Object
        // 3. Prepare query using this accountNumber i.e. using Select Query
        // 4. Hold ResultSet Object
        // 5. Iterate through ResultSet using its next() function.
        // 6. Set values to User Object.
        // 7. Return User Object.

        User user = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_banking_app", "root", "888eight888");

            String selectQuery = "select * from tbl_user where account_number = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setLong(1, accountNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Checking if user exists or not

            user = new User();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                Long accNo = resultSet.getLong("account_number");
                Double amount = resultSet.getDouble("amount");

                user.setId(id);
                user.setName(name);
                user.setAddress(address);
                user.setAccountNumber(accNo);
                user.setAmount(amount);
            }

            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void deleteUser(User user) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_banking_app", "root", "888eight888");

            String deleteQuery =
                    "delete from tbl_user where id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);

            preparedStatement.setInt(1, user.getId());

            preparedStatement.execute();

            connection.close();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
