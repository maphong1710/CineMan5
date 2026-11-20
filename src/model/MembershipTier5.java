package model;

public class MembershipTier5 {
    private int id;
    private String tierName;
    private int minPoint;
    private int maxPoint;
    private float discountPercent;

    public MembershipTier5() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTierName() { return tierName; }
    public void setTierName(String tierName) { this.tierName = tierName; }
    public int getMinPoint() { return minPoint; }
    public void setMinPoint(int minPoint) { this.minPoint = minPoint; }
    public int getMaxPoint() { return maxPoint; }
    public void setMaxPoint(int maxPoint) { this.maxPoint = maxPoint; }
    public float getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(float discountPercent) { this.discountPercent = discountPercent; }
}