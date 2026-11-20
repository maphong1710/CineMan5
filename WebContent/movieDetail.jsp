<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết phim - CineMan5</title>
    <script>
        function toggleTrailer() {
            var box = document.getElementById("trailerBox");
            box.style.display = (box.style.display === "none") ? "block" : "none";
        }
    </script>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <%
        Movie5 movie = (Movie5) request.getAttribute("movie");
        ArrayList<MovieGenre5> lstMovieGenre = (ArrayList<MovieGenre5>) request.getAttribute("lstMovieGenre");
        ArrayList<Showtime5> lstShowtime = (ArrayList<Showtime5>) request.getAttribute("lstShowtime");

        StringBuilder genreStr = new StringBuilder();
        if (lstMovieGenre != null) {
            for (int i = 0; i < lstMovieGenre.size(); i++) {
                genreStr.append(lstMovieGenre.get(i).getGenreName());
                if (i < lstMovieGenre.size() - 1) genreStr.append(", ");
            }
        }
    %>
    <% if (movie != null) { %>
        <h2 id="lblMovieName"><%= movie.getMovieName() %></h2>
        <img id="imgPosterFull" src="<%= movie.getPoster() %>" alt="Poster" width="200"><br><br>

        <p><b>Thể loại:</b> <%= genreStr.toString() %></p>
        <p id="lblDuration"><b>Thời lượng:</b> <%= movie.getDuration() %> phút</p>
        <p id="lblReleaseDate"><b>Ngày khởi chiếu:</b> <%= movie.getReleaseDate() %></p>
        <p id="lblDirector"><b>Đạo diễn:</b> <%= movie.getDirector() %></p>
        <p><b>Diễn viên:</b> <%= movie.getActors() %></p>
        <p id="lblLanguage"><b>Ngôn ngữ:</b> <%= movie.getLanguage() %> (Độ tuổi: <%= movie.getAgeRating() %>)</p>
        <p><b>Mô tả:</b></p>
        <textarea id="txtDescription" rows="4" cols="60" readonly><%= movie.getDescription() %></textarea><br><br>

        <button type="button" onclick="toggleTrailer()">Xem trailer</button>
        <div id="trailerBox" style="display:none; margin-top:10px;">
            <iframe width="560" height="315" src="<%= movie.getTrailer() %>" frameborder="0" allowfullscreen></iframe>
            <br><button type="button" onclick="toggleTrailer()">Quay lại</button>
        </div>

        <h3>Lịch chiếu & Định dạng</h3>
        <table id="tblShowtime" border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>Phòng chiếu</th>
                <th>Định dạng</th>
                <th>Giờ bắt đầu</th>
                <th>Giờ kết thúc dự kiến</th>
                <th>Giá vé</th>
                <th>Khuyến mãi</th>
                <th>Ghế trống</th>
                <th>Thao tác</th>
            </tr>
            <% if (lstShowtime != null) {
                for (Showtime5 st : lstShowtime) { %>
            <tr>
                <td><%= st.getRoomName() %></td>
                <td><%= st.getFormat() %></td>
                <td><%= st.getStartTime() %></td>
                <td><%= st.getEndTimeExpected() %></td>
                <td><%= String.format("%,.0f", st.getTicketPrice()) %> VNĐ</td>
                <td>
                    <% if (st.getPromotion() != null) { %>
                        <b><%= st.getPromotion().getProgramName() %></b> (-<%= String.format("%,.0f", st.getPromotion().getDiscountValue()) %> VNĐ)
                    <% } else { %>
                        Không áp dụng
                    <% } %>
                </td>
                <td><%= st.getAvailableSeats() %> / <%= st.getTotalSeats() %></td>
                <td>
                    <button type="button" id="btnBookTicket" onclick="alert('Chuyển sang đặt vé suất chiếu ID: <%= st.getId() %>')">Mua vé</button>
                </td>
            </tr>
            <%  }
               } %>
        </table>
        <br>
        <button type="button" id="btnBack" onclick="if (history.length > 1) { history.back(); } else { window.location.href='searchMovie.jsp'; }">Quay lại</button>
    <% } %>
</div>
</body>
</html>