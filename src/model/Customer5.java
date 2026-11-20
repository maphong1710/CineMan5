package model;

import java.sql.Date;

public class Customer5 extends User5 {
    private String customerCode;
    private String address;
    private String memberCardId;
    private Date registerDate;
    private MemberCard5 memberCard;

    public Customer5() {}

    public String getCustomerCode() { return customerCode; }
    public void setCustomerCode(String customerCode) { this.customerCode = customerCode; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getMemberCardId() { return memberCardId; }
    public void setMemberCardId(String memberCardId) { this.memberCardId = memberCardId; }
    public Date getRegisterDate() { return registerDate; }
    public void setRegisterDate(Date registerDate) { this.registerDate = registerDate; }
    public MemberCard5 getMemberCard() { return memberCard; }
    public void setMemberCard(MemberCard5 memberCard) { this.memberCard = memberCard; }
    public String getName() { return fullName; }
    public void setName(String name) { this.fullName = name; }
}