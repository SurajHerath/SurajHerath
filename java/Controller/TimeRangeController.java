/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.TimeRange;
import Service.TimeRangeService;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.sql.Time;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *
 * @author suraj
 */
@WebServlet("/TimeRangeController")
public class TimeRangeController extends HttpServlet {

    private TimeRangeService timeRangeService;

    @Override
    public void init() {
        try {
            timeRangeService = new TimeRangeService();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize timeRangeService", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addTimeRange(request, response);
                    break;
                case "edit":
                    updateTimeRange(request, response);
                    break;
                case "delete":
                    deleteTimeRange(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp");
                    break;
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "list":
                    listTimeRanges(request, response);
                    break;
                case "search":
                    searchTimeRange(request, response);
                    break;
                case "edit":
                    editTimeRange(request, response);
                    break;
                case "delete":
                    deleteTimeRange(request, response);
                    break;
                
                default:
                    response.sendRedirect("error.jsp");
                    break;
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }
    
    
    
    
    

    
    
    

    private void addTimeRange(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
            Time startTime = Time.valueOf(request.getParameter("startTime") + ":00");
            Time endTime = Time.valueOf(request.getParameter("endTime") + ":00");
            boolean isBooked = Boolean.parseBoolean(request.getParameter("isBooked"));
            String patientId = request.getParameter("patientId");
            if (patientId.isEmpty()) {
                patientId = null;
            }
            TimeRange timeRange = new TimeRange(0, scheduleId, startTime, endTime, isBooked, patientId);

            if (timeRangeService.addTimeRange(timeRange)) {
                response.sendRedirect("TimeRangeController?action=list");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void editTimeRange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        int timeRangeId = Integer.parseInt(request.getParameter("id"));  // Use 'id' instead of 'time_range_ID'
        TimeRange timeRange = timeRangeService.getTimeRangeById(timeRangeId);

        if (timeRange != null) {
            request.setAttribute("timeRange", timeRange);
            request.getRequestDispatcher("UpdateTimeRange.jsp").forward(request, response);
        } else {
            response.sendRedirect("TimeRangeController?action=list");
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error in editTimeRange: " + e.getMessage());
        response.sendRedirect("error.jsp");
    }
}



    private void updateTimeRange(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
        int timeRangeId = Integer.parseInt(request.getParameter("time_range_ID"));
        int scheduleId = Integer.parseInt(request.getParameter("schedule_ID"));
        
        // Append ":00" if necessary to match the "HH:MM:SS" format
        String startTimeStr = request.getParameter("start_time");
        String endTimeStr = request.getParameter("end_time");

        if (startTimeStr.length() == 5) {
            startTimeStr += ":00";
        }
        if (endTimeStr.length() == 5) {
            endTimeStr += ":00";
        }
        
        Time startTime = Time.valueOf(startTimeStr);
        Time endTime = Time.valueOf(endTimeStr);

        boolean isBooked = Boolean.parseBoolean(request.getParameter("is_booked"));
        String patientId = request.getParameter("p_ID");

        TimeRange timeRange = new TimeRange(timeRangeId, scheduleId, startTime, endTime, isBooked, patientId);

        if (timeRangeService.updateTimeRange(timeRange)) {
            response.sendRedirect("TimeRangeController?action=list");
        } else {
            response.sendRedirect("error.jsp");
        }
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("error.jsp");
    }
}

    
      private void deleteTimeRange(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int timeRangeId = Integer.parseInt(request.getParameter("timeRangeID"));
            if (timeRangeService.deleteTimeRange(timeRangeId)) {
                response.sendRedirect("TimeRangeController?action=list");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=Invalid+ID+format");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
    
    


    private void listTimeRanges(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<TimeRange> timeRanges = timeRangeService.listAllTimeRanges();
            request.setAttribute("timeRanges", timeRanges);
            request.getRequestDispatcher("ViewTimeRange.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }
    }

    private void searchTimeRange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int scheduleId = Integer.parseInt(request.getParameter("schedule_ID"));
            List<TimeRange> timeRanges = timeRangeService.searchByScheduleId(scheduleId);
            request.setAttribute("timeRanges", timeRanges);
            request.getRequestDispatcher("ViewTimeRange.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }
    }
}
