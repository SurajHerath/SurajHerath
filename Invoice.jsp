<%-- 
    Document   : Invoice
    Created on : Oct 20, 2024, 1:47:31 PM
    Author     : suraj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Invoice Generation</h2>

    <form action="GenerateInvoiceServlet" method="post"> <!-- Servlet to handle form submission -->
        <!-- Invoice ID -->
        <label for="invoiceID">Invoice ID:</label>
        <input type="text" id="invoiceID" name="invoiceID" required><br><br>

        <!-- Appointment ID -->
        <label for="appointmentID">Appointment ID:</label>
        <input type="text" id="appointmentID" name="appointmentID" required><br><br>

        <!-- Patient ID -->
        <label for="patientID">Patient ID:</label>
        <input type="text" id="patientID" name="patientID" required><br><br>

        <!-- Tax Rate -->
        <label for="taxRate">Tax Rate (%):</label>
        <input type="number" id="taxRate" name="taxRate" step="0.01" required><br><br>

        <!-- Total Fee -->
        <label for="totalFee">Total Fee:</label>
        <input type="number" id="totalFee" name="totalFee" step="0.01" required><br><br>

        <!-- Total Amount (calculated field) -->
        <label for="totalAmount">Total Amount:</label>
        <input type="number" id="totalAmount" name="totalAmount" step="0.01" readonly><br><br>

        <!-- Issue Date -->
        <label for="issueDate">Invoice Issue Date:</label>
        <input type="date" id="issueDate" name="issueDate" required><br><br>

        <button type="button" onclick="calculateTotalAmount()">Calculate Total Amount</button> <!-- Button to calculate total amount -->
        <button type="submit">Generate Invoice</button> <!-- Submit button to generate invoice -->
    </form>

    <script>
        function calculateTotalAmount() {
            const totalFee = parseFloat(document.getElementById("totalFee").value) || 0;
            const taxRate = parseFloat(document.getElementById("taxRate").value) || 0;

            // Calculate total amount with tax
            const totalAmount = totalFee + (totalFee * (taxRate / 100));
            document.getElementById("totalAmount").value = totalAmount.toFixed(2); // Set total amount field
        }
    </script>
</body>
</html>
    </body>
</html>
