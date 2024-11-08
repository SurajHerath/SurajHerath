/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import Model.Patient;

import Service.PatientService;

/**
 *
 * @author suraj
 */
@WebServlet("/PatientController")
public class PatientController extends HttpServlet {

    private PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            String p_ID = request.getParameter("p_ID");
            Patient patient = patientService.getPatientByID(p_ID);
            request.setAttribute("patient", patient);
            String[] nameParts = (patient.getP_Name() != null) ? patient.getP_Name().trim().split(" ") : new String[0];
            String firstName = nameParts.length > 0 ? nameParts[0] : "";
            String middleName = nameParts.length > 2 ? nameParts[1] : ""; // Assuming middle name is in the second position
            String lastName = nameParts.length > 1 ? nameParts[nameParts.length - 1] : ""; // Last name is the last part

            // Set name parts as request attributes
            request.setAttribute("firstName", firstName);
            request.setAttribute("middleName", middleName);
            request.setAttribute("lastName", lastName);

            request.getRequestDispatcher("/UpdatePatient.jsp").forward(request, response);

            
            
        } else if ("delete".equals(action)) {
            String p_ID = request.getParameter("p_ID");
            patientService.deletePatient(p_ID);
            response.sendRedirect("PatientController?action=list");

        } else if ("search".equals(action)) {
            String query = request.getParameter("query"); // Get the query from request parameters
            if (query != null && !query.isEmpty()) {
                List<Patient> searchResults = patientService.searchPatients(query);
                request.setAttribute("searchResults", searchResults);
                request.getRequestDispatcher("/ViewPatient.jsp").forward(request, response);
            } else {
                // Handle cases where the query is empty or null, if needed
                response.sendRedirect("PatientController?action=list");
            }

        } else if ("suggest".equals(action)) {
            String query = request.getParameter("query"); // Retrieve the query from request parameters
            if (query != null && !query.isEmpty()) {
//                List<Patient> suggestions = patientService.searchPatientByPartial(query);
//                response.setContentType("application/json");
//                PrintWriter out = response.getWriter();
//                out.write(new Gson().toJson(suggestions));
//                out.flush();
            } else {
                // Handle empty or null query if needed, possibly send an empty response or an error message
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.write("[]"); // Sends an empty JSON array as a fallback
                out.flush();
            }
        }// Closing brace for the else-if block for "suggest"
        else if ("list".equals(action)) {
            // Fetch all patients
            List<Patient> patients = patientService.getAllPatients();
            request.setAttribute("patients", patients);
            request.getRequestDispatcher("/ViewPatient.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addPatient(request, response);
                break;
            case "update":
                updatePatient(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    // Method to handle adding a new patient
    private void addPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p_ID = request.getParameter("p_ID");
        String p_FirstName = request.getParameter("p_FirstName");
        String p_MiddleName = request.getParameter("p_MiddleName");
        String p_LastName = request.getParameter("p_LastName");
        String p_NIC = request.getParameter("p_NIC");
        String p_Phone_Number = request.getParameter("p_Phone_Number");
        String p_Email = request.getParameter("p_Email");

        String p_Name = p_FirstName + " " + (p_MiddleName != null ? p_MiddleName + " " : "") + p_LastName;

        if (patientService.isPatientExists(p_NIC, p_ID)) {
            request.setAttribute("error", "Patient with the given NIC or ID already exists.");
            request.getRequestDispatcher("/PatientRegister.jsp").forward(request, response);
        } else {
            Patient patient = new Patient(p_ID, p_Name, p_NIC, p_Phone_Number, p_Email);
            patientService.addOrUpdatePatient(patient);
            response.sendRedirect("PatientController?action=list");
        }
    }

    private void updatePatient(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String p_ID = request.getParameter("p_ID");
    String p_NIC = request.getParameter("p_NIC");
    String p_Phone_Number = request.getParameter("p_Phone_Number");
    String p_Email = request.getParameter("p_Email");

    // Retrieve each part of the name
    String firstName = request.getParameter("p_FirstName");
    String middleName = request.getParameter("p_MiddleName");
    String lastName = request.getParameter("p_LastName");

    // Combine parts to form full name
    StringBuilder p_NameBuilder = new StringBuilder(firstName);
    if (middleName != null && !middleName.isEmpty()) {
        p_NameBuilder.append(" ").append(middleName);
    }
    p_NameBuilder.append(" ").append(lastName);

    String p_Name = p_NameBuilder.toString().trim(); // Make sure to trim leading/trailing spaces

    // Create a Patient object with the updated information
    Patient patient = new Patient(p_ID, p_Name, p_NIC, p_Phone_Number, p_Email);

    // Call the service's updatePatient method
    patientService.updatePatient(patient);

    // Redirect to the list view after updating
    response.sendRedirect("PatientController?action=list");
}

}
