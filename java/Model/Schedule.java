/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;
import java.util.Date;
import java.sql.Time;

/**
 *
 * @author suraj
 */






public class Schedule {
    private int scheduleID;   // Corresponds to schedule_ID
    private String eID;       // Corresponds to e_ID
    private String dayOfWeek; // Corresponds to day_of_week
    private String startTime;  // Corresponds to start_time
    private String endTime;    // Corresponds to end_time

    // Constructor
    public Schedule(int scheduleID, String eID, String dayOfWeek, String startTime, String endTime) {
        this.scheduleID = scheduleID;
        this.eID = eID;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime.toString();
        this.endTime = endTime.toString();
    }

    // Getters and Setters
    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getEID() {
        return eID;
    }

    public void setEID(String eID) {
        this.eID = eID;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}