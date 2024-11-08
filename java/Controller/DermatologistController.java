/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Dermatologist;
import Service.DermatologistService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import Service.EmployeeService;
import Model.Employees;
import Controller.EmployeeController;

/**
 *
 * @author suraj
 */
@WebServlet("/DermatologistController")
public class DermatologistController extends HttpServlet {

    private DermatologistService dermatologistService = new DermatologistService();
    private EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "list":
                    listDermatologists(request, response);
                    break;
                case "delete":
                    deleteDermatologist(request, response);
                    break;
                case "search":
                    searchDermatologist(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
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
                addDermatologist(request, response);
            } else if ("update".equals(action)) {
                updateDermatologist(request, response);
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // List all dermatologists
    private void listDermatologists(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Dermatologist> dermatologists = dermatologistService.getAllDermatologists();
        request.setAttribute("dermatologists", dermatologists);
        request.getRequestDispatcher("/ViewDermatologists.jsp").forward(request, response);
    }
    


    // Add a new dermatologist
   
    
   private void addDermatologist(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    // Gather input data
    String e_ID = request.getParameter("d_ID");
    String e_Name = request.getParameter("d_Name");
    String e_NIC = request.getParameter("d_NIC");
    String e_Phone_Number = request.getParameter("d_Phone_Number");
    String specialization = request.getParameter("d_Specialization");

    // Add the Employee data
    Employees employee = new Employees(e_ID, e_Name, e_NIC, e_Phone_Number);
    boolean employeeAdded = employeeService.addEmployee(employee);

    if (employeeAdded) {
        Dermatologist dermatologist = new Dermatologist(e_ID, e_Name, e_NIC, e_Phone_Number, specialization);
        boolean dermatologistAdded = dermatologistService.addDermatologist(dermatologist);

        if (dermatologistAdded) {
            response.sendRedirect("DermatologistController?action=list");
        } else {
            response.sendRedirect("error.jsp");
        }
    } else {
        response.sendRedirect("error.jsp");
    }
}
    
    
   
    
    
    private void updateDermatologist(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    String e_ID = request.getParameter("d_ID");
    String e_Name = request.getParameter("d_Name");
    String e_NIC = request.getParameter("d_NIC");
    String e_Phone_Number = request.getParameter("d_Phone_Number");
    String specialization = request.getParameter("d_Specialization");

    // Create a new employee object for updating
    Employees employee = new Employees(e_ID, e_Name, e_NIC, e_Phone_Number);
    boolean employeeUpdateSuccess = employeeService.updateEmployee(employee);

    // Update dermatologist details
    Dermatologist dermatologist = new Dermatologist(e_ID, e_Name, e_NIC, e_Phone_Number, specialization);
    boolean dermatologistUpdateSuccess = dermatologistService.updateDermatologist(dermatologist);

    if (employeeUpdateSuccess && dermatologistUpdateSuccess) {
        response.sendRedirect("DermatologistController?action=list");
    } else {
        response.sendRedirect("error.jsp");
    }
}
    
    
   
    
    
    // Delete a dermatologist
    private void deleteDermatologist(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String e_ID = request.getParameter("e_ID");
        dermatologistService.deleteDermatologist(e_ID);

        // Redirect to list after deleting
        response.sendRedirect("DermatologistController?action=list");
    }

    // Search for dermatologists by criteria
    private void searchDermatologist(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");

        List<Dermatologist> dermatologists = dermatologistService.searchDermatologists(searchQuery);
        request.setAttribute("dermatologists", dermatologists);
        request.getRequestDispatcher("/ViewDermatologists.jsp").forward(request, response);
    }

    // Show edit form for a specific dermatologist
   private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    String d_ID = request.getParameter("d_ID"); // Ensure this is consistent
    Dermatologist dermatologist = dermatologistService.getDermatologistByID(d_ID);
    request.setAttribute("dermatologist", dermatologist);
    request.getRequestDispatcher("/UpdateDermatologists.jsp").forward(request, response);
   }
}

