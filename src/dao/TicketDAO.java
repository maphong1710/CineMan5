package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Ticket5;

public class TicketDAO extends DAO {

    public TicketDAO() {
        super();
    }

    public ArrayList<Ticket5> getTicketsByInvoice(int invoiceId) {
        ArrayList<Ticket5> list = new ArrayList<>();
        String sql = "SELECT t.id, t.actualPrice, s.seatCode, s.seatType, m.movieName, r.roomName, st.startTime " +
                     "FROM tblTicket5 t " +
                     "JOIN tblSeat5 s ON t.tblSeat5id = s.id " +
                     "JOIN tblShowtime5 st ON t.tblShowtime5id = st.id " +
                     "JOIN tblMovie5 m ON st.tblMovie5id = m.id " +
                     "JOIN tblRoom5 r ON st.tblRoom5id = r.id " +
                     "WHERE t.tblInvoice5id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, invoiceId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ticket5 tk = new Ticket5();
                tk.setId(rs.getInt("id"));
                tk.setActualPrice(rs.getFloat("actualPrice"));
                tk.setSeatNumber(rs.getString("seatCode"));
                tk.setTicketType(rs.getString("seatType"));
                tk.setStatus("Đã xuất vé");
                tk.setMovieName(rs.getString("movieName"));
                tk.setRoomName(rs.getString("roomName"));
                tk.setStartTime(rs.getTimestamp("startTime"));
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}