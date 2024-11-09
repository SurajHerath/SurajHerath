<%-- 
    Document   : DeleteTreatment
    Created on : Oct 20, 2024, 1:42:13 PM
    Author     : suraj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@page import="Model.Treatment"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.List, java.util.Map, java.util.Set, java.util.HashMap" %>


<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <title>Index - Squadfree Bootstrap Template</title>
        <meta name="description" content="">
        <meta name="keywords" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com" rel="preconnect">
        <link href="https://fonts.gstatic.com" rel="preconnect" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Raleway:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/opensans-font.css">
        <link rel="stylesheet" type="text/css" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">
        <!-- Vendor CSS Files -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="vendor/aos/aos.css" rel="stylesheet">
        <link href="vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

        <!-- Main CSS File -->
        <link href="css/nav_and_footer.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css"/>
    </head>

    <body>

        <header id="header" class="header d-flex align-items-center fixed-top">
            <div class="container-fluid container-xl position-relative d-flex align-items-center justify-content-between">

                <a href="index.html" class="logo d-flex align-items-center">
                    <!-- Uncomment the line below if you also wish to use an image logo -->
                    <!-- <img src="assets/img/logo.png" alt=""> -->
                    <h1 class="sitename">Aurora Skin Care Clinic</h1>
                </a>

                <nav id="navmenu" class="navmenu">
                    <ul>
                        <li><a href="#hero" class="active">Home</a></li>
                        <li><a href="#about">About</a></li>
                        <li><a href="#services">Services</a></li>
                        <li class="dropdown"><a href="#"><span>Dermatologists</span> <i class="bi bi-chevron-down toggle-dropdown"></i></a>
                            <ul>
                                <li><a href="#">Register Dermatologistst</a></li>
                                <li><a href="#">View Dermatologistst</a></li>
                            </ul>
                        </li>
                        <li class="dropdown"><a href="#"><span>Patient</span> <i class="bi bi-chevron-down toggle-dropdown"></i></a>
                            <ul>
                                <li><a href="#">Register Patient</a></li>
                                <li><a href="#">View Patient</a></li>
                            </ul>
                        </li>
                        <li class="dropdown"><a href="#"><span>Appointment</span> <i class="bi bi-chevron-down toggle-dropdown"></i></a>
                            <ul>
                                <li><a href="#">Make Appointment</a></li>
                                <li><a href="#">View Appointments</a></li>
                            </ul>
                        </li> 


                    </ul>
                    <i class="mobile-nav-toggle d-xl-none bi bi-list"></i>
                </nav>
            </div>
        </header>

        <main class="main">

    <!-- Page Title -->
    <div class="page-title accent-background" style="background-image:url('img/doctor1.jpg');">
        <div class="container position-relative">
            <h1>View Treatment</h1>
            <a href="#View" class="btn-scroll" title="Scroll Down"><i class="bi bi-chevron-down"></i></a>
        </div>
    </div><!-- End Page Title -->

    <div class="page-content">
        <div class="container-fluid px-4">
            <h1 class="mt-4 text-center">Delete And View Treatment</h1>
            <br>

            <form id="searchForm" method="GET" action="TreatmentController">
                <input type="hidden" name="action" value="search" />
                <div class="input-group mb-4">
                    <input type="text" id="searchInput" name="query" class="form-control" placeholder="Search by Treatment Name or ID" autocomplete="off" required>
                    <button type="submit" class="btn btn-primary">Search</button>
                </div>
                <div id="suggestions" class="suggestions-box"></div>
            </form>

            <table id="tblTreatment" class="table table-striped">
                <thead>
                    <tr>
                        <th>Treatment ID</th>
                        <th>Treatment Name</th>
                        <th>Treatment Price</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        // Fetch treatments to display
                        List<Treatment> searchResults = (List<Treatment>) request.getAttribute("searchResults");
                        List<Treatment> treatmentsToDisplay = (searchResults != null && !searchResults.isEmpty()) ? searchResults : (List<Treatment>) request.getAttribute("treatments");

                        if (treatmentsToDisplay != null && !treatmentsToDisplay.isEmpty()) {
                            for (Treatment treatment : treatmentsToDisplay) {
                    %>
                    <tr>
                        <td><%= treatment.getT_ID() %></td>
                        <td><%= treatment.getT_Name() %></td>
                        <td><%= treatment.getT_Price() %></td>
                        <td>
                            <a href="TreatmentController?action=edit&t_ID=<%= treatment.getT_ID() %>" class="edit-treatment btn btn-warning">Edit</a>
                            <a href="TreatmentController?action=delete&t_ID=<%= treatment.getT_ID() %>" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this treatment?');">Delete</a>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="4">No treatments found.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</main>
    </div>
</div>
</div>

<footer class="bg-dark text-center text-white">
    <!-- Copyright -->
    <div class="text-right p-3" style="background-color: rgba(0, 0, 0, 0.2);">
        © 2024 Copyright:
        <a class="text-white" href="">Suraj Herath</a>
    </div>
    <!-- Copyright -->
</footer>   

<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="vendor/php-email-form/validate.js"></script>
<script src="vendor/aos/aos.js"></script>
<script src="vendor/purecounter/purecounter_vanilla.js"></script>
<script src="vendor/glightbox/js/glightbox.min.js"></script>
<script src="vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
<script src="vendor/isotope-layout/isotope.pkgd.min.js"></script>
<script src="vendor/swiper/swiper-bundle.min.js"></script>


<!-- Main JS File -->
<script src="js/main.js"></script>
</body>
</html>
