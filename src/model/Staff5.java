package model;

public class Staff5 extends User5 {
    protected String staffCode;
    protected String position;

    public Staff5() {}

    public String getStaffCode() { return staffCode; }
    public void setStaffCode(String staffCode) { this.staffCode = staffCode; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
}