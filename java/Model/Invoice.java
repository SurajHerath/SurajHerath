/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;
import java.util.Date;

/**
 *
 * @author suraj
 */

public class Invoice {
    private String Invoice_ID;
    private String A_ID;
    private String P_ID;
    private double Tax_Rate;
    private double Total_Fee;
    private double Total_Amount;
    private Date Invoice_Issue_Date;

    // Constructor
    public Invoice(String Invoice_ID, String A_ID, String P_ID, double Tax_Rate, double Total_Fee, Date Invoice_Issue_Date) {
        this.Invoice_ID = Invoice_ID;
        this.A_ID = A_ID;
        this.P_ID = P_ID;
        this.Tax_Rate = Tax_Rate;
        this.Total_Fee = Total_Fee;
        this.Invoice_Issue_Date = Invoice_Issue_Date;
        this.Total_Amount = calculateTotalAmount();
    }

    // Getters and Setters
    public String getInvoice_ID() {
        return Invoice_ID;
    }

    public void setInvoice_ID(String Invoice_ID) {
        this.Invoice_ID = Invoice_ID;
    }

    public String getA_ID() {
        return A_ID;
    }

    public void setA_ID(String A_ID) {
        this.A_ID = A_ID;
    }

    public String getP_ID() {
        return P_ID;
    }

    public void setP_ID(String P_ID) {
        this.P_ID = P_ID;
    }

    public double getTax_Rate() {
        return Tax_Rate;
    }

    public void setTax_Rate(double Tax_Rate) {
        this.Tax_Rate = Tax_Rate;
    }

    public double getTotal_Fee() {
        return Total_Fee;
    }

    public void setTotal_Fee(double Total_Fee) {
        this.Total_Fee = Total_Fee;
    }

    public double getTotal_Amount() {
        return Total_Amount;
    }

    public Date getInvoice_Issue_Date() {
        return Invoice_Issue_Date;
    }

    public void setInvoice_Issue_Date(Date Invoice_Issue_Date) {
        this.Invoice_Issue_Date = Invoice_Issue_Date;
    }

    // Method to calculate total amount
    public double calculateTotalAmount() {
        return this.Total_Fee + (this.Total_Fee * this.Tax_Rate / 100);
    }

    // Method to save invoice to database
    public void saveToDatabase() {
        try {
            // JDBC Connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdb", "root", "password");

            // SQL query to insert invoice into database
            String query = "INSERT INTO Invoice (Invoice_ID, A_ID, P_ID, Tax_Rate, Total_Fee, Total_Amount, Invoice_Issue_Date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, this.Invoice_ID);
            pstmt.setString(2, this.A_ID);
            pstmt.setString(3, this.P_ID);
            pstmt.setDouble(4, this.Tax_Rate);
            pstmt.setDouble(5, this.Total_Fee);
            pstmt.setDouble(6, this.Total_Amount);
            pstmt.setDate(7, new java.sql.Date(this.Invoice_Issue_Date.getTime()));

            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to generate invoice and save to database
    public void generateInvoice() {
        this.Total_Amount = calculateTotalAmount();
        saveToDatabase();
    }
}

