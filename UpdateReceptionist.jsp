<%-- 
    Document   : UpdateReceptionist
    Created on : Nov 2, 2024, 2:04:43 PM
    Author     : suraj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Arogya Health Care Hospital</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3 fw-bolder text-uppercase" href="./">Arogya Health Care Hospital</a>        
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
        <h1 class="mt-4 text-center"><b>Update Receptionist</b></h1>
        <br>
        <form class="border border-dark py-5 bg-light" action="ReceptionistController?action=update" method="POST">
            <div class="container">
                <label><b>Receptionist ID</b></label>
                <input type="text" name="e_ID" class="form-control my-1" placeholder="Enter Receptionist ID" value="${receptionist.e_ID}" required>

                <label><b>Name</b></label>
                <input type="text" name="e_Name" class="form-control my-1" placeholder="Enter Name" value="${receptionist.e_Name}" required>

                <label><b>NIC</b></label>
                <input type="text" name="e_NIC" class="form-control my-1" placeholder="Enter NIC" value="${receptionist.e_NIC}" required>

                <label><b>Phone Number</b></label>
                <input type="text" name="e_Phone_Number" class="form-control my-1" placeholder="Enter Phone Number" value="${receptionist.e_Phone_Number}" required>

                <label><b>Spoken Languages</b></label>
                <input type="text" name="spokenLanguages" class="form-control my-1" placeholder="Enter Spoken Languages" value="${receptionist.spokenLanguages}" required>

                <br>
                <input type="submit" value="Update Receptionist" class="btn btn-success container mt-3">
            </div>
        </form>
    </div>
</main>
    </div>
</main>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
