/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import Model.Patient;
import Utils.AuroraSkinCareDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suraj
 */

public class PatientService {

    public static Patient searchPatient(String query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private AuroraSkinCareDB db = new AuroraSkinCareDB();

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = db.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM patient";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                patients.add(mapResultSetToPatient(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public Patient getPatientByID(String p_ID) {
        try (Connection connection = db.getConnection()) {
            String query = "SELECT * FROM patient WHERE p_ID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, p_ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToPatient(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addOrUpdatePatient(Patient patient) {
        if (getPatientByID(patient.getP_ID()) != null) {
            updatePatient(patient);
        } else {
            addPatient(patient);
        }
    }

    public void addPatient(Patient patient) {
        try (Connection connection = db.getConnection()) {
            String query = "INSERT INTO patient (p_ID, p_Name, p_NIC, p_Phone_Number, p_Email) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                setPatientData(preparedStatement, patient);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updatePatient(Patient patient) {
    try (Connection connection = db.getConnection()) {
        String query = "UPDATE patient SET p_Name = ?, p_NIC = ?, p_Phone_Number = ?, p_Email = ? WHERE p_ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, patient.getP_Name());
            preparedStatement.setString(2, patient.getP_NIC());
            preparedStatement.setString(3, patient.getP_Phone_Number());
            preparedStatement.setString(4, patient.getP_Email());
            preparedStatement.setString(5, patient.getP_ID()); 
            preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public void deletePatient(String p_ID) {
        try (Connection connection = db.getConnection()) {
            String query = "DELETE FROM patient WHERE p_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, p_ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isPatientExists(String p_NIC, String p_ID) {
        try (Connection connection = db.getConnection()) {
            String query = "SELECT COUNT(*) FROM patient WHERE p_NIC = ? OR p_ID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, p_NIC);
            ps.setString(2, p_ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public List<Patient> searchPatients(String query) {
    List<Patient> results = new ArrayList<>();
    String sql = "SELECT * FROM patient WHERE p_Name LIKE ? OR p_NIC LIKE ? OR p_ID LIKE ?";

    try (Connection conn = db.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, "%" + query + "%");
        stmt.setString(2, "%" + query + "%");
        stmt.setString(3, "%" + query + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            results.add(mapResultSetToPatient(rs));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return results;
}

    private void setPatientData(PreparedStatement ps, Patient patient) throws SQLException {
        ps.setString(1, patient.getP_ID());
        ps.setString(2, patient.getP_Name());
        ps.setString(3, patient.getP_NIC());
        ps.setString(4, patient.getP_Phone_Number());
        ps.setString(5, patient.getP_Email());
    }

    private Patient mapResultSetToPatient(ResultSet rs) throws SQLException {
        return new Patient(rs.getString("p_ID"), rs.getString("p_Name"),
                rs.getString("p_NIC"), rs.getString("p_Phone_Number"), rs.getString("p_Email"));
    }
    
    
    //new
    
      public List<Patient> searchPatientByPartial(String partial) {
    List<Patient> patients = new ArrayList<>();
    String query = "SELECT * FROM patient WHERE p_Name LIKE ? OR p_ID LIKE ? OR p_NIC LIKE ?";

    try (Connection connection = db.getConnection();
         PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setString(1, "%" + partial + "%");
        ps.setString(2, "%" + partial + "%");
        ps.setString(3, "%" + partial + "%");
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                patients.add(mapResultSetToPatient(rs));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return patients;
}
}
 








