package ge.tbc.testautomation.data;

import ge.tbc.testautomation.utils.MSSQLConnection;
import org.testng.annotations.DataProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchDataProvider {

    @DataProvider(name="branchData")
    public static Object[][] getStreetData() {
        try (Connection connection = MSSQLConnection.connect()) {

            String SQL = """
                    SELECT id, street_name, street_number
                    FROM dbo.branch_cases
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
                        resultSet.getString("street_name"),
                        resultSet.getString("street_number"),

                };
                iter++;
            }

            return data;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
