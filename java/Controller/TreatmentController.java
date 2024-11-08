/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Model.Treatment;

import Service.TreatmentService;

/**
 *
 * @author suraj
 */
@WebServlet("/TreatmentController")
public class TreatmentController extends HttpServlet {

    private final TreatmentService treatmentService = new TreatmentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            String t_ID = request.getParameter("t_ID");
            Treatment treatment = treatmentService.getTreatmentByID(t_ID);
            request.setAttribute("treatment", treatment);
            request.getRequestDispatcher("/UpdateTreatment.jsp").forward(request, response);

        } else if ("delete".equals(action)) {
            String t_ID = request.getParameter("t_ID");
            treatmentService.deleteTreatment(t_ID);
            response.sendRedirect("TreatmentController?action=list");

        } else if ("search".equals(action)) {
            String query = request.getParameter("query");
            if (query != null && !query.isEmpty()) {
                List<Treatment> searchResults = treatmentService.searchTreatments(query);
                request.setAttribute("searchResults", searchResults);
                request.getRequestDispatcher("/ViewTreatment.jsp").forward(request, response);
            } else {
                response.sendRedirect("TreatmentController?action=list");
            }

        } else if ("suggest".equals(action)) {
            String query = request.getParameter("query");
            if (query != null && !query.isEmpty()) {
//                List<Treatment> suggestions = treatmentService.searchTreatmentByPartial(query);
//                response.setContentType("application/json");
//                PrintWriter out = response.getWriter();
//                out.write(new Gson().toJson(suggestions));
//                out.flush();
            } else {
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.write("[]");
                out.flush();
            }

        } else if ("list".equals(action)) {
            List<Treatment> treatments = treatmentService.getAllTreatments();
            request.setAttribute("treatments", treatments);
            request.getRequestDispatcher("/ViewTreatment.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addTreatment(request, response);
                break;
            case "update":
                updateTreatment(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    // Method to handle adding a new treatment
    private void addTreatment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Retrieve parameters from the form with correct names
    String t_ID = request.getParameter("treatmentID");
    String t_Name = request.getParameter("treatmentName");
    
    // Handle potential null value for treatment price
    double t_Price = 0.0;
    String t_PriceStr = request.getParameter("treatmentPrice");
    if (t_PriceStr != null && !t_PriceStr.trim().isEmpty()) {
        try {
            t_Price = Double.parseDouble(t_PriceStr.trim());
        } catch (NumberFormatException e) {
            // Log or handle the error as needed
            t_Price = 0.0; // set a default or error value
        }
    }
    
    // Create a Treatment object
    Treatment treatment = new Treatment(t_ID, t_Name, t_Price);
    
    // Add or update treatment in the database
    treatmentService.addTreatment(treatment);
    
    // Redirect to the treatment list page
    response.sendRedirect("TreatmentController?action=list");
}
    
 

    // Method to handle updating an existing treatment
    private void updateTreatment(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String t_ID = request.getParameter("t_ID");
    String t_Name = request.getParameter("t_Name");
    double t_Price = Double.parseDouble(request.getParameter("t_Price"));

    Treatment treatment = new Treatment(t_ID, t_Name, t_Price);
    treatmentService.updateTreatment(treatment);
    response.sendRedirect("TreatmentController?action=list");
    
}
    
}