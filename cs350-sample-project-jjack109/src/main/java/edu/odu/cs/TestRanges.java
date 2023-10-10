package edu.odu.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.ArgumentsSource;
// import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.contains;

// import org.hamcrest.core.IsEqual;

/**
 * Test of the Ranges class
 */
public class TestRanges {

    double precision = 0.001;

    Interval toRemoveHigh;
    Interval toRemoveMiddle;
    Interval toRemoveEnd;
    Interval toRemove;

    @BeforeEach
    void setup() {
        toRemoveHigh = new Interval(0.0, 3.0);
        toRemoveMiddle = new Interval(5.0, 8.0);
        toRemoveEnd = new Interval(8.0, 10.0);

        toRemove = new Interval(5.0, 8.0);

    }

    @Test
    public void testConstructor() {
        
        Ranges ranges = new Ranges(1.0, 100.0);
        assertThat(ranges.sum(), closeTo(99.0, precision)); // checks the ranges.sum()?

        Interval[] expected = { new Interval(1.0, 100.0) };
        assertThat (ranges, contains(expected)); // checks ranges.iterator()

        assertThat(ranges.iterator().hasNext(), is(true));
    }



     /**
   * Remove an interval from the range. E.g., if we start with the
   * range (0, 10), then subtracting (5,8) would leave all numbers in (0,5) and
   * (8,10).
   */
    @Test
    public void testRemoveInterval() {
       Ranges ranges = new Ranges(0.0, 10.0);
       //Interval interval = new Interval(5.0, 8.0);
       
       //call the mutator
       ranges.remove(toRemove);
       
       //examine the accessors
       assertThat(ranges.sum(), closeTo(7.0, precision));   // check the ranges.sum()
      
       Interval[] expected = { new Interval(0.0, 5.0), new Interval(8.0,10.0)};
       assertThat(ranges, contains(expected));
       assertThat (ranges.iterator().hasNext(), is(true));

    }

    @Test
    public void testSubtractLowEnd() {
        Ranges ranges = new Ranges(10.0, 100.0);
        Interval interval = new Interval(0.0, 20.0);
        ranges.remove(interval);
        assertThat(ranges.sum(), closeTo(80.0, precision));
        Interval[] expected = { new Interval(20.0, 100.0) };
        assertThat (ranges, contains(expected));
    }

    @Test
    public void testSubtractHighEnd() {
        Ranges ranges = new Ranges(10.0, 100.0);
        Interval interval = new Interval(90.0, 110.0);
        ranges.remove(interval);
        assertThat(ranges.sum(), closeTo(80.0, precision));
        Interval[] expected = { new Interval(10.0, 90.0) };
        assertThat (ranges, contains(expected));
    }

    @Test
    public void testSubtractInterior() {
        Ranges ranges = new Ranges(10.0, 100.0);
        Interval interval = new Interval(20.0, 90.0);
        ranges.remove(interval);
        assertThat(ranges.sum(), closeTo(20.0, precision));
        Interval[] expected = { new Interval(10.0, 20.0), new Interval(90.0, 100.0) };
        assertThat (ranges, contains(expected));
    }

    @Test
    public void testSubtractAll() {
        Ranges ranges = new Ranges(10.0, 100.0);
        Interval interval = new Interval(9.0, 110.0);
        ranges.remove(interval);
        assertThat(ranges.sum(), closeTo(0.0, precision));
        assertThat (ranges.iterator().hasNext(), is(false));
    }

    @Test
    public void testSubtractNone() {
        Ranges ranges = new Ranges(10.0, 100.0);
        Interval interval = new Interval(101.0, 110.0);
        ranges.remove(interval);
        assertThat(ranges.sum(), closeTo(90.0, precision));
        Interval[] expected = { new Interval(10.0, 100.0)};
        assertThat (ranges, contains(expected));
    }

    @Test
    public void testSubtractEmpty() {
        Ranges ranges = new Ranges(10.0, 100.0);
        Interval interval = new Interval(51.0, 50.0);
        ranges.remove(interval);
        assertThat(ranges.sum(), closeTo(90.0, precision));
        Interval[] expected = { new Interval(10.0, 100.0)};
        assertThat (ranges, contains(expected));
    }
}
