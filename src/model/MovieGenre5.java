package model;

public class MovieGenre5 {
    private int id;
    private String genreName;
    private String description;
    private Movie5 movie;
    private Genre5 genre;

    public MovieGenre5() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getGenreName() { return genreName; }
    public void setGenreName(String genreName) { this.genreName = genreName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Movie5 getMovie() { return movie; }
    public void setMovie(Movie5 movie) { this.movie = movie; }
    public Genre5 getGenre() { return genre; }
    public void setGenre(Genre5 genre) { this.genre = genre; }
}