/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author suraj
 */


public class Receptionist extends Employees {

    private String spokenLanguages;

    public Receptionist(String e_ID, String e_Name, String e_NIC, String e_Phone_Number, String spokenLanguages) {
        super(e_ID, e_Name, e_NIC, e_Phone_Number);
        this.spokenLanguages = spokenLanguages;
    }

    public String getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(String spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    // Appointment-related methods
    public void bookAppointment() {
        // Implement booking logic
    }

    public void updateAppointmentDetails() {
        // Implement update logic
    }

    public void searchAppointment() {
        // Implement search logic
    }

    public void checkAvailability() {
        // Implement check availability logic
    }

    public void viewAppointments() {
        // Implement view appointments logic
    }

    public void cancelAppointment() {
        // Implement cancellation logic
    }
}
