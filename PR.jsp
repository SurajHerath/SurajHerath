<%-- 
    Document   : PR
    Created on : Oct 24, 2024, 11:41:47 AM
    Author     : suraj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Patient Registration</title>
</head>
<body>
    <h1>Register a New Patient</h1>

    <form method="post" action="patients">
        ID: <input type="text" name="p_ID" value="${Patient.p_ID}" /><br />
        Name: <input type="text" name="p_Name" value="${Patient.p_Name}" /><br />
        NIC: <input type="text" name="p_NIC" value="${Patient.p_NIC}" /><br />
        Phone Number: <input type="text" name="p_Phone_Number" value="${Patient.p_Phone_Number}" /><br />
        Email: <input type="email" name="p_Email" value="${Patient.p_Email}" /><br />
        <input type="submit" value="Register/Update" />
    </form>

    <h2>Search Patients</h2>
    <form method="get" action="patients">
        NIC: <input type="text" name="p_NIC" />
        OR
        ID: <input type="text" name="p_ID" />
        <input type="submit" value="Search" />
    </form>

    <h2>Patient List</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>NIC</th>
            <th>Phone Number</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="Patient" items="${Patients}">
            <tr>
                <td>${Patient.p_ID}</td>
                <td>${Patient.p_Name}</td>
                <td>${Patient.p_NIC}</td>
                <td>${Patient.p_Phone_Number}</td>
                <td>${Patient.p_Email}</td>
                <td>
                    <a href="patients?action=edit&p_ID=${Patient.p_ID}">Edit</a>
                    <a href="patients?action=delete&p_ID=${Patient.p_ID}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>
</body>
</html>