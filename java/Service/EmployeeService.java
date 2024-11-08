package Service;

import Model.Employees;
import Utils.AuroraSkinCareDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suraj
 */
public class EmployeeService {
    private AuroraSkinCareDB db = new AuroraSkinCareDB();

   public boolean addEmployee(Employees employee) {
    String query = "INSERT INTO employees (e_ID, e_Name, e_NIC, e_Phone_Number) VALUES (?, ?, ?, ?)";
    try (Connection conn = db.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        if (conn == null) {
            return false; // Connection is null
        }

        stmt.setString(1, employee.getE_ID());
        stmt.setString(2, employee.getE_Name());
        stmt.setString(3, employee.getE_NIC());
        stmt.setString(4, employee.getE_Phone_Number());

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        return false; // SQL error occurred
    }
}
    // Update an employee's details
    public boolean updateEmployee(Employees employee) { // Corrected method name
        String query = "UPDATE employees SET e_Name = ?, e_NIC = ?, e_Phone_Number = ? WHERE e_ID = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, employee.getE_Name());
            stmt.setString(2, employee.getE_NIC());
            stmt.setString(3, employee.getE_Phone_Number());
            stmt.setString(4, employee.getE_ID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an employee
    public boolean deleteEmployee(String e_ID) {
        String query = "DELETE FROM employees WHERE e_ID = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, e_ID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // List all employees
    public List<Employees> getAllEmployees() { // Corrected method name
        List<Employees> employees = new ArrayList<>();
        String query = "SELECT * FROM employees";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Employees employee = new Employees(rs.getString("e_ID"), rs.getString("e_Name"), rs.getString("e_NIC"), rs.getString("e_Phone_Number"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // Search employees by name, NIC, or ID
    public List<Employees> searchEmployees(String keyword) {
        List<Employees> employees = new ArrayList<>();
        String query = "SELECT * FROM employees WHERE e_ID LIKE ? OR e_Name LIKE ? OR e_NIC LIKE ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Employees employee = new Employees(rs.getString("e_ID"), rs.getString("e_Name"), rs.getString("e_NIC"), rs.getString("e_Phone_Number"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    
    // Retrieve an employee by ID
    public Employees getEmployeeByID(String e_ID) {
        Employees employee = null;
        String query = "SELECT * FROM employees WHERE e_ID = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, e_ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                employee = new Employees(rs.getString("e_ID"), rs.getString("e_Name"), rs.getString("e_NIC"), rs.getString("e_Phone_Number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

}
