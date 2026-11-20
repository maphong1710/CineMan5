<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ Khách hàng - CineMan5</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1 id="lblLogo">Hệ thống Quản lý Rạp chiếu phim CineMan</h1>
    <p id="lblWelcome">Xin chào Quý khách!</p>

    <form id="frmSearch" action="SearchMovieServlet" method="get">
        <input type="text" id="txtKeyword" name="keyword" placeholder="Nhập tên phim...">
        <button type="submit" id="btnSearch">Tìm kiếm</button>
    </form>
    <br>
    <a href="searchMovie.jsp"><button type="button">Tìm kiếm phim</button></a>
</div>
</body>
</html>