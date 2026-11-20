package model;

import java.sql.Date;

public class MemberCard5 {
    private int id;
    private String cardCode;
    private Date issueDate;
    private int loyaltyPoints;
    private MembershipTier5 tier;

    public MemberCard5() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCardCode() { return cardCode; }
    public void setCardCode(String cardCode) { this.cardCode = cardCode; }
    public Date getIssueDate() { return issueDate; }
    public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public void setLoyaltyPoints(int loyaltyPoints) { this.loyaltyPoints = loyaltyPoints; }
    public MembershipTier5 getTier() { return tier; }
    public void setTier(MembershipTier5 tier) { this.tier = tier; }
}