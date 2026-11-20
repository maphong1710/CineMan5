<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Báo cáo thống kê khách hàng - CineMan5</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <%
        String statisticType = (String) session.getAttribute("statisticType");
        String startDate = (String) session.getAttribute("startDate");
        String endDate = (String) session.getAttribute("endDate");
        ArrayList<CustomerStatistic5> listCS = (ArrayList<CustomerStatistic5>) session.getAttribute("listCS");
        CustomerStatistic5 guestCS = (CustomerStatistic5) session.getAttribute("guestCS");
        Float totalRevenueAll = (Float) session.getAttribute("totalRevenueAll");
        if (totalRevenueAll == null) totalRevenueAll = 0f;

        int totalTicketsAll = 0;
        if (listCS != null) {
            for (CustomerStatistic5 cs : listCS) totalTicketsAll += cs.getTicketCount();
        }
        if (guestCS != null) totalTicketsAll += guestCS.getTicketCount();
    %>

    <h2 id="lblReportTitle"><%= statisticType != null ? statisticType : "Thống kê khách hàng theo doanh thu" %></h2>
    <p id="lblDateRange">Từ ngày: <b><%= startDate %></b> đến ngày: <b><%= endDate %></b></p>

    <a href="CustomerStatisticServlet?action=sort"><button type="button" id="btnSortDesc">Đảo chiều sắp xếp doanh thu</button></a>
    <br><br>

    <table id="tblStatistic" border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>STT</th>
            <th>Mã khách hàng</th>
            <th>Tên khách hàng</th>
            <th>Số điện thoại</th>
            <th>Hạng thẻ</th>
            <th>Điểm tích lũy</th>
            <th>Số vé đã mua</th>
            <th><a href="CustomerStatisticServlet?action=sort">Tổng doanh thu (VNĐ)</a></th>
            <th>Tỷ lệ doanh thu (%)</th>
            <th>Chi tiết</th>
        </tr>
        <%
            int stt = 1;
            if (listCS != null) {
                for (CustomerStatistic5 cs : listCS) {
                    MemberCard5 mc = cs.getMemberCard();
                    String tierName = (mc != null && mc.getTier() != null) ? mc.getTier().getTierName() : "Thành viên Mới";
                    String pointsStr = (mc != null) ? String.format("%,d điểm", mc.getLoyaltyPoints()) : "0 điểm";
        %>
        <tr>
            <td><%= stt++ %></td>
            <td><%= cs.getCustomerCode() %></td>
            <td><%= cs.getCustomerName() %></td>
            <td><%= cs.getCustomerPhone() != null ? cs.getCustomerPhone() : "" %></td>
            <td><%= tierName %></td>
            <td><%= pointsStr %></td>
            <td><%= cs.getTicketCount() %></td>
            <td><%= String.format("%,.0f", cs.getTotalRevenue()) %></td>
            <td><%= String.format("%.2f%%", cs.getPercentage()) %></td>
            <td>
                <a href="TransactionDetailServlet?customerId=<%= cs.getCustomerId() %>">
                    <button type="button" id="btnViewTransaction">Xem giao dịch</button>
                </a>
            </td>
        </tr>
        <%      }
            }
            if (guestCS != null) {
        %>
        <tr>
            <td><%= stt++ %></td>
            <td><%= guestCS.getCustomerCode() %></td>
            <td><%= guestCS.getCustomerName() %></td>
            <td><%= guestCS.getCustomerPhone() %></td>
            <td>Khách vãng lai</td>
            <td>-</td>
            <td><%= guestCS.getTicketCount() %></td>
            <td><%= String.format("%,.0f", guestCS.getTotalRevenue()) %></td>
            <td><%= String.format("%.2f%%", guestCS.getPercentage()) %></td>
            <td>
                <a href="TransactionDetailServlet?customerId=0">
                    <button type="button" id="btnViewTransaction">Xem giao dịch</button>
                </a>
            </td>
        </tr>
        <% } %>
        <tr style="font-weight: bold; background-color: #f0f0f0;">
            <td colspan="6">Tổng cộng</td>
            <td><%= totalTicketsAll %></td>
            <td><%= String.format("%,.0f", totalRevenueAll) %></td>
            <td><%= totalRevenueAll > 0 ? "100.00%" : "0.00%" %></td>
            <td></td>
        </tr>
    </table>

    <br>
    <a href="selectStatisticType.jsp"><button type="button" id="btnBack">Quay lại</button></a>
    <a href="managerMain.jsp"><button type="button">Thoát</button></a>
</div>
</body>
</html>