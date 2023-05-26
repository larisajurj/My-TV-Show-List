package utilities;

import javafx.scene.control.Button;

public class TvShowList {
    private String title;
    private String year;
    private String duration;
    private String rating;
    private String genre;
    private String text;
    private Button add_to_watched;

    public TvShowList(String title, String year, String duration, String rating, String genre, String text) {
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.rating = rating;
        this.genre = genre;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TvShowList{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", duration='" + duration + '\'' +
                ", rating='" + rating + '\'' +
                ", genre='" + genre + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
