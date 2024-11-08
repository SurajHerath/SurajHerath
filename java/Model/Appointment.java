/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.*;
import java.util.Date;


/**
 *
 * @author suraj
 */
public class Appointment {

    private String a_ID;
    private String p_ID;
    private String e_ID;
    private String a_Date;
    private String a_time;
    private String t_ID;
    private double reg_Fee;
    private double t_Price;
    private String a_Status;
    private int schedule_ID;
    private int time_range_ID;

    public Appointment(String a_ID, String p_ID, String e_ID, String a_Date, String a_time,String t_ID, double reg_Fee, double t_Price, String a_Status,int schedule_ID, int time_range_ID) {
        this.a_ID = a_ID;
        this.p_ID = p_ID;
        this.e_ID = e_ID;
        this.a_Date = a_Date;
        this.a_time = a_time;
        this.t_ID = t_ID;
        this.reg_Fee = reg_Fee;
        this.t_Price = t_Price;
        this.a_Status = a_Status;
        this.schedule_ID = schedule_ID;
        this.time_range_ID = time_range_ID;
    }

    // Getters and Setters
    public String getA_ID() {
        return a_ID;
    }

    public void setA_ID(String a_ID) {
        this.a_ID = a_ID;
    }

    public String getP_ID() {
        return p_ID;
    }

    public void setP_ID(String p_ID) {
        this.p_ID = p_ID;
    }

    public String getE_ID() {
        return e_ID;
    }

    public void setE_ID(String e_ID) {
        this.e_ID = e_ID;
    }

    public String getA_Date() {
        return a_Date;
    }

    public void setA_Date(String a_Date) {
        this.a_Date = a_Date;
    }

    public String getA_time() {
        return a_time;
    }

    public void setA_time(String a_time) {
        this.a_time = a_time;
    }

    public String getT_ID() {
        return t_ID;
    }

    public void setT_ID(String t_ID) {
        this.t_ID = t_ID;
    }

    public double getReg_Fee() {
        return reg_Fee;
    }

    public void setReg_Fee(double reg_Fee) {
        this.reg_Fee = reg_Fee;
    }

    public double getT_Price() {
        return t_Price;
    }

    public void setT_Price(double t_Price) {
        this.t_Price = t_Price;
    }

    public String getA_Status() {
        return a_Status;
    }

    public void setA_Status(String a_Status) {
        this.a_Status = a_Status;
    }

    public int getSchedule_ID() {
        return schedule_ID;
    }

    public void setSchedule_ID(int schedule_ID) {
        this.schedule_ID = schedule_ID;
    }

    public int getTime_range_ID() {
        return time_range_ID;
    }

    public void setTime_range_ID(int time_range_ID) {
        this.time_range_ID = time_range_ID;
    }
}
