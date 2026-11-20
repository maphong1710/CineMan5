package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Genre5;
import model.MovieGenre5;

public class MovieGenreDAO extends DAO {

    public MovieGenreDAO() {
        super();
    }

    public ArrayList<MovieGenre5> getGenresByMovie(int movieId) {
        ArrayList<MovieGenre5> list = new ArrayList<>();
        String sql = "SELECT mg.id, g.id AS genreId, g.genreName, g.description " +
                     "FROM tblMovieGenre5 mg JOIN tblGenre5 g ON mg.tblGenre5id = g.id " +
                     "WHERE mg.tblMovie5id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Genre5 g = new Genre5();
                g.setId(rs.getInt("genreId"));
                g.setGenreName(rs.getString("genreName"));
                g.setDescription(rs.getString("description"));

                MovieGenre5 mg = new MovieGenre5();
                mg.setId(rs.getInt("id"));
                mg.setGenreName(rs.getString("genreName"));
                mg.setDescription(rs.getString("description"));
                mg.setGenre(g);
                list.add(mg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}