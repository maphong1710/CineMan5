package controller;

import dao.MovieDAO;
import dao.MovieGenreDAO;
import dao.ShowtimeDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Movie5;
import model.MovieGenre5;
import model.Showtime5;

@WebServlet("/MovieDetailServlet")
public class MovieDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        int movieId = Integer.parseInt(req.getParameter("movieId"));

        MovieDAO movieDAO = new MovieDAO();
        MovieGenreDAO genreDAO = new MovieGenreDAO();
        ShowtimeDAO showtimeDAO = new ShowtimeDAO();

        Movie5 movie = movieDAO.getMovieById(movieId);
        ArrayList<MovieGenre5> lstMovieGenre = genreDAO.getGenresByMovie(movieId);
        ArrayList<Showtime5> lstShowtime = showtimeDAO.getFormatsByMovie(movieId);

        req.setAttribute("movie", movie);
        req.setAttribute("lstMovieGenre", lstMovieGenre);
        req.setAttribute("lstShowtime", lstShowtime);
        req.getRequestDispatcher("movieDetail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}