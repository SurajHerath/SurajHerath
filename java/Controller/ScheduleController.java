/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.Schedule;
import Service.ScheduleService;
import java.io.IOException;
import java.sql.Time;
import java.util.List;

/**
 *
 * @author suraj
 */
@WebServlet("/ScheduleController")
public class ScheduleController extends HttpServlet {

    private ScheduleService scheduleService = new ScheduleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "list":
                    listSchedules(request, response);
                    break;
                case "add":
                    showAddForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                      deleteSchedule(request, response);
                      break;
                case "search":
                    searchSchedules(request, response);
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
                addSchedule(request, response);
            } else if ("update".equals(action)) {
                updateSchedule(request, response);
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // Show the add form
    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addSchedule.jsp").forward(request, response);
    }

    // Show edit form for a specific schedule
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int scheduleID = Integer.parseInt(request.getParameter("scheduleID"));
        Schedule schedule = scheduleService.getScheduleByID(scheduleID);  // Ensure this fetches the correct Schedule object

        if (schedule != null) {
            request.setAttribute("schedule", schedule);  // Add schedule to request scope
            request.getRequestDispatcher("UpdateSchedule.jsp").forward(request, response);
        } else {
            response.sendRedirect("error.jsp");  // Redirect to an error page if schedule is null
        }
    }

    // List all schedules for a specific dermatologist
    private void listSchedules(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Schedule> schedules;
        String dermatologistID = request.getParameter("dermatologistID");

        if (dermatologistID != null && !dermatologistID.isEmpty()) {
            schedules = scheduleService.getSchedulesByDermatologist(dermatologistID);
        } else {
            schedules = scheduleService.getAllSchedules(); // Use this if you want all schedules
        }

        request.setAttribute("schedules", schedules);
        request.getRequestDispatcher("ViewSchedule.jsp").forward(request, response);

    }

    // Add a new schedule
    private void addSchedule(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String dermatologistID = request.getParameter("dermatologistID");
        String dayOfWeek = request.getParameter("dayOfWeek");

        // Append ":00" to time values to match the HH:MM:SS format
        String startTimeInput = request.getParameter("startTime") + ":00";
        String endTimeInput = request.getParameter("endTime") + ":00";

        Time startTime = Time.valueOf(startTimeInput);
        Time endTime = Time.valueOf(endTimeInput);

        Schedule schedule = new Schedule(0, dermatologistID, dayOfWeek, startTime.toString(), endTime.toString());
        boolean scheduleAdded = scheduleService.addSchedule(schedule);

        if (scheduleAdded) {
            response.sendRedirect("ScheduleController?action=list&dermatologistID=" + dermatologistID);
        } else {
            response.sendRedirect("error.jsp");
        }
    }

// Method to delete a schedule
private void deleteSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String scheduleIdParam = request.getParameter("schedule_id"); // Make sure this matches the parameter name from JSP

    // Check if the parameter is null or empty
    if (scheduleIdParam == null || scheduleIdParam.isEmpty()) {
        System.out.println("Schedule ID is missing or empty.");
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Schedule ID");
        return;
    }

    try {
        // Parse the schedule ID if present
        int scheduleId = Integer.parseInt(scheduleIdParam);

        // Call the service to delete the schedule
        ScheduleService scheduleService = new ScheduleService();
        boolean isDeleted = scheduleService.deleteSchedule(scheduleId);

        if (isDeleted) {
            // Redirect to viewSchedules.jsp after deleting schedule
            response.sendRedirect("ScheduleController?action=list&dermatologistID="); // This ensures that the page is reloaded after deletion
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete the schedule.");
        }

    } catch (NumberFormatException e) {
        System.out.println("Invalid Schedule ID format: " + scheduleIdParam);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Schedule ID format");
    }
}



    public void updateSchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String scheduleIDStr = request.getParameter("scheduleID");
        String dermatologistID = request.getParameter("dermatologistID");
        String dayOfWeek = request.getParameter("dayOfWeek");
        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");

        // Debug print statements
        System.out.println("Schedule ID: " + scheduleIDStr);
        System.out.println("Dermatologist ID: " + dermatologistID);
        System.out.println("Day of Week: " + dayOfWeek);
        System.out.println("Start Time: " + startTimeStr);
        System.out.println("End Time: " + endTimeStr);

        try {
            // Parse scheduleID
            int scheduleID = Integer.parseInt(scheduleIDStr);

            // Create a Schedule object
            Schedule schedule = new Schedule(scheduleID, dermatologistID, dayOfWeek, startTimeStr, endTimeStr);

            // Call the service to update the schedule
            boolean isUpdated = scheduleService.updateSchedule(schedule);
            if (isUpdated) {
                response.sendRedirect("ScheduleController?action=list&dermatologistID=" + dermatologistID); // Redirect after successful update
            } else {
                request.setAttribute("error", "Failed to update schedule. Please check your inputs.");
                request.getRequestDispatcher("/UpdateSchedule.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Schedule ID format. Please try again.");
            request.getRequestDispatcher("/UpdateSchedule.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("/UpdateSchedule.jsp").forward(request, response);
        }
    }

    // Delete a schedule
    protected void searchSchedules(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");

        // Use the service to perform the search based on the query
        List<Schedule> searchResults = scheduleService.searchSchedules(searchQuery);

        // Set the search results in the request attribute
        request.setAttribute("schedules", searchResults);

        // Forward the request to the view page to display results
        request.getRequestDispatcher("ViewSchedule.jsp").forward(request, response);
    }
}
