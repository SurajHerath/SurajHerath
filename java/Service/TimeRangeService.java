/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.TimeRange;
import Utils.AuroraSkinCareDB;
/**
 *
 * @author suraj
 */
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TimeRangeService {
    private AuroraSkinCareDB db = new AuroraSkinCareDB(); // Use your database utility class

    public boolean addTimeRange(TimeRange timeRange) {
    String sql = "INSERT INTO timeRange (schedule_ID, start_time, end_time, is_booked, p_ID) VALUES (?, ?, ?, ?, ?)";
    try (Connection connection = db.getConnection(); // Get connection from AuroraSkinCareDB
         PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, timeRange.getScheduleId());
        stmt.setTime(2, timeRange.getStartTime());
        stmt.setTime(3, timeRange.getEndTime());
        stmt.setBoolean(4, timeRange.getisBooked());
        
        // If patientId is null, set it as SQL NULL
        if (timeRange.getPatientId() == null) {
            stmt.setNull(5, java.sql.Types.VARCHAR);
        } else {
            stmt.setString(5, timeRange.getPatientId());
        }

        return stmt.executeUpdate() > 0; // Executes insert and returns whether rows were affected
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Return false if there was an exception
    }
}


    public boolean updateTimeRange(TimeRange timeRange) {
    String sql = "UPDATE timeRange SET schedule_ID = ?, start_time = ?, end_time = ?, is_booked = ?, p_ID = ? WHERE time_range_ID = ?";
    
    try (Connection connection = db.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {
        
        // Set parameters for the update statement
        stmt.setInt(1, timeRange.getScheduleId());
        stmt.setTime(2, timeRange.getStartTime());
        stmt.setTime(3, timeRange.getEndTime());
        stmt.setBoolean(4, timeRange.getisBooked());

        // Handle optional patientId field (nullable)
        if (timeRange.getPatientId() == null || timeRange.getPatientId().isEmpty()) {
            stmt.setNull(5, java.sql.Types.VARCHAR);
        } else {
            stmt.setString(5, timeRange.getPatientId());
        }

        // Set the time range ID for the WHERE clause
        stmt.setInt(6, timeRange.getTimeRangeId());

        // Execute the update and check if it affected any rows
        return stmt.executeUpdate() > 0;
        
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Return false in case of an exception
    }
}

    // Delete a time range by ID
    public boolean deleteTimeRange(int timeRangeId) {
        String sql = "DELETE FROM timerange WHERE time_range_ID = ?"; // Check table and column names
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, timeRangeId);

            // Execute the delete statement and check if any rows were affected
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<TimeRange> searchByScheduleId(int scheduleId) {
        List<TimeRange> timeRanges = new ArrayList<>();
        String sql = "SELECT * FROM timeRange WHERE schedule_ID = ?";
        try (Connection connection = db.getConnection(); // Get connection from AuroraSkinCareDB
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, scheduleId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TimeRange timeRange = new TimeRange(
                    rs.getInt("time_range_ID"),
                    rs.getInt("schedule_ID"),
                    rs.getTime("start_time"),
                    rs.getTime("end_time"),
                    rs.getBoolean("is_booked"),
                    rs.getString("p_ID")
                );
                timeRanges.add(timeRange);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeRanges;
    }

    public List<TimeRange> listAllTimeRanges() {
        List<TimeRange> timeRanges = new ArrayList<>();
        String sql = "SELECT * FROM timeRange";
        try (Connection connection = db.getConnection(); // Get connection from AuroraSkinCareDB
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TimeRange timeRange = new TimeRange(
                    rs.getInt("time_range_ID"),
                    rs.getInt("schedule_ID"),
                    rs.getTime("start_time"),
                    rs.getTime("end_time"),
                    rs.getBoolean("is_booked"),
                    rs.getString("p_ID")
                );
                timeRanges.add(timeRange);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeRanges;
    }
    
     // Get a TimeRange by its ID
    public TimeRange getTimeRangeById(int timeRangeId) {
    TimeRange timeRange = null;
    String query = "SELECT * FROM timeRange WHERE time_range_ID = ?";
    
    // Corrected connection retrieval
    try (Connection connection = db.getConnection(); // Use your AuroraSkinCareDB to get the connection
         PreparedStatement stmt = connection.prepareStatement(query)) {
        
        stmt.setInt(1, timeRangeId);  // Set the time range ID parameter

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Retrieve the data from the result set and populate the TimeRange object
                int scheduleId = rs.getInt("schedule_ID");
                Time startTime = rs.getTime("start_time");
                Time endTime = rs.getTime("end_time");
                boolean isBooked = rs.getBoolean("is_booked");
                String patientId = rs.getString("p_ID");

                // Create the TimeRange object
                timeRange = new TimeRange(timeRangeId, scheduleId, startTime, endTime, isBooked, patientId);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return timeRange;  // Return the TimeRange object (or null if not found)
}

    
    
    
  public List<TimeRange> getAvailableTimeRangesByScheduleId(int scheduleID) throws SQLException {
    List<TimeRange> availableTimeRanges = new ArrayList<>();
    String query = "SELECT * FROM TimeRange WHERE schedule_ID = ? AND is_booked = false";
    try (Connection connection = AuroraSkinCareDB.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, scheduleID);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                TimeRange timeRange = new TimeRange(
                    resultSet.getInt("time_range_ID"),
                    resultSet.getInt("schedule_ID"),
                    resultSet.getTime("start_time"),
                    resultSet.getTime("end_time"),
                    resultSet.getBoolean("is_booked"),
                    resultSet.getString("p_ID")
                );
                availableTimeRanges.add(timeRange);
            }
        }
    }
    return availableTimeRanges;
}

  public boolean updateTimeSlot(int time_range_ID, String p_ID, boolean is_booked) {
    String sql = "UPDATE timerange SET is_booked = ?, p_ID = ? WHERE time_range_ID = ?";
    try (Connection conn = AuroraSkinCareDB.getConnection(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setBoolean(1, is_booked);
        stmt.setString(2, p_ID);
        stmt.setInt(3, time_range_ID);

        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0; // Return true if the update was successful
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Return false if there was an error
    }
}

  
  
  
  
  // Method to check if a time slot is available (not booked)
    public boolean isTimeSlotAvailable(int timeRangeID) {
        String query = "SELECT is_booked FROM time_range WHERE time_range_ID = ?";
        
        try (Connection connection = db.getConnection(); // Get connection from AuroraSkinCareDB
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setInt(1, timeRangeID); // Set the time range ID parameter
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // If slot is not booked, return true
                    return !resultSet.getBoolean("is_booked");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if the slot is not found or an error occurred
    }

    // Method to mark a time slot as booked and assign it to a patient
    public boolean markAsBooked(int timeRangeID, String patientID) {
        String updateQuery = "UPDATE time_range SET is_booked = true, p_ID = ? WHERE time_range_ID = ?";
        
        try (Connection connection = db.getConnection(); // Get connection from AuroraSkinCareDB
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            
            preparedStatement.setString(1, patientID); // Set the patient ID
            preparedStatement.setInt(2, timeRangeID); // Set the time range ID
            
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Time slot successfully booked for patient: " + patientID);
                return true;
            } else {
                System.out.println("Failed to book the time slot.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }

    // Method to cancel a booking for a time slot and make it available again
    public void cancelBooking(String timeRangeID) {
        String updateQuery = "UPDATE time_range SET is_booked = false, p_ID = NULL WHERE time_range_ID = ?";
        
        try (Connection connection = db.getConnection(); // Get connection from AuroraSkinCareDB
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            
            preparedStatement.setString(1, timeRangeID); // Set the time range ID
            
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Time slot booking cancelled successfully.");
            } else {
                System.out.println("Failed to cancel the time slot booking.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    
}
