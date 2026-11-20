package model;

import java.sql.Timestamp;

public class Showtime5 {
    private int id;
    private Timestamp startTime;
    private Timestamp endTimeExpected;
    private float ticketPrice;
    private String format;
    private String roomName;
    private int totalSeats;
    private int availableSeats;
    private Movie5 movie;
    private Room5 room;
    private Promotion5 promotion;

    public Showtime5() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Timestamp getStartTime() { return startTime; }
    public void setStartTime(Timestamp startTime) { this.startTime = startTime; }
    public Timestamp getEndTimeExpected() { return endTimeExpected; }
    public void setEndTimeExpected(Timestamp endTimeExpected) { this.endTimeExpected = endTimeExpected; }
    public Timestamp getEndTime() { return endTimeExpected; }
    public void setEndTime(Timestamp endTime) { this.endTimeExpected = endTime; }
    public float getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(float ticketPrice) { this.ticketPrice = ticketPrice; }
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }
    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }
    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
    public Movie5 getMovie() { return movie; }
    public void setMovie(Movie5 movie) { this.movie = movie; }
    public Room5 getRoom() { return room; }
    public void setRoom(Room5 room) { this.room = room; }
    public Promotion5 getPromotion() { return promotion; }
    public void setPromotion(Promotion5 promotion) { this.promotion = promotion; }
}