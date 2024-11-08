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

public class TimeRange {
    private int timeRangeID;      // Corresponds to time_range_ID
    private int scheduleID;       // Corresponds to schedule_ID
    private Time startTime;       // Corresponds to start_time
    private Time endTime;         // Corresponds to end_time
    private boolean isBooked;      // Corresponds to is_booked
    private String patientID;      // Corresponds to p_ID

    // Default constructor
    public TimeRange() {}

    // Parameterized constructor
    public TimeRange(int timeRangeID, int scheduleID, Time startTime, Time endTime, boolean isBooked, String patientID) {
        this.timeRangeID = timeRangeID;
        this.scheduleID = scheduleID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isBooked = isBooked;
        this.patientID = patientID;
    }

    // Getters and Setters
    public int getTimeRangeId() {
        return timeRangeID;
    }

    public void setTimeRangeId(int timeRangeId) {
        this.timeRangeID = timeRangeId;
    }

    public int getScheduleId() {
        return scheduleID;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleID = scheduleId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public boolean getisBooked() {
        return isBooked;
    }

    public void setisBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public String getPatientId() {
        return patientID;
    }

    public void setPatientId(String patientId) {
        this.patientID = patientId;
    }

    
}
