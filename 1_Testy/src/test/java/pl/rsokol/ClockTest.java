package pl.rsokol;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClockTest {

    @Test
    public void create() {
        final Clock clock = Clock.of(12, 15);
        assertEquals(12, clock.getHours());
        assertEquals(15, clock.getMinutes());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNegativeHours() {
        Clock.of(-1, 15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidHours() {
        Clock.of(25, 15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNegativeMinutes() {
        Clock.of(12, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidMinutes() {
        Clock.of(12, 60);
    }

    @Test
    public void addMinutesNoHourSkip() {
        final Clock clock = Clock.of(12, 30).add(29);
        assertEquals(12, clock.getHours());
        assertEquals(59, clock.getMinutes());
    }

    @Test
    public void addMinutesHourSkip() {
        final Clock clock = Clock.of(12, 30).add(30);
        assertEquals(13, clock.getHours());
        assertEquals(0, clock.getMinutes());
    }

    @Test
    public void addMinutesDaySkip() {
        final Clock clock = Clock.of(22, 30).add(90);
        assertEquals(0, clock.getHours());
        assertEquals(0, clock.getMinutes());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addMinutesNegative() {
        Clock.of(22, 30).add(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addClockNull() {
        Clock.of(10, 11).add(null);
    }

    @Test
    public void addClockNoHourSkip() {
        final Clock sum = Clock.of(12, 30).add(Clock.of(0, 29));
        assertEquals(12, sum.getHours());
        assertEquals(59, sum.getMinutes());
    }

    @Test
    public void addClockHourSkip() {
        final Clock sum = Clock.of(12, 30).add(Clock.of(0, 30));
        assertEquals(13, sum.getHours());
        assertEquals(0, sum.getMinutes());
    }

    @Test
    public void addClockDaySkip() {
        final Clock sum = Clock.of(22, 56).add(Clock.of(12, 31));
        assertEquals(11, sum.getHours());
        assertEquals(27, sum.getMinutes());
    }

}
