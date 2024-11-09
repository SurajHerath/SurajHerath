<%-- 
    Document   : PatientReg
    Created on : Oct 21, 2024, 3:41:45 PM
    Author     : suraj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ page import="java.util.List" %>
<%@ page import="Model.Dermatologist" %>
<%@ page import="Model.Patient" %>
<%@ page import="Model.Schedule" %>
<%@ page import="Model.TimeRange" %>
<%@ page import="Model.Treatment" %>
<%@ page import="Model.Employees" %>
<%@ page import="Service.EmployeeService" %>
<%@ page import="Service.PatientService" %>
<%@ page import="Service.DermatologistService" %>
<%@ page import="Service.ScheduleService" %>
<%@ page import="Service.TimeRangeService" %>
<%@ page import="Service.TreatmentService" %>


<%
    // Assume patient ID is passed as a request parameter or session attribute
    String patientId = request.getParameter("p_ID");

    PatientService patientService = new PatientService();
    DermatologistService dermatologistService = new DermatologistService();
    EmployeeService employeeService = new EmployeeService();
    ScheduleService scheduleService = new ScheduleService();
    TimeRangeService timeRangeService = new TimeRangeService();
    TreatmentService treatmentService = new TreatmentService();

    // Fetch patient details
    Patient patient = patientService.getPatientByID(patientId);

    // Fetch list of dermatologists
    List<Dermatologist> dermatologists = dermatologistService.getAllDermatologists();

    // Fetch list of treatments
    List<Treatment> treatments = treatmentService.getAllTreatments();

    List<Employees> employees = employeeService.getAllEmployees();
