package ge.tbc.testautomation.data;

import ge.tbc.testautomation.utils.MSSQLConnection;
import org.testng.annotations.DataProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataProvider {

    @DataProvider
    public static Object[][] getUserData() {
        try (Connection connection = MSSQLConnection.connect()) {

            String SQL = """
                    SELECT id, firstName, lastName, gender, model,
                           address1, address2, city, contact1, contact2
                    FROM dbo.RegistrationData
                    ORDER BY id
                    """;

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            SQL,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY
                    );

            ResultSet resultSet = preparedStatement.executeQuery();


            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();


            int colCount = resultSet.getMetaData().getColumnCount();
            Object[][] data = new Object[rowCount][colCount];

            int iter = 0;
            while (resultSet.next()) {

                data[iter] = new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("gender"),
                        resultSet.getString("model"),
                        resultSet.getString("address1"),
                        resultSet.getString("address2"),
                        resultSet.getString("city"),
                        resultSet.getString("contact1"),
                        resultSet.getString("contact2")
                };
                iter++;
            }

            return data;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
