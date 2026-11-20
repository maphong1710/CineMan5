package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Movie5;

public class MovieDAO extends DAO {

    public MovieDAO() {
        super();
    }

    public ArrayList<Movie5> getMovieByName(String keyword) {
        ArrayList<Movie5> list = new ArrayList<>();
        String sql = "SELECT * FROM tblMovie5 WHERE movieName LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Movie5 m = new Movie5();
                m.setId(rs.getInt("id"));
                m.setMovieName(rs.getString("movieName"));
                m.setDuration(rs.getInt("duration"));
                m.setDirector(rs.getString("director"));
                m.setActors(rs.getString("actors"));
                m.setDescription(rs.getString("description"));
                m.setPoster(rs.getString("poster"));
                m.setTrailer(rs.getString("trailer"));
                m.setReleaseDate(rs.getDate("releaseDate"));
                m.setLanguage(rs.getString("language"));
                m.setAgeRating(rs.getString("ageRating"));
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Movie5 getMovieById(int id) {
        Movie5 m = null;
        String sql = "SELECT * FROM tblMovie5 WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                m = new Movie5();
                m.setId(rs.getInt("id"));
                m.setMovieName(rs.getString("movieName"));
                m.setDuration(rs.getInt("duration"));
                m.setDirector(rs.getString("director"));
                m.setActors(rs.getString("actors"));
                m.setDescription(rs.getString("description"));
                m.setPoster(rs.getString("poster"));
                m.setTrailer(rs.getString("trailer"));
                m.setReleaseDate(rs.getDate("releaseDate"));
                m.setLanguage(rs.getString("language"));
                m.setAgeRating(rs.getString("ageRating"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }
}