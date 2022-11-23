package p02_database;

import java.sql.*;

public class JDBC {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3361/EMP";
    private static final String USER_NAME = "admin";
    private static final String PASS_WORD = "admin";

    public Employee getEmployeeById(int id) throws Exception {
        Employee employee = new Employee();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(DRIVER);  // 1. load driver
            connection = DriverManager.getConnection(URL, USER_NAME, PASS_WORD);  // 2. connect to database
            String sql = "SELECT * FROM emp WHERE ID = " + id;  // 3. define sql statement
            statement = connection.createStatement();  // 4. create a Statement object
            resultSet = statement.executeQuery(sql);  // 5. use statement object to execute sql query

            // get resultSet's data to java object
            while (resultSet.next()) {
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
            }

            // close the connection and other resources
            resultSet.close();
            statement.close();
            connection.close();

            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
                resultSet = null;
            }
            if (statement != null) {
                statement.close();
                statement = null;
            }
            if (connection != null) {
                connection.close();
                connection = null;
            }
        }

        return null;
    }
}
