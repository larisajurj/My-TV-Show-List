package java.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.TvShowList;

public class TvShowListTest {

    @Test
    public void testGettersAndSetters() {
        // Create an instance of TvShowList
        TvShowList tvShow = new TvShowList("Friends", "1994", "30 min", "9.0", "Comedy", "A sitcom about a group of friends...");

        // Test getters
        Assertions.assertEquals("Friends", tvShow.getTitle(), "Incorrect title.");
        Assertions.assertEquals("1994", tvShow.getYear(), "Incorrect year.");
        Assertions.assertEquals("30 min", tvShow.getDuration(), "Incorrect duration.");
        Assertions.assertEquals("9.0", tvShow.getRating(), "Incorrect rating.");
        Assertions.assertEquals("Comedy", tvShow.getGenre(), "Incorrect genre.");
        Assertions.assertEquals("A sitcom about a group of friends...", tvShow.getText(), "Incorrect text.");

        // Test setters
        tvShow.setTitle("The Big Bang Theory");
        Assertions.assertEquals("The Big Bang Theory", tvShow.getTitle(), "Title not set correctly.");

        tvShow.setYear("2007");
        Assertions.assertEquals("2007", tvShow.getYear(), "Year not set correctly.");

        tvShow.setDuration("22 min");
        Assertions.assertEquals("22 min", tvShow.getDuration(), "Duration not set correctly.");

        tvShow.setRating("8.2");
        Assertions.assertEquals("8.2", tvShow.getRating(), "Rating not set correctly.");

        tvShow.setGenre("Sitcom");
        Assertions.assertEquals("Sitcom", tvShow.getGenre(), "Genre not set correctly.");

        tvShow.setText("A sitcom about a group of socially awkward physicists...");
        Assertions.assertEquals("A sitcom about a group of socially awkward physicists...", tvShow.getText(), "Text not set correctly.");
    }

    @Test
    public void testToString() {
        TvShowList tvShow = new TvShowList("Breaking Bad", "2008", "60 min", "9.5", "Drama", "A high school chemistry teacher...");
        String expectedString = "TvShowList{" +
                "title='Breaking Bad', " +
                "year='2008', " +
                "duration='60 min', " +
                "rating='9.5', " +
                "genre='Drama', " +
                "text='A high school chemistry teacher...'}";
        Assertions.assertEquals(expectedString, tvShow.toString(), "Incorrect toString representation.");
    }
}
