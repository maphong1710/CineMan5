package model;

import java.sql.Timestamp;

public class Ticket5 {
    private int id;
    private float actualPrice;
    private String seatNumber;
    private String ticketType;
    private String status;
    private String movieName;
    private String roomName;
    private Timestamp startTime;
    private Showtime5 showtime;
    private Seat5 seat;

    public Ticket5() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public float getActualPrice() { return actualPrice; }
    public void setActualPrice(float actualPrice) { this.actualPrice = actualPrice; }
    public float getPrice() { return actualPrice; }
    public void setPrice(float price) { this.actualPrice = price; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public String getTicketType() { return ticketType; }
    public void setTicketType(String ticketType) { this.ticketType = ticketType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMovieName() { return movieName; }
    public void setMovieName(String movieName) { this.movieName = movieName; }
    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }
    public Timestamp getStartTime() { return startTime; }
    public void setStartTime(Timestamp startTime) { this.startTime = startTime; }
    public Showtime5 getShowtime() { return showtime; }
    public void setShowtime(Showtime5 showtime) { this.showtime = showtime; }
    public Seat5 getSeat() { return seat; }
    public void setSeat(Seat5 seat) { this.seat = seat; }
}