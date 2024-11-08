/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

/**
 *
 * @author suraj
 */
import Model.Appointment;
import Model.Patient;
import Model.Schedule;
import Model.TimeRange;
import Utils.AuroraSkinCareDB;
import java.sql.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import Service.ScheduleService;
import Service.TimeRangeService;

public class AppointmentService {

    private final AuroraSkinCareDB db = new AuroraSkinCareDB();
    private final PatientService patientService = new PatientService();
    private final ScheduleService scheduleService = new ScheduleService();
    private final TimeRangeService timeRangeService = new TimeRangeService();

    
    
    
   
    

    public Patient searchPatient(String query) {
        List<Patient> patients = patientService.searchPatients(query);
        if (!patients.isEmpty()) {
            return patients.get(0); // Return the first matched patient
        }
        return null; // No patient found
    }

    
 

    // Method to add an appointment
    public boolean addAppointment(Appointment appointment) {
        String query = "INSERT INTO appointment (a_ID, p_ID, e_ID, a_Date, time_range_ID, schedule_ID, t_ID, reg_Fee, t_Price, a_Status) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, appointment.getA_ID());
            stmt.setString(2, appointment.getP_ID());
            stmt.setString(3, appointment.getE_ID());
            stmt.setString(4, appointment.getA_Date());
            stmt.setString(5, appointment.getA_time());
            stmt.setInt(6, appointment.getTime_range_ID());
            stmt.setInt(7, appointment.getSchedule_ID());
            stmt.setString(8, appointment.getT_ID());
            stmt.setDouble(9, appointment.getReg_Fee());
            stmt.setDouble(10, appointment.getT_Price());
            stmt.setString(11, appointment.getA_Status());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if insertion is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there's an error
        }
    }

    // Method to check if the appointment time slot is available
    public boolean isTimeSlotAvailable(int timeRangeID) {
        return timeRangeService.isTimeSlotAvailable(timeRangeID); // Assume this checks slot availability
    }

    // Method to mark the time slot as booked after adding the appointment
    public boolean markTimeSlotAsBooked(int timeRangeID, String p_ID) {
        return timeRangeService.markAsBooked(timeRangeID, p_ID); // Assume this marks the slot as booked
    }




    // Update an appointment
    public boolean updateAppointment(Appointment appointment) {
        String sql = "UPDATE Appointment SET p_ID=?, e_ID=?, a_Date=?, a_time=?, t_ID=?, reg_Fee=?, t_Price=?, a_Status=?, schedule_ID=?, time_range_ID=? WHERE a_ID=?";
        try (Connection connection = db.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, appointment.getP_ID());
            statement.setString(2, appointment.getE_ID());
            statement.setString(4, appointment.getA_Date());
            statement.setString(4, appointment.getA_time());
            statement.setString(5, appointment.getT_ID());
            statement.setDouble(6, appointment.getReg_Fee());
            statement.setDouble(7, appointment.getT_Price());
            statement.setString(8, appointment.getA_Status());
            statement.setInt(9, appointment.getSchedule_ID());
            statement.setInt(10, appointment.getTime_range_ID());
            statement.setString(11, appointment.getA_ID());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an appointment
    public boolean deleteAppointment(String a_ID) {
        String sql = "DELETE FROM appointment WHERE a_ID = ?";
        try (Connection connection = db.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, a_ID);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get an appointment by ID
    public Appointment getAppointment(String a_ID) {
        String sql = "SELECT * FROM Appointment WHERE a_ID=?";
        try (Connection connection = db.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, a_ID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Appointment(
                        resultSet.getString("a_ID"),
                        resultSet.getString("p_ID"),
                        resultSet.getString("e_ID"),
                        resultSet.getString("a_Date"),
                        resultSet.getString("a_time"),
                        resultSet.getString("t_ID"),
                        resultSet.getDouble("reg_Fee"),
                        resultSet.getDouble("t_Price"),
                        resultSet.getString("a_Status"),
                        resultSet.getInt("schedule_ID"),
                        resultSet.getInt("time_range_ID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // List all appointments
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointment";
        try (Connection connection = db.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                appointments.add(new Appointment(
                        resultSet.getString("a_ID"),
                        resultSet.getString("p_ID"),
                        resultSet.getString("e_ID"),
                        resultSet.getString("a_Date"),
                        resultSet.getString("a_time"),
                        resultSet.getString("t_ID"),
                        resultSet.getDouble("reg_Fee"),
                        resultSet.getDouble("t_Price"),
                        resultSet.getString("a_Status"),
                        resultSet.getInt("schedule_ID"),
                        resultSet.getInt("time_range_ID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    // Search appointments by ID, patient ID, or employee ID
    public List<Appointment> searchAppointments(String searchTerm) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointment WHERE a_ID LIKE ? OR p_ID LIKE ? OR e_ID LIKE ?";
        try (Connection connection = db.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            String query = "%" + searchTerm + "%";
            statement.setString(1, query);
            statement.setString(2, query);
            statement.setString(3, query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                appointments.add(new Appointment(
                        resultSet.getString("a_ID"),
                        resultSet.getString("p_ID"),
                        resultSet.getString("e_ID"),
                        resultSet.getString("a_Date"),
                        resultSet.getString("a_time"),
                        resultSet.getString("t_ID"),
                        resultSet.getDouble("reg_Fee"),
                        resultSet.getDouble("t_Price"),
                        resultSet.getString("a_Status"),
                        resultSet.getInt("schedule_ID"),
                        resultSet.getInt("time_range_ID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    // Reset weekly slots
    public void resetWeeklySlots() {
        String resetQuery = "UPDATE TimeRange SET is_booked = FALSE, patient_id = NULL";
        try (Connection connection = db.getConnection(); PreparedStatement statement = connection.prepareStatement(resetQuery)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
