package model;

import java.util.ArrayList;

public class Room5 {
    private int id;
    private String roomName;
    private int capacity;
    private String description;
    private int bufferMinutes;
    private ArrayList<Seat5> seats;

    public Room5() { this.seats = new ArrayList<>(); }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getBufferMinutes() { return bufferMinutes; }
    public void setBufferMinutes(int bufferMinutes) { this.bufferMinutes = bufferMinutes; }
    public ArrayList<Seat5> getSeats() { return seats; }
    public void setSeats(ArrayList<Seat5> seats) { this.seats = seats; }
}