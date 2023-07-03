package comn.functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    public static final String DateFormat = "yyyy-MM-dd HH:mm:ss";
    public static final String DateFormatSimplified = "yyyy-MM-dd";
    public static final String TimeFormat = "HH:mm:ss";

    private static final String address = "localhost";
    private static final int port = 3306;
    private static final String username = "root";
    private static final String password = "";
    private static final String database = "bdd_lpp";
    private static final String charset = "utf8";

    private static Connection connection = null;

    public static Connection getConnection() throws IOException, SQLException {
        if (connection == null || !connection.isValid(0)) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://" + address + ":" + port + "/" + database + "?charset=" + charset,
                        username,
                        password
                );
            } catch (SQLException e) {
                throw new IOException("Failed to connect to the database: " + address + ":" + port);
            }
        }
        return connection;
    }

    public static int getNextIncrement(String table, boolean commit) {
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT AUTO_INCREMENT AS val FROM INFORMATION_SCHEMA.TABLES " +
                            "WHERE table_name = '" + table + "' AND table_schema = '" + database + "'"
            );
            if (resultSet.next()) {
                int value = resultSet.getInt("val");
                if (resultSet.wasNull()) {
                    return 1;
                } else {
                    return value;
                }
            } else {
                return 1;
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}