/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Receptionist;
import Utils.AuroraSkinCareDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceptionistService {
    private AuroraSkinCareDB db = new AuroraSkinCareDB();

    // Add a new receptionist
    public boolean addReceptionist(Receptionist receptionist) {
        String query = "INSERT INTO receptionists (e_ID, spoken_languages) VALUES (?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
         
            if (conn == null) {
            return false; // Connection is null
        }
            stmt.setString(1, receptionist.getE_ID()); 
            stmt.setString(2, receptionist.getSpokenLanguages());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            return false;
        }
    }
    

    // Update a receptionist's details
    public boolean updateReceptionist(Receptionist receptionist) {
        String query = "UPDATE receptionists SET spoken_languages = ? WHERE e_ID = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, receptionist.getSpokenLanguages());
            stmt.setString(2, receptionist.getE_ID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    

    // Delete a receptionist
    public boolean deleteReceptionist(String e_ID) {
        String query = "DELETE FROM receptionists WHERE e_ID = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, e_ID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve a list of all receptionists
   public List<Receptionist> getAllReceptionists() {
    List<Receptionist> receptionists = new ArrayList<>();
    String query = "SELECT e.e_ID, e.e_Name, e.e_NIC, e.e_Phone_Number, r.spoken_languages " +
                   "FROM employees e JOIN receptionists r ON e.e_ID = r.e_ID";
    try (Connection conn = db.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Receptionist receptionist = new Receptionist(
                    rs.getString("e_ID"),
                    rs.getString("e_Name"),
                    rs.getString("e_NIC"),
                    rs.getString("e_Phone_Number"),
                    rs.getString("spoken_languages")); // Assuming "spoken_languages" is the correct column name
            receptionists.add(receptionist);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return receptionists;
}
    
    
    
    

    // Search receptionists by name, NIC, or ID
    public List<Receptionist> searchReceptionists(String keyword) {
    List<Receptionist> receptionists = new ArrayList<>();
    String query = "SELECT e.e_ID, e.e_Name, e.e_NIC, e.e_Phone_Number, r.spoken_languages " +
                   "FROM employees e " +
                   "JOIN receptionists r ON e.e_ID = r.e_ID " +
                   "WHERE e.e_ID LIKE ? OR e.e_Name LIKE ? OR e.e_NIC LIKE ?";
    try (Connection conn = db.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, "%" + keyword + "%");
        stmt.setString(2, "%" + keyword + "%");
        stmt.setString(3, "%" + keyword + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Receptionist receptionist = new Receptionist(
                    rs.getString("e_ID"),
                    rs.getString("e_Name"),
                    rs.getString("e_NIC"),
                    rs.getString("e_Phone_Number"),
                    rs.getString("spoken_languages")); // Assuming "spoken_languages" is the correct column name
            receptionists.add(receptionist);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return receptionists;
}

    
    // Get a receptionist by ID
    public Receptionist getReceptionistByID(String e_ID) {
        Receptionist receptionist = null;
        String query = "SELECT * FROM receptionists WHERE e_ID = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, e_ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                receptionist = new Receptionist(
                        rs.getString("e_ID"), rs.getString("e_Name"), rs.getString("e_NIC"), rs.getString("e_Phone_Number"), rs.getString("spoken_languages"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receptionist;
    }

    

    // Appointment-related methods (skeleton implementations)
    public void bookAppointment(String appointmentDetails) {
        // Logic to book an appointment
    }

    public void updateAppointmentDetails(String appointmentId, String updatedDetails) {
        // Logic to update appointment details
    }

    public void searchAppointment(String criteria, String value) {
        // Logic to search for appointments based on criteria
    }

    public void checkAvailability(String date) {
        // Logic to check availability on a given date
    }

    public void viewAppointments() {
        // Logic to retrieve and display all appointments
    }

    public void cancelAppointment(String appointmentId) {
        // Logic to cancel an appointment
    }
}
