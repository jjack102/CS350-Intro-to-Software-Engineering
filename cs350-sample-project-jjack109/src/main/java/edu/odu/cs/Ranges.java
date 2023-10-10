package edu.odu.cs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * An ordered sequence of disjoint (non-overlapping) intervals.   
 */
public class Ranges implements Iterable<Interval> {

  LinkedList<Interval> remaining;

  /**
   * Create a range with no gaps.
   * 
   * @param low
   * @param high
   */
  Ranges(double low, double high) {
    remaining = new LinkedList<>();
    remaining.add(new Interval(low, high));
  }

  /**
   * Remove an interval from the range. E.g., if we start with the
   * range (0, 10), then subtracting (5,8) would leave all numbers in (0,5) and
   * (8,10).
   * 
   * @param toRemove the range of numbers to subtract
   */
  public void remove(Interval toRemove) {
    if (toRemove.width() == 0.0) {
      return;
    }
    ListIterator<Interval> iter = remaining.listIterator();
    while (iter.hasNext()) {
      Interval current = iter.next();
      if (current.getMin() > toRemove.getMax())
        break;
      replaceIfOverlapping(toRemove, iter, current);
    }
  }

  private void replaceIfOverlapping(Interval toRemove, ListIterator<Interval> iter, Interval current) {
    if (current.overlaps((toRemove))) {
      replaceOverlappingInterval(toRemove, iter, current);
    }
  }

  private void replaceOverlappingInterval(Interval toRemove, ListIterator<Interval> iter, Interval current) {
    Interval lowerPart = current.below(toRemove.getMin());
    Interval upperPart = current.above(toRemove.getMax());
    iter.remove();
    addIfNonEmpty(iter, lowerPart);
    addIfNonEmpty(iter, upperPart);
  }

  private void addIfNonEmpty(ListIterator<Interval> interval, Interval lowerPart) {
    if (lowerPart.width() > 0.0) {
      interval.add(lowerPart);
    }
  }

  /**
   * The sum of the widths of all remaining intervals on the number line.
   * 
   * @return the sum
   */
  public double sum() {
    double total = 0.0;
    for (Interval interval : remaining) {
      total += interval.width();
    }
    return total;
  }


  /**
   * For debugging purposes. Contents and format are not specified.
   */
  public String toString() {
    return remaining.toString();
  }

  /**
   * Allow iteration over the remaining intervals. Intervals will be disjoint and
   * will be presented in ascending order of starting position.
   * 
   * @return an iterator for the remaining intervals
   */
  public Iterator<Interval> iterator() {
    return remaining.iterator();
  }


}