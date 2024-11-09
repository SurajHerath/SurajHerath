<%-- 
    Document   : AddApointment
    Created on : Nov 4, 2024, 3:30:23 PM
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
<%@ page import="com.google.gson.Gson" %>

<%
    String patientId = request.getParameter("p_ID");

    PatientService patientService = new PatientService();
    DermatologistService dermatologistService = new DermatologistService();
    EmployeeService employeeService = new EmployeeService();
    ScheduleService scheduleService = new ScheduleService();
    TimeRangeService timeRangeService = new TimeRangeService();
    TreatmentService treatmentService = new TreatmentService();

    Patient patient = patientService.getPatientByID(patientId);
    List<Dermatologist> dermatologists = dermatologistService.getAllDermatologists();
    List<Treatment> treatments = treatmentService.getAllTreatments();
    List<Employees> employees = employeeService.getAllEmployees();


%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Aurora Skin Care Clinic</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <main> 
            <div class="container">
                <h2>Book an Appointment</h2>

                <form action="AppointmentController?action=add" method="post">


                    <div class="form-group">
                        <label for="a_ID">Appointment ID:</label>
                        <input type="text" class="form-control" id="a_ID" name="a_ID" required> <!--use that for insert-->

                    </div>

                    <div class="form-group">
                        <label for="patientSearch">Search by NIC or Name:</label>
                        <input type="text" class="form-control" id="patientSearch" placeholder="Enter NIC or Name">
                        <button type="button" onclick="searchPatient()">Search</button>
                    </div>

                    <div class="form-group">
                        <label for="p_ID">Patient ID:</label>
                        <input type="text" class="form-control" id="p_ID" name="p_ID" readonly> <!--use that for insert-->
                    </div>
                    <div class="form-group">
                        <label for="p_Name">Patient Name:</label>
                        <input type="text" class="form-control" id="p_Name" name="p_Name" readonly>
                    </div>

                    <div class="form-group">
                        <label for="dermatologist">Choose Dermatologist:</label>
                        <select class="form-control" id="dermatologist" name="dermatologist" required onchange="updateDermatologistID(); clearTimeSlots()">
                            <option value="">Select Dermatologist</option>
                            <% for (Dermatologist d : dermatologists) {
                                    for (Employees e : employees) {
                                        if (d.getE_ID().equals(e.getE_ID())) {%>
                            <option value="<%= d.getE_ID()%>"><%= e.getE_Name()%></option>
                            <% }
                                    }
                                } %>
                        </select>
                        <input type="text" class="form-control" id="e_ID" name="e_ID" readonly>  <!--use that for insert-->
                    </div>


                    <div class="form-group">
                        <label for="appointmentDate">Appointment Date:</label>
                        <input type="date" class="form-control" id="a_Date" name="appointmentDate" required> <!--use that for insert-->
                    </div>

                    <div class="form-group">
                        <label><b>Day of Week</b></label>
                        <select name="dayOfWeek" class="form-control my-1" required> 
                            <option value="">Select Day</option>
                            <option value="Monday">Monday</option>
                            <option value="Tuesday">Tuesday</option>
                            <option value="Wednesday">Wednesday</option>
                            <option value="Thursday">Thursday</option>
                            <option value="Friday">Friday</option>
                            <option value="Saturday">Saturday</option>
                            <option value="Sunday">Sunday</option>
                        </select>
                        <button type="button" onclick="searchTimeSlot()">Search</button>
                    </div>


                    <div class="form-group">
                        <label for="timeSlot">Available Time Slot:</label>
                        <select class="form-control" id="a_Time" name="a_Time" required onchange="updateTimeRangeID()"> <!--use that for insert(get stat time only)-->
                            <option value="">Select Time Slot</option>
                        </select>
                        <input type="text" class="form-control" id="timeRangeID" name="timeRangeID" readonly> <!--use that for insert-->
                        <br>
                        <input type="text" class="form-control" id="scheduleID" name="scheduleID" readonly> <!--use that for insert-->
                    </div>






                    <div class="form-group">
                        <label for="treatment">Choose Treatment:</label>
                        <select class="form-control" id="treatment" name="treatment" required onchange="showTreatmentPrice()">
                            <option value="">Select Treatment</option>
                            <% for (Treatment t : treatments) {%>
                            <option value="<%= t.getT_Name()%>" data-price="<%= t.getT_Price()%>" data-id="<%= t.getT_ID()%>">
                                <%= t.getT_Name()%> - $<%= t.getT_Price()%>
                            </option>
                            <% }%>
                        </select>
                        <input type="text" class="form-control" id="TreatmentID" name="TreatmentID" readonly> <!-- This will show the treatment ID -->
                    </div>

                    <div class="form-group">
                        <label for="treatmentPrice">Treatment Price:</label>
                        <input type="text" class="form-control" id="treatmentPrice" name="treatmentPrice" readonly> <!-- This will show the treatment price -->
                    </div>



                    <div class="form-group">
                        <label for="registrationFee">Registration Fee:</label>
                        <input type="number" class="form-control" id="registrationFee" name="registrationFee" required> <!--use that for insert-->
                    </div>

                    <div class="form-group">
                        <label for="appointmentStatus">Appointment Status:</label>
                        <select class="form-control" id="appointmentStatus" name="appointmentStatus" required> <!--use that for insert-->
                            <option value="Scheduled">Scheduled</option>
                            <option value="Pending">Pending</option>
                            <option value="Confirmed">Confirmed</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Confirm Appointment</button>

                </form>
            </div>

            <script>

                function updateDermatologistID() {
                    var select = document.getElementById("dermatologist");
                    var selectedID = select.options[select.selectedIndex].value;
                    document.getElementById("e_ID").value = selectedID;
                }

                function updateTimeRangeID() {
                    const timeSlotSelect = document.getElementById("a_Time");
                    const selectedOption = timeSlotSelect.options[timeSlotSelect.selectedIndex];

                    if (selectedOption) {
                        document.getElementById("timeRangeID").value = selectedOption.value; // Set timeRangeID
                        document.getElementById("scheduleID").value = selectedOption.getAttribute("data-schedule-id"); // Set scheduleID
                    }
                }



                function searchPatient() {
                    var searchQuery = document.getElementById('patientSearch').value;
                    fetch("AppointmentController?action=searchPatient&query=" + searchQuery)
                            .then(response => response.json())
                            .then(data => {
                                document.getElementById("p_ID").value = data.patient.p_ID;
                                document.getElementById("p_Name").value = data.patient.p_Name;
                            });
                }

                function showTreatmentPrice() {
                    var treatmentSelect = document.getElementById('treatment');
                    var price = treatmentSelect.options[treatmentSelect.selectedIndex].getAttribute('data-price');
                    var treatmentID = treatmentSelect.options[treatmentSelect.selectedIndex].getAttribute('data-id');

                    document.getElementById('treatmentPrice').value = price;
                    document.getElementById('TreatmentID').value = treatmentID; // Set the Treatment ID
                }

                // Function to clear time slots when dermatologist or date changes
                function clearTimeSlots() {
                    document.getElementById("timeSlot").innerHTML = "<option value=''>Select Time Slot</option>";
                }



                function searchTimeSlot() {
                    const eID = document.getElementById("e_ID").value;
                    const dayOfWeek = document.querySelector('select[name="dayOfWeek"]').value;

                    if (eID && dayOfWeek) {
                        fetch("AppointmentController?action=getAvailableTimeSlots&eID=" + eID + "&dayOfWeek=" + dayOfWeek)
                                .then(response => {
                                    if (!response.ok) {
                                        throw new Error(`HTTP status ${response.status}`);
                                    }
                                    return response.json();
                                })
                                .then(data => {
                                    const timeSlotSelect = document.getElementById("a_Time");
                                    timeSlotSelect.innerHTML = "<option value=''>Select Time Slot</option>";
                                    if (data && data.length > 0) {
                                        data.forEach(timeSlot => {
                                            const option = document.createElement("option");
                                            option.value = timeSlot.timeRangeID; // Set timeRangeID as the option value
                                            option.text = timeSlot.startTime + " - " + timeSlot.endTime;
                                            option.setAttribute("data-schedule-id", timeSlot.scheduleID); // Set scheduleID as data attribute
                                            timeSlotSelect.appendChild(option);
                                        });
                                    } else {
                                        const option = document.createElement("option");
                                        option.value = '';
                                        option.text = 'No available time slots';
                                        timeSlotSelect.appendChild(option);
                                        alert("No available time slots found.");
                                    }
                                })
                                .catch(error => {
                                    console.error("Error fetching time slots:", error);
                                    alert("Error fetching available time slots: " + error.message);
                                });
                    } else {
                        alert("Please select both a dermatologist and day of the week.");
                    }
                }



// Add event listeners when the DOM is fully loaded
                document.addEventListener('DOMContentLoaded', function () {
                    document.getElementById('dermatologist').addEventListener('change', function () {
                        updateDermatologistID();
                        clearTimeSlots();
                    });

                    document.querySelector('select[name="dayOfWeek"]').addEventListener('change', clearTimeSlots);

                    // Add event listener for the search button
                    document.querySelector('button[onclick="searchTimeSlot()"]').addEventListener('click', searchTimeSlot);

                    document.getElementById('a_Time').addEventListener('change', updateTimeRangeID);
                });
            </script>
        </main>
    </body>
</html>
