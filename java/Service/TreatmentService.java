/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

/**
 *
 * @author suraj
 */


import Model.Treatment;
import Utils.AuroraSkinCareDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreatmentService {
    private AuroraSkinCareDB db = new AuroraSkinCareDB();

    public List<Treatment> getAllTreatments() {
        List<Treatment> treatments = new ArrayList<>();
        try (Connection connection = db.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM treatment";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                treatments.add(mapResultSetToTreatment(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return treatments;
    }

    public Treatment getTreatmentByID(String t_ID) {
        try (Connection connection = db.getConnection()) {
            String query = "SELECT * FROM treatment WHERE t_ID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, t_ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToTreatment(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addOrUpdateTreatment(Treatment treatment) {
        if (getTreatmentByID(treatment.getT_ID()) != null) {
            updateTreatment(treatment);
        } else {
            addTreatment(treatment);
        }
    }

    public void addTreatment(Treatment treatment) {
        try (Connection connection = db.getConnection()) {
            String query = "INSERT INTO treatment (t_ID, t_Name, t_Price) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                setTreatmentData(preparedStatement, treatment);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTreatment(Treatment treatment) {
        try (Connection connection = db.getConnection()) {
            String query = "UPDATE treatment SET t_Name = ?, t_Price = ? WHERE t_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, treatment.getT_Name());
                preparedStatement.setDouble(2, treatment.getT_Price());
                preparedStatement.setString(3, treatment.getT_ID());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTreatment(String t_ID) {
        try (Connection connection = db.getConnection()) {
            String query = "DELETE FROM treatment WHERE t_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, t_ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Treatment> searchTreatments(String query) {
        List<Treatment> results = new ArrayList<>();
        String sql = "SELECT * FROM treatment WHERE t_Name LIKE ? OR t_ID LIKE ?";

        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                results.add(mapResultSetToTreatment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    private void setTreatmentData(PreparedStatement ps, Treatment treatment) throws SQLException {
        ps.setString(1, treatment.getT_ID());
        ps.setString(2, treatment.getT_Name());
        ps.setDouble(3, treatment.getT_Price());
    }

    private Treatment mapResultSetToTreatment(ResultSet rs) throws SQLException {
        return new Treatment(rs.getString("t_ID"), rs.getString("t_Name"), rs.getDouble("t_Price"));
    }
    
    public List<Treatment> searchTreatmentByPartial(String partial) {
    List<Treatment> treatments = new ArrayList<>();
    String query = "SELECT * FROM treatment WHERE t_Name LIKE ? OR t_Type LIKE ? OR t_ID LIKE ?";

    try (Connection connection = db.getConnection();
         PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setString(1, "%" + partial + "%");
        ps.setString(2, "%" + partial + "%");
        ps.setString(3, "%" + partial + "%");
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                treatments.add(mapResultSetToTreatment(rs));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return treatments;
}
}