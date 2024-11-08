/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Receptionist;
import Service.ReceptionistService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import Service.EmployeeService;
import Model.Employees;

/**
 *
 * @author suraj
 */
@WebServlet("/ReceptionistController")
public class ReceptionistController extends HttpServlet {

    private ReceptionistService receptionistService = new ReceptionistService();
     private EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "list":
                    listReceptionists(request, response);
                    break;
                case "delete":
                    deleteReceptionist(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "search":
                    searchReceptionists(request, response);
                    break;
                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                addReceptionist(request, response);
            } else if ("update".equals(action)) {
                updateReceptionist(request, response);
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // List all receptionists
    private void listReceptionists(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Receptionist> receptionists = receptionistService.getAllReceptionists();
        request.setAttribute("receptionists", receptionists);
        request.getRequestDispatcher("/ViewReceptionists.jsp").forward(request, response);
    }
    

    // Add a new receptionist
    private void addReceptionist(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    // Gather input data
    String e_ID = request.getParameter("r_ID"); // Use r_ID for Receptionist ID
    String e_Name = request.getParameter("r_Name");
    String e_NIC = request.getParameter("r_NIC");
    String e_Phone_Number = request.getParameter("r_Phone_Number");
    String spokenLanguages = request.getParameter("spoken_languages");

    // Add the Employee data
    Employees employee = new Employees(e_ID, e_Name, e_NIC, e_Phone_Number);
    boolean employeeAdded = employeeService.addEmployee(employee);

    if (employeeAdded) {
        // Add the Receptionist data
        Receptionist receptionist = new Receptionist(e_ID, e_Name, e_NIC, e_Phone_Number, spokenLanguages);
        boolean receptionistAdded = receptionistService.addReceptionist(receptionist);

        if (receptionistAdded) {
            // Redirect to list after adding
            response.sendRedirect("ReceptionistController?action=list");
        } else {
            response.sendRedirect("error.jsp");
        }
    } else {
        response.sendRedirect("error.jsp");
    }
}

    // Update receptionist information
    private void updateReceptionist(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    String e_ID = request.getParameter("e_ID");
    String e_Name = request.getParameter("e_Name");
    String e_NIC = request.getParameter("e_NIC");
    String e_Phone_Number = request.getParameter("e_Phone_Number");
    String spokenLanguages = request.getParameter("spokenLanguages");

    // Create a new employee object for updating
    Employees employee = new Employees(e_ID, e_Name, e_NIC, e_Phone_Number);
    boolean employeeUpdateSuccess = employeeService.updateEmployee(employee);

    // Update receptionist details
    Receptionist receptionist = new Receptionist(e_ID, e_Name, e_NIC, e_Phone_Number, spokenLanguages);
    boolean receptionistUpdateSuccess = receptionistService.updateReceptionist(receptionist);

    if (employeeUpdateSuccess && receptionistUpdateSuccess) {
        response.sendRedirect("ReceptionistController?action=list");
    } else {
        response.sendRedirect("error.jsp");
    }
}

    
    
    
    
    
    // Delete a receptionist
    private void deleteReceptionist(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String e_ID = request.getParameter("e_ID");
        receptionistService.deleteReceptionist(e_ID);

        // Redirect to list after deleting
        response.sendRedirect("ReceptionistController?action=list");
    }

    // Search for receptionists by criteria
    private void searchReceptionists(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");

        List<Receptionist> receptionists = receptionistService.searchReceptionists(searchQuery);
        request.setAttribute("receptionists", receptionists);
        request.getRequestDispatcher("/ViewReceptionists.jsp").forward(request, response);
    }

    
    
    
    // Show edit form for a specific receptionist
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String e_ID = request.getParameter("e_ID");
        Receptionist receptionist = receptionistService.getReceptionistByID(e_ID);
        request.setAttribute("receptionist", receptionist);
        request.getRequestDispatcher("/UpdateReceptionist.jsp").forward(request, response);
    }
}
