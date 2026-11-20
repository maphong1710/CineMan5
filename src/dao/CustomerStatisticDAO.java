package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.CustomerStatistic5;
import model.MemberCard5;
import model.MembershipTier5;

public class CustomerStatisticDAO extends DAO {

    public CustomerStatisticDAO() {
        super();
    }

    public ArrayList<CustomerStatistic5> getStatisticByDate(Date startDate, Date endDate) {
        ArrayList<CustomerStatistic5> list = new ArrayList<>();
        String sql = "SELECT c.id AS customerId, c.customerCode, u.fullName, u.phone, u.email, " +
                     "mc.id AS cardId, mc.cardCode, mc.loyaltyPoints, " +
                     "mt.id AS tierId, mt.tierName, mt.discountPercent, " +
                     "COUNT(t.id) AS ticketCount, COALESCE(SUM(i.totalAmount), 0) AS totalRevenue " +
                     "FROM tblCustomer5 c " +
                     "JOIN tblUser5 u ON c.tblUser5id = u.id " +
                     "JOIN tblInvoice5 i ON i.tblCustomer5id = c.id " +
                     "LEFT JOIN tblTicket5 t ON t.tblInvoice5id = i.id " +
                     "LEFT JOIN tblMemberCard5 mc ON mc.tblCustomer5id = c.id " +
                     "LEFT JOIN tblMembershipTier5 mt ON mc.tblMembershipTier5id = mt.id " +
                     "WHERE DATE(i.transactionTime) BETWEEN ? AND ? " +
                     "GROUP BY c.id, c.customerCode, u.fullName, u.phone, u.email, " +
                     "mc.id, mc.cardCode, mc.loyaltyPoints, mt.id, mt.tierName, mt.discountPercent";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CustomerStatistic5 cs = new CustomerStatistic5();
                cs.setId(rs.getInt("customerId"));
                cs.setCustomerId(rs.getInt("customerId"));
                cs.setCustomerCode(rs.getString("customerCode"));
                cs.setCustomerName(rs.getString("fullName"));
                cs.setFullName(rs.getString("fullName"));
                cs.setCustomerPhone(rs.getString("phone"));
                cs.setPhone(rs.getString("phone"));
                cs.setEmail(rs.getString("email"));
                cs.setTicketCount(rs.getInt("ticketCount"));
                cs.setTotalRevenue(rs.getFloat("totalRevenue"));

                int cardId = rs.getInt("cardId");
                if (!rs.wasNull() && cardId > 0) {
                    MemberCard5 mc = new MemberCard5();
                    mc.setId(cardId);
                    mc.setCardCode(rs.getString("cardCode"));
                    mc.setLoyaltyPoints(rs.getInt("loyaltyPoints"));

                    int tierId = rs.getInt("tierId");
                    if (!rs.wasNull() && tierId > 0) {
                        MembershipTier5 mt = new MembershipTier5();
                        mt.setId(tierId);
                        mt.setTierName(rs.getString("tierName"));
                        mt.setDiscountPercent(rs.getFloat("discountPercent"));
                        mc.setTier(mt);
                    }
                    cs.setMemberCard(mc);
                }
                list.add(cs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public CustomerStatistic5 getGuestStatistic(Date startDate, Date endDate) {
        CustomerStatistic5 guestCS = new CustomerStatistic5();
        guestCS.setCustomerId(0);
        guestCS.setCustomerCode("GUEST");
        guestCS.setCustomerName("Khách vãng lai");
        guestCS.setCustomerPhone("N/A");

        String sql = "SELECT COUNT(t.id) AS ticketCount, COALESCE(SUM(i.totalAmount), 0) AS totalRevenue " +
                     "FROM tblInvoice5 i " +
                     "LEFT JOIN tblTicket5 t ON t.tblInvoice5id = i.id " +
                     "WHERE i.tblCustomer5id IS NULL AND DATE(i.transactionTime) BETWEEN ? AND ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                guestCS.setTicketCount(rs.getInt("ticketCount"));
                guestCS.setTotalRevenue(rs.getFloat("totalRevenue"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return guestCS;
    }
}