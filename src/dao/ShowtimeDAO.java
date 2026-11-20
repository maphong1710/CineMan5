package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Promotion5;
import model.Showtime5;

public class ShowtimeDAO extends DAO {

    public ShowtimeDAO() {
        super();
    }

    public ArrayList<Showtime5> getFormatsByMovie(int movieId) {
        ArrayList<Showtime5> list = new ArrayList<>();
        String sql = "SELECT s.*, r.roomName, r.capacity, " +
                     "p.id AS promoId, p.programName, p.discountValue, p.`condition` AS promoCondition, " +
                     "(r.capacity - (SELECT COUNT(*) FROM tblTicket5 t WHERE t.tblShowtime5id = s.id)) AS availableSeats " +
                     "FROM tblShowtime5 s " +
                     "JOIN tblRoom5 r ON s.tblRoom5id = r.id " +
                     "LEFT JOIN tblPromotion5 p ON s.tblPromotion5id = p.id " +
                     "WHERE s.tblMovie5id = ? ORDER BY s.startTime ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Showtime5 st = new Showtime5();
                st.setId(rs.getInt("id"));
                st.setStartTime(rs.getTimestamp("startTime"));
                st.setEndTimeExpected(rs.getTimestamp("endTimeExpected"));
                st.setTicketPrice(rs.getFloat("ticketPrice"));
                st.setFormat(rs.getString("format"));
                st.setRoomName(rs.getString("roomName"));
                st.setTotalSeats(rs.getInt("capacity"));
                st.setAvailableSeats(rs.getInt("availableSeats"));

                int promoId = rs.getInt("promoId");
                if (!rs.wasNull() && promoId > 0) {
                    Promotion5 promo = new Promotion5();
                    promo.setId(promoId);
                    promo.setProgramName(rs.getString("programName"));
                    promo.setDiscountValue(rs.getFloat("discountValue"));
                    promo.setCondition(rs.getString("promoCondition"));
                    st.setPromotion(promo);
                }
                list.add(st);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}