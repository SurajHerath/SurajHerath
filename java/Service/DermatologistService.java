/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Service;

import Model.Dermatologist;
import Utils.AuroraSkinCareDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suraj
 */
public class DermatologistService {
    private AuroraSkinCareDB db = new AuroraSkinCareDB();

   public boolean addDermatologist(Dermatologist dermatologist) {
    String query = "INSERT INTO dermatologist (e_ID, Specialization) VALUES (?, ?)";
    try (Connection conn = db.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        if (conn == null) {
            return false; // Connection is null
        }

        stmt.setString(1, dermatologist.getE_ID());
        stmt.setString(2, dermatologist.getSpecialization());

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        return false; // SQL error occurred
    }
}
    // Update a dermatologist's details
  
   
   public boolean updateDermatologist(Dermatologist dermatologist) {
    String query = "UPDATE dermatologist SET Specialization = ? WHERE e_ID = ?";
    try (Connection conn = db.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, dermatologist.getSpecialization()); // Ensure you're using the correct getter
        stmt.setString(2, dermatologist.getE_ID()); // Ensure you're using the correct getter
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace(); // Print any errors for debugging
        return false;
    }
}
   
    // Delete a dermatologist
    public boolean deleteDermatologist(String e_ID) {
        String query = "DELETE FROM dermatologist WHERE e_ID = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, e_ID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // List all dermatologists
    public List<Dermatologist> getAllDermatologists() {
        List<Dermatologist> dermatologists = new ArrayList<>();
        String query = "SELECT e.e_ID, e.e_Name, e.e_NIC, e.e_Phone_Number, d.Specialization " +
                   "FROM employees e JOIN dermatologist d ON e.e_ID = d.e_ID";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Dermatologist dermatologist = new Dermatologist(
                        rs.getString("e_ID"), rs.getString("e_Name"), rs.getString("e_NIC"), rs.getString("e_Phone_Number"), rs.getString("Specialization"));
                dermatologists.add(dermatologist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
      
        return dermatologists;
        
    }

    // Search dermatologists by name, NIC, or ID
    public List<Dermatologist> searchDermatologists(String keyword) {
        List<Dermatologist> dermatologists = new ArrayList<>();
        String query = "SELECT e.e_ID, e.e_Name, e.e_NIC, e.e_Phone_Number, d.Specialization " +
                   "FROM employees e " +
                   "JOIN dermatologist d ON e.e_ID = d.e_ID " +
                   "WHERE e.e_ID LIKE ? OR e.e_Name LIKE ? OR e.e_NIC LIKE ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Dermatologist dermatologist = new Dermatologist(
                        rs.getString("e_ID"), rs.getString("e_Name"), rs.getString("e_NIC"), rs.getString("e_Phone_Number"), rs.getString("Specialization"));
                dermatologists.add(dermatologist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dermatologists;
    }

    // Get a dermatologist by ID
    public Dermatologist getDermatologistByID(String e_ID) {
        Dermatologist dermatologist = null;
        String query = "SELECT * FROM dermatologist WHERE e_ID = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, e_ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                dermatologist = new Dermatologist(
                        rs.getString("e_ID"), rs.getString("e_Name"), rs.getString("e_NIC"), rs.getString("e_Phone_Number"), rs.getString("Specialization"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dermatologist;
    }
}
