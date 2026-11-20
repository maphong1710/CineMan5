package model;

public class CustomerStatistic5 extends Customer5 {
    private int customerId;
    private String customerName;
    private String customerPhone;
    private int ticketCount;
    private float totalRevenue;
    private float percentage;
    private String rank;

    public CustomerStatistic5() {}

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
    public int getTicketCount() { return ticketCount; }
    public void setTicketCount(int ticketCount) { this.ticketCount = ticketCount; }
    public float getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(float totalRevenue) { this.totalRevenue = totalRevenue; }
    public float getPercentage() { return percentage; }
    public void setPercentage(float percentage) { this.percentage = percentage; }
    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }
}