<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết giao dịch khách hàng - CineMan5</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <%
        Customer5 customer = (Customer5) request.getAttribute("customer");
        ArrayList<Invoice5> listInvoice = (ArrayList<Invoice5>) request.getAttribute("listInvoice");
        String startDate = (String) session.getAttribute("startDate");
        String endDate = (String) session.getAttribute("endDate");
    %>

    <h2>Chi tiết giao dịch khách hàng</h2>
    <div id="lblCustomerInfo">
        <p><b>Mã khách hàng:</b> <%= customer != null ? customer.getCustomerCode() : "" %></p>
        <p><b>Họ và tên:</b> <%= customer != null ? customer.getFullName() : "" %></p>
        <% if (customer != null && customer.getId() > 0) { %>
            <p><b>Số điện thoại:</b> <%= customer.getPhone() != null ? customer.getPhone() : "" %></p>
            <p><b>Email:</b> <%= customer.getEmail() != null ? customer.getEmail() : "" %></p>
        <% } %>
        <p><b>Khoảng thời gian thống kê:</b> <%= startDate %> đến <%= endDate %></p>
    </div>

    <h3>Danh sách hóa đơn và vé chi tiết</h3>
    <table id="tblInvoice" border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Mã hóa đơn</th>
            <th>Thời gian giao dịch</th>
            <th>Phương thức thanh toán</th>
            <th>Giảm giá (%)</th>
            <th>Tổng tiền (VNĐ)</th>
            <th>Thông tin khách vãng lai</th>
            <th>Nhân viên bán vé</th>
            <th>Danh sách vé thuộc hóa đơn</th>
        </tr>
        <% if (listInvoice != null && !listInvoice.isEmpty()) {
            for (Invoice5 inv : listInvoice) { %>
        <tr>
            <td>#<%= inv.getId() %></td>
            <td><%= inv.getTransactionTime() %></td>
            <td><%= inv.getPaymentMethod() %></td>
            <td><%= inv.getDiscountApplied() %>%</td>
            <td><%= String.format("%,.0f", inv.getTotalAmount()) %></td>
            <td><%= inv.getGuestName() != null ? inv.getGuestName() + " - " + inv.getGuestPhone() : "Thành viên" %></td>
            <td>
                <% if (inv.getSalesStaff() != null) { %>
                    <%= inv.getSalesStaff().getStaffCode() %> - <%= inv.getSalesStaff().getFullName() %>
                <% } else { %>
                    Đặt vé Online
                <% } %>
            </td>
            <td>
                <table id="tblTicket" border="1" cellpadding="5" cellspacing="0">
                    <tr>
                        <th>Mã vé</th>
                        <th>Phim</th>
                        <th>Phòng</th>
                        <th>Suất chiếu</th>
                        <th>Ghế</th>
                        <th>Loại ghế</th>
                        <th>Giá vé thực tế</th>
                    </tr>
                    <% if (inv.getTickets() != null) {
                        for (Ticket5 tk : inv.getTickets()) { %>
                    <tr>
                        <td>#<%= tk.getId() %></td>
                        <td><%= tk.getMovieName() %></td>
                        <td><%= tk.getRoomName() %></td>
                        <td><%= tk.getStartTime() %></td>
                        <td><%= tk.getSeatNumber() %></td>
                        <td><%= tk.getTicketType() %></td>
                        <td><%= String.format("%,.0f", tk.getActualPrice()) %> VNĐ</td>
                    </tr>
                    <%  }
                       } %>
                </table>
            </td>
        </tr>
        <%  }
           } else { %>
        <tr>
            <td colspan="8">Không có hóa đơn giao dịch nào trong khoảng thời gian này.</td>
        </tr>
        <% } %>
    </table>

    <br>
    <a href="customerStatistic.jsp"><button type="button" id="btnBackToReport">Quay lại</button></a>
    <a href="managerMain.jsp"><button type="button">Thoát</button></a>
</div>
</body>
</html>