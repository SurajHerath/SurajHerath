<%-- 
    Document   : RegisterReceptionist
    Created on : Nov 2, 2024, 1:38:10 PM
    Author     : suraj
--%>

<%-- 
    Document   : Registerdermatologists
    Created on : Oct 20, 2024, 12:56:42 PM
    Author     : suraj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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



        <div id="layoutSidenav_content">
            <main>
    <div class="container-fluid px-4">
        <h1 class="mt-4 text-center"><b>Register Receptionist</b></h1>
        <br>
        <form class="border border-dark py-5 bg-light" action="ReceptionistController?action=add" method="POST">
            <div class="container">
                <label><b>Receptionist ID</b></label>
                <input type="text" name="r_ID" class="form-control my-1" placeholder="Enter Receptionist ID" required>

                <label><b>Name</b></label>
                <input type="text" name="r_Name" class="form-control my-1" placeholder="Enter Name" required>

                <label><b>NIC</b></label>
                <input type="text" name="r_NIC" class="form-control my-1" placeholder="Enter NIC" required>

                <label><b>Phone Number</b></label>
                <input type="text" name="r_Phone_Number" class="form-control my-1" placeholder="Enter Phone Number" required>

                <label><b>Spoken Languages</b></label>
                <input type="text" name="spoken_languages" class="form-control my-1" placeholder="Enter Spoken Languages" required>

                <br>
                <input type="submit" value="ADD Receptionist" class="btn btn-success container mt-3">
            </div>
        </form>
    </div>
</main>
    </div>
                
       
        <footer class="bg-dark text-center text-white">
            <!-- Copyright -->
            <div class="text-right p-3" style="background-color: rgba(0, 0, 0, 0.2);">
                © 2024 Copyright:
                <a class="text-white" href="">Suraj Herath</a>
            </div>
            <!-- Copyright -->
        </footer>


        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/jquery.steps.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/Form.js"></script>

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

