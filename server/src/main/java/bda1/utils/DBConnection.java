package bda1.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection connection = null;


    public static Connection getDBConnection() {
        if (connection == null) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection(
                        "jdbc:oracle:thin:@192.168.56.4:1521:xe", "agence", "1234");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return connection;
    }

}
