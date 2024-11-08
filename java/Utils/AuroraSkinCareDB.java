/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author suraj
 */
public class AuroraSkinCareDB {

    private static final String URL = "jdbc:mysql://localhost:3306/aura_skin_care_clinic";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    // Get the connection to the database
    public static Connection getConnection() throws SQLException {

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

}
