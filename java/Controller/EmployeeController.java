/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Employees;
import Service.EmployeeService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author suraj
 */
@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {

    private EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "list":
                    listEmployees(request, response);
                    break;
                case "delete":
                    deleteEmployee(request, response);
                    break;
                case "search":
                    searchEmployee(request, response);
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
                addEmployee(request, response);
            } else if ("update".equals(action)) {
                updateEmployee(request, response);
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // List all employees
    private void listEmployees(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Employees> employees = employeeService.getAllEmployees();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/employeeList.jsp").forward(request, response);
    }

    // Add a new employee
    private void addEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String e_ID = request.getParameter("e_ID");
        String e_Name = request.getParameter("e_Name");
        String e_NIC = request.getParameter("e_NIC");
        String e_Phone_Number = request.getParameter("e_Phone_Number");

        Employees employee = new Employees(e_ID, e_Name, e_NIC, e_Phone_Number);
        employeeService.addEmployee(employee);

        // Redirect to list after adding
        response.sendRedirect("EmployeeController?action=list");
    }

    // Update employee information
    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String e_ID = request.getParameter("e_ID");
        String e_Name = request.getParameter("e_Name");
        String e_NIC = request.getParameter("e_NIC");
        String e_Phone_Number = request.getParameter("e_Phone_Number");

        Employees employee = new Employees(e_ID, e_Name, e_NIC, e_Phone_Number);
        employeeService.updateEmployee(employee);

        // Redirect to list after updating
        response.sendRedirect("EmployeeController?action=list");
    }

    // Delete an employee
    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String e_ID = request.getParameter("e_ID");
        employeeService.deleteEmployee(e_ID);

        // Redirect to list after deleting
        response.sendRedirect("EmployeeController?action=list");
    }

    // Show edit form for a specific employee
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String e_ID = request.getParameter("e_ID");
        Employees employee = employeeService.getEmployeeByID(e_ID);
        request.setAttribute("employee", employee);
        request.getRequestDispatcher("/EditEmployee.jsp").forward(request, response);
    }

    // Search for employees by criteria
    private void searchEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");

        List<Employees> employees = employeeService.searchEmployees(searchQuery);
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/employeeList.jsp").forward(request, response);
    }
}
