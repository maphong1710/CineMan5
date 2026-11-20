<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tìm kiếm thông tin phim - CineMan5</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h2>Tìm kiếm thông tin phim</h2>
    <form id="frmSearch" action="SearchMovieServlet" method="get">
        <input type="text" id="txtKeyword" name="keyword"
               value="<%= request.getAttribute("keyword") != null ? request.getAttribute("keyword") : "" %>"
               placeholder="Nhập tên phim...">
        <button type="submit" id="btnSearch">Tìm kiếm</button>
    </form>
    <br>
    <a href="customerMain.jsp"><button type="button">Quay lại</button></a>
    <hr>

    <%
        String errorMsg = (String) request.getAttribute("errorMsg");
        if (errorMsg != null) {
    %>
        <p id="lblMessage" style="color: red; font-weight: bold;"><%= errorMsg %></p>
    <%
        }
        ArrayList<Movie5> listMovie = (ArrayList<Movie5>) request.getAttribute("listMovie");
        Map<Integer, ArrayList<MovieGenre5>> mapGenres = (Map<Integer, ArrayList<MovieGenre5>>) request.getAttribute("mapGenres");
        Map<Integer, ArrayList<Showtime5>> mapFormats = (Map<Integer, ArrayList<Showtime5>>) request.getAttribute("mapFormats");

        if (listMovie != null && !listMovie.isEmpty()) {
    %>
        <h3 id="lblTitleResult">Danh sách phim kết quả (<%= listMovie.size() %> phim):</h3>
        <table id="tblMovieResult" border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>Poster</th>
                <th>Tên phim</th>
                <th>Thời lượng</th>
                <th>Thể loại</th>
                <th>Định dạng chiếu</th>
                <th>Thao tác</th>
            </tr>
            <%
                for (Movie5 m : listMovie) {
                    ArrayList<MovieGenre5> genres = mapGenres.get(m.getId());
                    ArrayList<Showtime5> formats = mapFormats.get(m.getId());

                    StringBuilder genreStr = new StringBuilder();
                    if (genres != null) {
                        for (int i = 0; i < genres.size(); i++) {
                            genreStr.append(genres.get(i).getGenreName());
                            if (i < genres.size() - 1) genreStr.append(", ");
                        }
                    }

                    Set<String> uniqueFormats = new LinkedHashSet<>();
                    if (formats != null) {
                        for (Showtime5 st : formats) {
                            uniqueFormats.add(st.getFormat());
                        }
                    }
            %>
            <tr>
                <td><img id="imgPoster" src="<%= m.getPoster() %>" alt="Poster" width="80"></td>
                <td><%= m.getMovieName() %></td>
                <td><%= m.getDuration() %> phút</td>
                <td><%= genreStr.toString() %></td>
                <td><%= String.join(", ", uniqueFormats) %></td>
                <td>
                    <form action="MovieDetailServlet" method="get">
                        <input type="hidden" name="movieId" value="<%= m.getId() %>">
                        <button type="submit" id="btnViewDetail">Xem chi tiết</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
    <% } %>
</div>
</body>
</html>