<%-- 
    Document   : UpdateTimeRange
    Created on : Nov 6, 2024, 11:23:38 PM
    Author     : suraj
--%>


<%@page import="Model.TimeRange"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Service.TimeRangeService" %>
<%@ page import="Controller.TimeRangeController" %>
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
        <!-- Navbar -->
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <a class="navbar-brand ps-3 fw-bolder text-uppercase" href="./">Aurora Skin Care Clinic</a>
        </nav>

        <!-- Main content -->
        <main class="main">
            <div class="page-content">
                <div class="container-fluid px-4">
                    <h1 class="mt-4 text-center">Update TimeRange</h1>
                    <br>
                    <%
                        TimeRange timeRange = (TimeRange) request.getAttribute("timeRange");
                    %>
                    <!-- Form to Add TimeRange -->
                    <form id="updateTimeRangeForm" method="POST" action="TimeRangeController?action=edit">
                        <input type="hidden" name="action" value="update" />
                        <input type="hidden" name="time_range_ID" value="${timeRange.timeRangeId}" />

                        <div class="form-group mb-4">
                            <label for="scheduleId" class="form-label">Schedule ID</label>
                            <input type="text" id="scheduleId" name="schedule_ID" class="form-control" value="${timeRange.scheduleId}" required />
                        </div>

                        <div class="form-group mb-4">
                            <label for="startTime" class="form-label">Start Time</label>
                            <input type="time" id="startTime" name="start_time" class="form-control" value="${timeRange.startTime}" required />
                        </div>

                        <div class="form-group mb-4">
                            <label for="endTime" class="form-label">End Time</label>
                            <input type="time" id="endTime" name="end_time" class="form-control" value="${timeRange.endTime}" required />
                        </div>

                        

                        <div class="form-group mb-4">
                            <label for="isBooked" class="form-label">Is Booked</label>
                            <select id="isBooked" name="is_booked" class="form-control" required>
                                <option value="true" <c:if test="${timeRange.isBooked == true}"></c:if>Yes</option>
                                <option value="false" <c:if test="${timeRange.isBooked == false}"></c:if>No</option>
                            </select>
                        </div>



                        <div class="form-group mb-4">
                            <label for="patientId" class="form-label">Patient ID</label>
                            <input type="text" id="patientId" name="p_ID" class="form-control" value="${timeRange.patientId}" />
                        </div>

                        <input type="submit" value="Update TimeRange" class="btn btn-success container mt-3">

                    </form>
                </div>
            </div>
        </main>

        <!-- Footer -->
        <footer class="bg-dark text-center text-white">
            <div class="text-right p-3" style="background-color: rgba(0, 0, 0, 0.2);">
                © 2024 Copyright:
                <a class="text-white" href="#">Suraj Herath</a>
            </div>
        </footer>

        <!-- Scripts -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
