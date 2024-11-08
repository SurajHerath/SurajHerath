/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author suraj
 */

public class Dermatologist extends Employees {
    private String specialization;

    public Dermatologist(String e_ID, String e_Name, String e_NIC, String e_Phone_Number, String specialization) {
        super(e_ID, e_Name, e_NIC, e_Phone_Number); // Call to super class constructor
        this.specialization = specialization;
    }

    // Getters and setters
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
