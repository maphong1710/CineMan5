package controller;

import dao.MovieDAO;
import dao.MovieGenreDAO;
import dao.ShowtimeDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Movie5;
import model.MovieGenre5;
import model.Showtime5;

@WebServlet("/SearchMovieServlet")
public class SearchMovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String keyword = req.getParameter("keyword");
        if (keyword == null || keyword.trim().isEmpty()) {
            req.setAttribute("errorMsg", "Vui lòng nhập từ khóa tìm kiếm");
            req.getRequestDispatcher("searchMovie.jsp").forward(req, resp);
            return;
        }

        keyword = keyword.trim();
        req.setAttribute("keyword", keyword);

        MovieDAO movieDAO = new MovieDAO();
        ArrayList<Movie5> listMovie = movieDAO.getMovieByName(keyword);

        if (listMovie == null || listMovie.isEmpty()) {
            req.setAttribute("errorMsg", "Không tìm thấy phim phù hợp");
            req.getRequestDispatcher("searchMovie.jsp").forward(req, resp);
            return;
        }

        MovieGenreDAO genreDAO = new MovieGenreDAO();
        ShowtimeDAO showtimeDAO = new ShowtimeDAO();
        Map<Integer, ArrayList<MovieGenre5>> mapGenres = new HashMap<>();
        Map<Integer, ArrayList<Showtime5>> mapFormats = new HashMap<>();

        for (Movie5 m : listMovie) {
            mapGenres.put(m.getId(), genreDAO.getGenresByMovie(m.getId()));
            mapFormats.put(m.getId(), showtimeDAO.getFormatsByMovie(m.getId()));
        }

        req.setAttribute("listMovie", listMovie);
        req.setAttribute("mapGenres", mapGenres);
        req.setAttribute("mapFormats", mapFormats);
        req.getRequestDispatcher("searchMovie.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}