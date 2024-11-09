<%-- 
    Document   : UpdateSchedule
    Created on : Nov 3, 2024, 10:25:31 AM
    Author     : suraj
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Schedule"%>
<%@page import="Service.ScheduleService"%>
<%@page import="Model.Employees"%>
<%@page import="java.util.List"%>
<%@ page import="java.sql.*" %>
<%@page import="Utils.AuroraSkinCareDB"%>
<%@ page import="java.util.List, java.util.Map, java.util.Set, java.util.HashMap" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Aurora Skin Care Clinic</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>

    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3 fw-bolder text-uppercase" href="./">Aurora Skin Care Clinic</a>        
            <!-- Navbar Search-->
            
            
                
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                    <button class="btn btn-info" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        
                        <li><a class="dropdown-item" href="logout.php">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
        
  <div id="layoutSidenav_content">

<%
    Schedule schedule = (Schedule) request.getAttribute("schedule");
    if (schedule == null) { 
%>
        <div class="alert alert-danger text-center">
            <strong>Error:</strong> Schedule not found. Please go back and try again.
        </div>
<%
    } else {
%>
        <main> 
            <div class="container-fluid px-4">
                <h1 class="mt-4 text-center"><b>Update Schedule</b></h1>
                <br>
                
                <form class="border border-dark py-5 bg-light" action="ScheduleController?action=update" method="POST">
                    <div class="container">
                        <!-- Hidden field to store scheduleID -->
                        <input type="hidden" name="scheduleID" value="<%= schedule.getScheduleID() %>">

                        <label><b>Dermatologist ID</b></label>
                        <input type="text" name="dermatologistID" class="form-control my-1" placeholder="Enter Dermatologist ID" value="<%= schedule.getEID() %>" required>

                        <label><b>Day of Week</b></label>
<select name="dayOfWeek" class="form-control my-1" required>
    <option value="">Select Day</option>
    <option value="Monday" <%= schedule.getDayOfWeek().equals("Monday") ? "selected" : "" %>>Monday</option>
    <option value="Tuesday" <%= schedule.getDayOfWeek().equals("Tuesday") ? "selected" : "" %>>Tuesday</option>
    <option value="Wednesday" <%= schedule.getDayOfWeek().equals("Wednesday") ? "selected" : "" %>>Wednesday</option>
    <option value="Thursday" <%= schedule.getDayOfWeek().equals("Thursday") ? "selected" : "" %>>Thursday</option>
    <option value="Friday" <%= schedule.getDayOfWeek().equals("Friday") ? "selected" : "" %>>Friday</option>
    <option value="Saturday" <%= schedule.getDayOfWeek().equals("Saturday") ? "selected" : "" %>>Saturday</option>
    <option value="Sunday" <%= schedule.getDayOfWeek().equals("Sunday") ? "selected" : "" %>>Sunday</option>
</select>

                        <label><b>Start Time</b></label>
                        <input type="time" name="startTime" class="form-control my-1" value="<%= schedule.getStartTime() %>" required>

                        <label><b>End Time</b></label>
                        <input type="time" name="endTime" class="form-control my-1" value="<%= schedule.getEndTime() %>" required>

                        <br>
                        <input type="submit" value="Update Schedule" class="btn btn-success container mt-3">
                    </div>
                </form>
            </div>
        </main>
<%
    }
%>

</div>

        </div>
    </div>
        <br> 
        <br> 
        <br> 
    <footer class="bg-dark text-center text-white">
<!-- Copyright -->
      <div class="text-right p-3" style="background-color: rgba(0, 0, 0, 0.2);">
          © 2024 Copyright:
          <a class="text-white" href="">Suraj Herath</a>
      </div>
      <!-- Copyright -->
    </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>