%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <title>Patient Register - Aurora Skin Care Clinic</title>
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
        <link rel="stylesheet" href="css/styles.css"/>
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



        <div class="page-content">
		<div class="wizard-heading">Form Booking </div>
		<div class="wizard-v6-content">
			<div class="wizard-form">

                    <form class="form-register" id="form-register" action="PatientController" method="post">
                        <div id="form-total">

                            <!-- SECTION 1 -->
                            <h2>
                                <p class="step-icon"><span>1</span></p>
                                <span class="step-text">Patient Info</span>
                            </h2>

                            <section>

                                <div class="inner">
                                    <div class="form-heading">
                                        <h3>Patient Info</h3>
                                        <span>1/3</span>
                                    </div>

                                    <div class="form-row form-row-date">
                                        <div class="form-holder form-holder-2">
                                            <label for="NIC" class="special-label">NIC Number:</label>

                                            <!-- Single input field for both search and register actions -->
                                            <input type="text" id="searchOrNICInput" name="NIC" class="form-control my-1" placeholder="Enter or Search by NIC, Name, or ID" autocomplete="off" required>

                                            <!-- Hidden input to specify the action for the controller -->
                                            <input type="hidden" name="action" value="searchOrInsert">

                                            <!-- Button for triggering the search or registration -->
                                            <button type="submit" class="btn btn-primary">Search / Register</button>
                                        </div>
                                    </div>


                                    <div class="form-row">
                                        <div class="form-holder">
                                            <label class="form-row-inner">
                                                <input type="text" class="form-control" id="first_name" name="first_name" required>
                                                <span class="label">First Name</span>
                                            </label>
                                        </div>

                                        <div class="form-holder">
                                            <label class="form-row-inner">
                                                <input type="text" class="form-control" id="first_name" name="first_name" required>
                                                <span class="label">Middle Name</span>
                                            </label>
                                        </div>


                                        <div class="form-holder">
                                            <label class="form-row-inner">
                                                <input type="text" class="form-control" id="last_name" name="last_name" required>
                                                <span class="label">Last Name</span>
                                            </label>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-holder">
                                            <label class="form-row-inner">
                                                <input type="text" class="form-control" id="phone" name="phone" required>
                                                <span class="label">Phone Number</span>
                                            </label>
                                        </div>

                                        <div class="form-holder">
                                            <label class="form-row-inner">
                                                <input type="text" name="your_email_1" id="your_email_1" class="form-control"  required>
                                                <span class="label">E-Mail</span>
                                            </label>
                                        </div>
                                    </div>

                                    <div class="form-row form-row-date">
                                        <div class="form-holder form-holder-2">
                                            <label for="date" class="special-label">Date:</label>
                                            <input type="date" name="adate" class="form-control my-1" placeholder="Enter Appoinment Date"required>
                                        </div>
                                    </div>
                                </div>

                                <!-- Submit Button -->
                                <div class="form-row">
                                    <div class="form-holder">
                                        <button type="submit" class="btn btn-primary">Register Patient</button> <!--when click on that button need tow msg patient register succesfully-->
                                    </div>
                                </div>
                                <div class="card-footer">
                                    <a href="PatientController?action=list" class="btn btn-primary">Go to List</a>
                                    <input type="hidden" name="action" value="add">
                                    <input type="submit" value="Add Patient" class="btn btn-danger float-end">
                                </div>

                            </section>

                            <!-- SECTION 2: Booking Appointment -->
                            <h2>
                                <p class="step-icon"><span>2</span></p>
                                <span class="step-text">Booking Appointment</span>
                            </h2>
                            <section>
                                <div class="inner">
                                    <div class="form-heading">
                                        <h3>Booking Appointment</h3>
                                        <span>2/3</span>
                                    </div>
                                    <!-- Book Appointment Form Elements -->
                                    <div class="form-group">
                                        <label for="p_ID">Patient ID:</label>
                                        <input type="text" class="form-control" id="p_ID" name="p_ID" value="<%= patient.getP_ID()%>" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="p_Name">Patient Name:</label>
                                        <input type="text" class="form-control" id="p_Name" name="p_Name" value="<%= patient.getP_Name()%>" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="dermatologist">Choose Dermatologist:</label>
                                        <select class="form-control" id="dermatologist" name="dermatologist" required onchange="fetchSchedule(this.value)">
                                            <option value="">Select Dermatologist</option>
                                            <% for (Dermatologist d : dermatologists) {
                                            for (Employees e : employees) {
                                                if (d.getE_ID().equals(e.getE_ID())) {%>
                                            <option value="<%= d.getE_ID()%>"><%= e.getE_Name()%></option>
                                            <% }
                                            }
                                        } %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="appointmentDate">Appointment Date:</label>
                                        <input type="date" class="form-control" id="appointmentDate" name="appointmentDate" required onchange="fetchTimeSlots()">
                                    </div>
                                    <div class="form-group">
                                        <label for="timeSlot">Available Time Slot:</label>
                                        <select class="form-control" id="timeSlot" name="timeSlot" required></select>
                                    </div>
                                    <div class="form-group">
                                        <label for="treatment">Choose Treatment:</label>
                                        <select class="form-control" id="treatment" name="treatment" required onchange="showTreatmentPrice()">
                                            <option value="">Select Treatment</option>
                                            <% for (Treatment t : treatments) {%>
                                            <option value="<%= t.getT_Name()%>" data-price="<%= t.getT_Price()%>"><%= t.getT_Name()%> - $<%= t.getT_Price()%></option>
                                            <% }%>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="treatmentPrice">Treatment Price:</label>
                                        <input type="text" class="form-control" id="treatmentPrice" name="treatmentPrice" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="registrationFee">Registration Fee:</label>
                                        <input type="number" class="form-control" id="registrationFee" name="registrationFee" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="appointmentStatus">Appointment Status:</label>
                                        <select class="form-control" id="appointmentStatus" name="appointmentStatus" required>
                                            <option value="Scheduled">Scheduled</option>
                                            <option value="Pending">Pending</option>
                                            <option value="Confirmed">Confirmed</option>
                                        </select>
                                    </div>
                                </div>
                            </section>

                            <!-- SECTION 3: Confirm Appointment Details -->
                            <h2>
                                <p class="step-icon"><span>3</span></p>
                                <span class="step-text">Confirm</span>
                            </h2>
                            <section>
                                <div class="inner">
                                    <div class="form-heading">
                                        <h3>Confirm Appointment Details</h3>
                                        <span>3/3</span>
                                    </div>
                                    <div class="table-responsive">
                                        <table class="table">
                                            <tbody>
                                                <tr class="space-row">
                                                    <th>Patient Name:</th>
                                                    <td id="fullname-val"><%= patient.getP_Name()%></td>
                                                </tr>
                                                <tr class="space-row">
                                                    <th>Dermatologist:</th>
                                                    <td id="dermatologist-val"></td>
                                                </tr>
                                                <tr class="space-row">
                                                    <th>Appointment Date:</th>
                                                    <td id="day-val"></td>
                                                </tr>
                                                <tr class="space-row">
                                                    <th>Time Slot:</th>
                                                    <td id="time-val"></td>
                                                </tr>
                                                <tr class="space-row">
                                                    <th>Treatment:</th>
                                                    <td id="treatment-val"></td>
                                                </tr>
                                                <tr class="space-row">
                                                    <th>Treatment Price:</th>
                                                    <td id="treatmentPrice-val"></td>
                                                </tr>
                                                <tr class="space-row">
                                                    <th>Registration Fee:</th>
                                                    <td id="registrationFee-val"></td>
                                                </tr>
                                                <tr class="space-row">
                                                    <th>Appointment Status:</th>
                                                    <td id="appointmentStatus-val"></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </section>
                        </div>
                    </form>


                </div>
            </div>
        </div>


        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/jquery.steps.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/Form.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min(2).js"></script>

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
        <script src="js/formmain.js"></script>
    </body>
</html>
