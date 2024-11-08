/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.Date;

/**
 *
 * @author suraj
 */
/*@WebServlet(name = "InvoiceController", urlPatterns = {"/InvoiceController"})
public class InvoiceController extends HttpServlet {

    private AuroraSkinCareDB db = new AuroraSkinCareDB(); // Assumes your database utility class

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String invoiceID = request.getParameter("invoiceID");
        String appointmentID = request.getParameter("appointmentID");
        String patientID = request.getParameter("patientID");
        double taxRate = Double.parseDouble(request.getParameter("taxRate"));
        double totalFee = Double.parseDouble(request.getParameter("totalFee"));
        double totalAmount = totalFee + (totalFee * (taxRate / 100));
        Date issueDate = new Date(); // Assuming issue date is the current date

        try (Connection connection = db.getConnection()) {
            String sql = "INSERT INTO Invoice (invoice_ID, A_ID, P_ID, tax_rate, total_fee, total_amount, issue_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, invoiceID);
                stmt.setString(2, appointmentID);
                stmt.setString(3, patientID);
                stmt.setDouble(4, taxRate);
                stmt.setDouble(5, totalFee);
                stmt.setDouble(6, totalAmount);
                stmt.setDate(7, new java.sql.Date(issueDate.getTime()));

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    response.getWriter().write("Invoice generated successfully.");
                } else {
                    response.getWriter().write("Failed to generate invoice.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Error generating invoice.");
        }
    }
}
 */