package Utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Manages database connection using properties from db.properties.
 */

public class SqlConnection
{
    private static Connection con;
    private static String port;
    private static String databaseName;
    private static String userName;
    private static String password;
    private static PreparedStatement ps;
    private static ResultSet rs;


    static
    // Loads database connection properties
    {
        Properties props = new Properties();

        try (InputStream input = SqlConnection.class.getClassLoader().getResourceAsStream("db.properties"))
        {
            if (input == null)
            {
                throw new RuntimeException("db.properties not found");
            }
            props.load(input);

            port = props.getProperty("port", "1433");
            databaseName = props.getProperty("databaseName");
            userName = props.getProperty("userName", "sa");
            password = props.getProperty("password");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Returns a database connection. Establishes a new connection if not already created.
     *
     * @return the database connection
     */
    public static Connection getConnection()
    {
        try
        {
            if (con == null || con.isClosed())
            {
                con = DriverManager.getConnection("jdbc:sqlserver://localhost:" + port + ";databaseName=" + databaseName, userName, password);
                System.out.println("Database connection established.");
                return con;
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error establishing connection: " + e.getMessage());
        }

        return con;
    }
}
