<%-- 
    Document   : UpdateAppointment
    Created on : Nov 3, 2024, 7:06:53 PM
    Author     : suraj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="Model.Appointment"%>
<%@page import="Service.AppointmentService"%>
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
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4 text-center"><b>Update Appoinment</b></h1>
                        <br>
                        <% Appointment appointment = (Appointment) request.getAttribute("appointment");%>
                        <form action="Appointment" method="post">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="a_ID" value="<%= appointment.getA_ID()%>">

                            <label for="p_ID">Patient ID:</label><br>
                            <input type="text" id="p_ID" name="p_ID" value="<%= appointment.getP_ID()%>" required><br>

                            <label for="e_ID">Dermatologist ID:</label><br>
                            <input type="text" id="e_ID" name="e_ID" value="<%= appointment.getE_ID()%>" required><br>

                            <label for="a_Date">Appointment Date:</label><br>
                            <input type="date" id="a_Date" name="a_Date" value="<%= appointment.getA_Date()%>" required><br>

                            <label for="a_time">Appointment Time:</label><br>
                            <input type="time" id="a_time" name="a_time" value="<%= appointment.getA_time()%>" required><br>
                            
                            <label for="schedule_ID">Schedule ID:</label><br>
                            <input type="number" id="schedule_ID" name="schedule_ID" value="<%= appointment.getSchedule_ID()%>" required><br>

                            <label for="time_range_ID">Time Range ID:</label><br>
                            <input type="number" id="time_range_ID" name="time_range_ID" value="<%= appointment.getTime_range_ID()%>" required><br>

                            <label for="t_ID">Treatment ID:</label><br>
                            <input type="text" id="t_ID" name="t_ID" value="<%= appointment.getT_ID()%>" required><br>

                            <label for="reg_Fee">Registration Fee:</label><br>
                            <input type="number" id="reg_Fee" name="reg_Fee" value="<%= appointment.getReg_Fee()%>" step="0.01" required><br>

                            <label for="t_Price">Treatment Price:</label><br>
                            <input type="number" id="t_Price" name="t_Price" value="<%= appointment.getT_Price()%>" step="0.01" required><br>

                            <label for="a_Status">Appointment Status:</label><br>
                            <select id="a_Status" name="a_Status">
                                <option value="Scheduled" <%= appointment.getA_Status().equals("Scheduled") ? "selected" : ""%>>Scheduled</option>
                                <option value="Pending" <%= appointment.getA_Status().equals("Pending") ? "selected" : ""%>>Pending</option>
                                <option value="Completed" <%= appointment.getA_Status().equals("Completed") ? "selected" : ""%>>Completed</option>
                                <option value="Cancelled" <%= appointment.getA_Status().equals("Cancelled") ? "selected" : ""%>>Cancelled</option>
                                <option value="No Showed" <%= appointment.getA_Status().equals("No Showed") ? "selected" : ""%>>No Showed</option>
                                <option value="Rescheduled" <%= appointment.getA_Status().equals("Rescheduled") ? "selected" : ""%>>Rescheduled</option>
                            </select><br>


                            <input type="submit" value="Update Appointment" class="btn btn-success container mt-3">
                        </form>


                        <!-- if that time solt available then show this time slot book select differnt time slot -->

                    </div>
                </main>
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
