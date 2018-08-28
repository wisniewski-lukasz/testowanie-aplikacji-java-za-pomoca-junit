package pl.rsokol;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import org.junit.Ignore;

public class ProductRatingTest {

    @Before
    public void before() {
        rating = new ProductRating();
    }

    @Test
    public void create() {
        assertEquals(0, rating.getScore());
    }

    @Test
    @Ignore
    public void setScore() {
        rating.setScore(3);
        assertEquals(3, rating.getScore());
    }

    @Test
    public void setScoreZero() {
        rating.setScore(0);
        assertEquals(0, rating.getScore());
    }

    @Test
    public void setScoreFive() {
        rating.setScore(5);
        assertEquals(5, rating.getScore());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setScoreLessThanZero() {
        rating.setScore(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setScoreLessMoreThanFive() {
        rating.setScore(6);
    }

    private ProductRating rating;

}
