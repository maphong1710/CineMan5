package model;

import java.sql.Date;

public class Movie5 {
    private int id;
    private String movieName;
    private String description;
    private int duration;
    private Date releaseDate;
    private String language;
    private String director;
    private String actors;
    private String status;
    private String poster;
    private String trailer;
    private String ageRating;

    public Movie5() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMovieName() { return movieName; }
    public void setMovieName(String movieName) { this.movieName = movieName; }
    public String getName() { return movieName; }
    public void setName(String name) { this.movieName = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public String getActors() { return actors; }
    public void setActors(String actors) { this.actors = actors; }
    public String getCast() { return actors; }
    public void setCast(String cast) { this.actors = cast; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }
    public String getPosterUrl() { return poster; }
    public void setPosterUrl(String posterUrl) { this.poster = posterUrl; }
    public String getTrailer() { return trailer; }
    public void setTrailer(String trailer) { this.trailer = trailer; }
    public String getAgeRating() { return ageRating; }
    public void setAgeRating(String ageRating) { this.ageRating = ageRating; }
}