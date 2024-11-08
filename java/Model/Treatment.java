/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author suraj
 */
public class Treatment {
    private String t_ID;
    private String t_Name;
    private double t_Price;

    // Constructor to initialize Treatment object
    public Treatment(String t_ID, String t_Name, double t_Price) {
        this.t_ID = t_ID;
        this.t_Name = t_Name;
        this.t_Price = t_Price;
    }

    // Getters and Setters for each attribute
    public String getT_ID() {
        return t_ID;
    }

    public void setT_ID(String t_ID) {
        this.t_ID = t_ID;
    }

    public String getT_Name() {
        return t_Name;
    }

    public void setT_Name(String t_Name) {
        this.t_Name = t_Name;
    }

    public double getT_Price() {
        return t_Price;
    }

    public void setT_Price(double t_Price) {
        this.t_Price = t_Price;
    }

}