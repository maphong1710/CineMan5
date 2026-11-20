<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chọn loại thống kê - CineMan5</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h2 id="lblTitle">Chọn loại thống kê và khoảng thời gian</h2>

    <%
        String errorMsg = (String) request.getAttribute("errorMsg");
        if (errorMsg != null) {
    %>
        <p id="lblErrorMsg" style="color: red; font-weight: bold;"><%= errorMsg %></p>
    <% } %>

    <form id="frmFilter" action="CustomerStatisticServlet" method="get">
        <label>Loại thống kê:</label>
        <select name="statisticType">
            <option value="Thống kê khách hàng theo doanh thu">Thống kê khách hàng theo doanh thu</option>
        </select><br><br>

        <label>Ngày bắt đầu:</label>
        <input type="date" id="txtStartDate" name="startDate" required><br><br>

        <label>Ngày kết thúc:</label>
        <input type="date" id="txtEndDate" name="endDate" required><br><br>

        <button type="submit" id="btnViewReport">Xem báo cáo</button>
        <a href="managerMain.jsp"><button type="button">Quay lại</button></a>
    </form>
</div>
</body>
</html>