/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author suraj
 */
public class Employees {
    private String e_ID;
    private String e_Name;
    private String e_NIC;
    private String e_Phone_Number;

    // Constructor
    
   public Employees(String e_ID, String e_Name, String e_NIC, String e_Phone_Number) {
        this.e_ID = e_ID;
        this.e_Name = e_Name;
        this.e_NIC = e_NIC;
        this.e_Phone_Number = e_Phone_Number;
    }

    // Getters and Setters
    public String getE_ID() { 
        return e_ID; 
    }
    
    public void setE_ID(String e_ID) {
        this.e_ID = e_ID; 
    }

    
    public String getE_Name() { 
        return e_Name; 
    }
    
    public void setE_Name(String e_Name) {
        this.e_Name = e_Name; 
    }
    

    public String getE_NIC() { 
        return e_NIC; 
    }
    
    public void setE_NIC(String e_NIC) {
        this.e_NIC = e_NIC; 
    }

    public String getE_Phone_Number() { 
        return e_Phone_Number; 
    }
    
    public void setE_Phone_Number(String e_Phone_Number) {
        this.e_Phone_Number = e_Phone_Number;
    }
}