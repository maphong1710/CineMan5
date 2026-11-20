package model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Invoice5 {
    private int id;
    private Timestamp transactionTime;
    private String paymentMethod;
    private float totalAmount;
    private float discountApplied;
    private String guestName;
    private String guestPhone;
    private String status;
    private ArrayList<Ticket5> tickets;
    private Customer5 customer;
    private SalesStaff5 salesStaff;

    public Invoice5() { this.tickets = new ArrayList<>(); }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Timestamp getTransactionTime() { return transactionTime; }
    public void setTransactionTime(Timestamp transactionTime) { this.transactionTime = transactionTime; }
    public Timestamp getPaymentDate() { return transactionTime; }
    public void setPaymentDate(Timestamp paymentDate) { this.transactionTime = paymentDate; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public float getTotalAmount() { return totalAmount; }
    public void setTotalAmount(float totalAmount) { this.totalAmount = totalAmount; }
    public float getDiscountApplied() { return discountApplied; }
    public void setDiscountApplied(float discountApplied) { this.discountApplied = discountApplied; }
    public String getGuestName() { return guestName; }
    public void setGuestName(String guestName) { this.guestName = guestName; }
    public String getGuestPhone() { return guestPhone; }
    public void setGuestPhone(String guestPhone) { this.guestPhone = guestPhone; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public ArrayList<Ticket5> getTickets() { return tickets; }
    public void setTickets(ArrayList<Ticket5> tickets) { this.tickets = tickets; }
    public Customer5 getCustomer() { return customer; }
    public void setCustomer(Customer5 customer) { this.customer = customer; }
    public SalesStaff5 getSalesStaff() { return salesStaff; }
    public void setSalesStaff(SalesStaff5 salesStaff) { this.salesStaff = salesStaff; }
}