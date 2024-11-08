/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Schedule;
import Utils.AuroraSkinCareDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suraj
 */
public class ScheduleService {

    private AuroraSkinCareDB db = new AuroraSkinCareDB();

    // Method to add a new schedule
    public boolean addSchedule(Schedule schedule) {
        String query = "INSERT INTO Schedule (e_ID, day_of_week, start_time, end_time) VALUES (?, ?, ?, ?)";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, schedule.getEID()); // Use getEID() to get dermatologist ID
            stmt.setString(2, schedule.getDayOfWeek());
            stmt.setTime(3, Time.valueOf(schedule.getStartTime())); // Ensure you're using Time
            stmt.setTime(4, Time.valueOf(schedule.getEndTime())); // Ensure you're using Time

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL error for debugging
            return false;
        }
    }

    // Method to update an existing schedule
    public boolean updateSchedule(Schedule schedule) {
        String query = "UPDATE Schedule SET day_of_week = ?, start_time = ?, end_time = ? WHERE schedule_ID = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the parameters
            stmt.setString(1, schedule.getDayOfWeek());

            // Convert start time and end time, handle nulls appropriately
            if (schedule.getStartTime() != null && !schedule.getStartTime().isEmpty()) {
                stmt.setTime(2, Time.valueOf(schedule.getStartTime() + ":00")); // Adding seconds
            } else {
                stmt.setNull(2, java.sql.Types.TIME);
            }

            if (schedule.getEndTime() != null && !schedule.getEndTime().isEmpty()) {
                stmt.setTime(3, Time.valueOf(schedule.getEndTime() + ":00")); // Adding seconds
            } else {
                stmt.setNull(3, java.sql.Types.TIME);
            }

            stmt.setInt(4, schedule.getScheduleID());

            // Execute the update and return the result
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);  // Debug statement
            return rowsAffected > 0; // Returns true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions
            return false; // Returns false if there was an error
        }
    }

    public boolean scheduleExists(int scheduleID) {
        String query = "SELECT COUNT(*) FROM Schedule WHERE schedule_ID = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, scheduleID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Return true if at least one row is found
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if no schedule is found
    }

    // Method to delete a schedule by ID
    public boolean deleteSchedule(int scheduleID) {
    String query = "DELETE FROM Schedule WHERE schedule_ID = ?"; // Ensure table and column names are correct
    try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, scheduleID);

        // Execute the delete and return true if a row was affected
        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace(); // Log the SQL error for debugging purposes
        return false;
    }
}

    // Method to retrieve a list of schedules for a given dermatologist
    public List<Schedule> getSchedulesByDermatologist(String dermatologistID) {
        List<Schedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM Schedule WHERE e_ID = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, dermatologistID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Schedule schedule = new Schedule(
                        rs.getInt("schedule_ID"),
                        rs.getString("e_ID"),
                        rs.getString("day_of_week"),
                        rs.getTime("start_time").toString(), // Ensure the Time is converted to String if needed
                        rs.getTime("end_time").toString() // Ensure the Time is converted to String if needed
                );
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL error for debugging
        }
        return schedules;
    }

    // Method to retrieve all schedules
    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM Schedule";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Schedule schedule = new Schedule(
                        rs.getInt("schedule_ID"),
                        rs.getString("e_ID"),
                        rs.getString("day_of_week"),
                        rs.getTime("start_time").toString(), // Ensure the Time is converted to String if needed
                        rs.getTime("end_time").toString() // Ensure the Time is converted to String if needed
                );
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL error for debugging
        }
        return schedules;
    }

    // Method to get a schedule by ID
    public Schedule getScheduleByID(int scheduleID) {
        Schedule schedule = null;
        String query = "SELECT * FROM Schedule WHERE schedule_ID = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, scheduleID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                schedule = new Schedule(
                        rs.getInt("schedule_ID"),
                        rs.getString("e_ID"),
                        rs.getString("day_of_week"),
                        rs.getTime("start_time").toString(), // Ensure the Time is converted to String if needed
                        rs.getTime("end_time").toString() // Ensure the Time is converted to String if needed
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL error for debugging
        }
        return schedule;
    }

    public List<Schedule> searchSchedules(String searchQuery) {
        List<Schedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM Schedule WHERE e_ID LIKE ? OR day_of_week LIKE ?";

        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            // Using '%' in the LIKE clause to perform a partial match search
            stmt.setString(1, "%" + searchQuery + "%");
            stmt.setString(2, "%" + searchQuery + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Schedule schedule = new Schedule(
                        rs.getInt("schedule_ID"),
                        rs.getString("e_ID"),
                        rs.getString("day_of_week"),
                        rs.getTime("start_time").toString(),
                        rs.getTime("end_time").toString()
                );
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL error for debugging
        }
        return schedules;
    }
    
    
    
  
    public Integer getScheduleByDermatologistAndDay(String eID, String dayOfWeek) {
    Integer scheduleId = null;
    String query = "SELECT schedule_ID FROM Schedule WHERE e_ID = ? AND day_of_week = ?";
    
    try (Connection connection = AuroraSkinCareDB.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
         
        // Set parameters for query
        statement.setString(1, eID);
        statement.setString(2, dayOfWeek);
        
        // Execute query
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                scheduleId = resultSet.getInt("schedule_ID");
                System.out.println("Schedule ID found for eID: " + eID + ", Day of Week: " + dayOfWeek + " -> Schedule ID: " + scheduleId);
            } else {
                System.out.println("No schedule found for eID: " + eID + ", Day of Week: " + dayOfWeek);
            }
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return scheduleId;
}
}
