/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;

/**
 *
 * @author suraj
 */


public class Patient {
    private String p_ID;
    private String p_Name;
    private String p_NIC;
    private String p_Phone_Number;
    private String p_Email; 

    // Constructor
    public Patient(String p_ID, String p_Name, String p_NIC, String p_Phone_Number, String p_Email) {
        this.p_ID = p_ID;
        this.p_Name = p_Name;
        this.p_NIC = p_NIC;
        this.p_Phone_Number = p_Phone_Number;
        this.p_Email = p_Email;
    }

    // Getters and Setters
    public String getP_ID() {
        return p_ID;
    }

    public void setP_ID(String p_ID) {
        this.p_ID = p_ID;
    }

    public String getP_Name() {
        return p_Name;
    }

    public void setP_Name(String p_Name) {
        this.p_Name = p_Name;
    }

    public String getP_NIC() {
        return p_NIC;
    }

    public void setP_NIC(String p_NIC) {
        this.p_NIC = p_NIC;
    }

    public String getP_Phone_Number() {
        return p_Phone_Number;
    }

    public void setP_Phone_Number(String p_Phone_Number) {
        this.p_Phone_Number = p_Phone_Number;
    }

    public String getP_Email() {
        return p_Email;
    }

    public void setP_Email(String p_Email) {
        this.p_Email = p_Email;
    }

    
    
    
    //up set clear...........................
    
    
    
    
    
    
    // Method to save patient to the database
    public void saveToDatabase() {
        try {
            // JDBC Connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/;databaseName=aura_skin_care_clinic.patient", "root", "12345");

            // SQL query to insert patient into the database
            String query = "INSERT INTO patient (p_ID, p_Name, p_NIC, p_Phone_Number, p_Email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, this.p_ID);
            pstmt.setString(2, this.p_Name);
            pstmt.setString(3, this.p_NIC);
            pstmt.setString(4, this.p_Phone_Number);
            pstmt.setString(5, this.p_Email);

            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update patient details in the database
    public void updateDetails() {
        try {
            // JDBC Connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/;databaseName=aura_skin_care_clinic.patient", "root", "12345");

            // SQL query to update patient details in the database
            String query = "UPDATE patient SET p_Name = ?, p_NIC = ?, p_Phone_Number = ?, p_Email = ? WHERE p_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, this.p_Name);
            pstmt.setString(2, this.p_NIC);
            pstmt.setString(3, this.p_Phone_Number);
            pstmt.setString(4, this.p_Email);
            pstmt.setString(5, this.p_ID);

            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to view all appointments for the patient
    public ResultSet viewAppointments() {
        ResultSet rs = null;
        try {
            // JDBC Connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/;databaseName=aura_skin_care_clinic", "root", "12345");

            // SQL query to get all appointments for the patient
            String query = "SELECT * FROM Appointment WHERE p_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, this.p_ID);

            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // Method to pay registration fee (basic example)
    public void payRegistrationFee(double amount) {
        System.out.println("Registration fee of " + amount + " paid for patient " + this.p_ID);
    }
}

