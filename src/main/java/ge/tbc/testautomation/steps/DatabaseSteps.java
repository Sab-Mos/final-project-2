package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.utils.MSSQLConnection;
import ge.tbc.testautomation.utils.UserModel;

import java.sql.*;
import java.util.HashMap;

public class DatabaseSteps {
    public HashMap<String, String> getAllUsernamesAndPasswords() {
        HashMap<String, String> users = new HashMap<>();
        try (Connection connection = MSSQLConnection.connect()) {
            String SQL = "SELECT * FROM Users4";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                users.put(resultSet.getString("Username"), resultSet.getString("PASSWORDHASH"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public UserModel getSpecificUser() {
        UserModel user = new UserModel();
        try (Connection connection = MSSQLConnection.connect()) {
            String SQL = """
                    SELECT RegistrationData.id, firstName, lastName, Accounts.username
                    FROM RegistrationData
                    INNER JOIN Accounts ON RegistrationData.id = Accounts.ownerId
                    """;
            Statement statement
                    = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
//                user = new UserModel(
//                        resultSet.getInt("RegistrationData.id"),
//                        resultSet.getString("firstName"),
//                        resultSet.getString("lastName"),
//                        resultSet.getString("username")
//                );
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setUsername(resultSet.getString("username"));
            }

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(){
        try (Connection connection = MSSQLConnection.connect()){
            String SQL = "INSERT INTO Users4 (Username, PasswordHash)\n" +
                    "VALUES (?, HASHBYTES('SHA2_256', ?))";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            String username = "banner_user";
            String password = "secret_sauce";
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateUser(){
        try (Connection connection = MSSQLConnection.connect()){
            String SQL = "UPDATE Users4\n" +
                    "SET Username = ?, PasswordHash = HASHBYTES('SHA2_256', ?)\n" +
                    "    WHERE Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            String oldUsername = "different_user";
            String newUsername = "very_different_user";
            String newPassword = "more_secret_sauce";
            preparedStatement.setString(1, newUsername);
            preparedStatement.setString(2, newPassword);
            preparedStatement.setString(3, oldUsername);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ResultSet selectAllRegistrationData(Connection connection) {
        final String SQL =
                "SELECT id, firstName, lastName, gender, model, " +
                        "       address1, address2, city, contact1, contact2 " +
                        "FROM dbo.RegistrationData ORDER BY id";

        try {
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            return st.executeQuery(SQL);
        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage(), e);
        }
    }


    public void deleteUser(){
        try (Connection connection = MSSQLConnection.connect()){
            String SQL = "DELETE FROM Users4 WHERE Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            String username = "very_different_user";
            preparedStatement.setString(1, username);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}