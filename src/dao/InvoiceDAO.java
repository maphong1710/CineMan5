package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Customer5;
import model.Invoice5;
import model.SalesStaff5;

public class InvoiceDAO extends DAO {

    public InvoiceDAO() {
        super();
    }

    public Customer5 getCustomerById(int customerId) {
        Customer5 c = null;
        String sql = "SELECT c.id, c.customerCode, u.fullName, u.phone, u.email, u.username " +
                     "FROM tblCustomer5 c JOIN tblUser5 u ON c.tblUser5id = u.id WHERE c.id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Customer5();
                c.setId(rs.getInt("id"));
                c.setCustomerCode(rs.getString("customerCode"));
                c.setFullName(rs.getString("fullName"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setUsername(rs.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public ArrayList<Invoice5> getInvoicesByCustomer(int customerId, Date startDate, Date endDate) {
        ArrayList<Invoice5> list = new ArrayList<>();
        String sql = "SELECT i.*, ss.id AS salesStaffId, st.staffCode, st.position, u.fullName AS staffName " +
                     "FROM tblInvoice5 i " +
                     "LEFT JOIN tblSalesStaff5 ss ON i.tblSalesStaff5id = ss.id " +
                     "LEFT JOIN tblStaff5 st ON ss.tblStaff5id = st.id " +
                     "LEFT JOIN tblUser5 u ON st.tblUser5id = u.id " +
                     "WHERE i.tblCustomer5id = ? AND DATE(i.transactionTime) BETWEEN ? AND ? " +
                     "ORDER BY i.transactionTime DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setDate(2, startDate);
            ps.setDate(3, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Invoice5 inv = new Invoice5();
                inv.setId(rs.getInt("id"));
                inv.setTransactionTime(rs.getTimestamp("transactionTime"));
                inv.setPaymentMethod(rs.getString("paymentMethod"));
                inv.setTotalAmount(rs.getFloat("totalAmount"));
                inv.setDiscountApplied(rs.getFloat("discountApplied"));
                inv.setStatus("Đã thanh toán");

                int salesStaffId = rs.getInt("salesStaffId");
                if (!rs.wasNull() && salesStaffId > 0) {
                    SalesStaff5 staff = new SalesStaff5();
                    staff.setId(salesStaffId);
                    staff.setStaffCode(rs.getString("staffCode"));
                    staff.setPosition(rs.getString("position"));
                    staff.setFullName(rs.getString("staffName"));
                    inv.setSalesStaff(staff);
                }
                list.add(inv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Invoice5> getGuestInvoices(Date startDate, Date endDate) {
        ArrayList<Invoice5> list = new ArrayList<>();
        String sql = "SELECT i.*, ss.id AS salesStaffId, st.staffCode, st.position, u.fullName AS staffName " +
                     "FROM tblInvoice5 i " +
                     "LEFT JOIN tblSalesStaff5 ss ON i.tblSalesStaff5id = ss.id " +
                     "LEFT JOIN tblStaff5 st ON ss.tblStaff5id = st.id " +
                     "LEFT JOIN tblUser5 u ON st.tblUser5id = u.id " +
                     "WHERE i.tblCustomer5id IS NULL AND DATE(i.transactionTime) BETWEEN ? AND ? " +
                     "ORDER BY i.transactionTime DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Invoice5 inv = new Invoice5();
                inv.setId(rs.getInt("id"));
                inv.setTransactionTime(rs.getTimestamp("transactionTime"));
                inv.setPaymentMethod(rs.getString("paymentMethod"));
                inv.setTotalAmount(rs.getFloat("totalAmount"));
                inv.setDiscountApplied(rs.getFloat("discountApplied"));
                inv.setGuestName(rs.getString("guestName"));
                inv.setGuestPhone(rs.getString("guestPhone"));
                inv.setStatus("Đã thanh toán");

                int salesStaffId = rs.getInt("salesStaffId");
                if (!rs.wasNull() && salesStaffId > 0) {
                    SalesStaff5 staff = new SalesStaff5();
                    staff.setId(salesStaffId);
                    staff.setStaffCode(rs.getString("staffCode"));
                    staff.setPosition(rs.getString("position"));
                    staff.setFullName(rs.getString("staffName"));
                    inv.setSalesStaff(staff);
                }
                list.add(inv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}