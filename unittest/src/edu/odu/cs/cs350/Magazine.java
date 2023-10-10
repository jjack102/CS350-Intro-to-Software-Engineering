package edu.odu.cs.cs350;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * A magazine containing a collection of articles, organized by (starting) page number.
 * 
 * 
 * @author zeil
 *
 */
public class Magazine implements Cloneable {

  
  private String title;
  private Map<Integer, Article> contents;
  private LocalDate date;
  

  /**
   * Create a "blank" magazine with empty strings for title, the current
   * date for the publication date,
   * and an empty (zero-length) list of articles.
   */
  public Magazine() {
    title = "";
    date = LocalDateTime.now().toLocalDate();
    contents = new TreeMap<Integer, Article>();
  }

  /**
   * Create a new magazine.
   * @param title title of the magazine
   * @param publDate publication date of the magazine.
   */
  public Magazine(String title, LocalDate pubDate) {
    this.title = title;
    this.date = pubDate;
    this.contents = new TreeMap<Integer, Article>();
  }

  
  
  /**
   * Get the title of this magazine.
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Set the title of this magazine.
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }


  /**
   * Get the publication date of this magazine.
   * @return the date
   */
  public LocalDate getPublicationDate() {
    return date;
  }

  /**
   * Set the publication date of this magazine.
   * @param pubDate the publication date to set
   */
  public void setPublicationDate(LocalDate pubDate) {
    this.date = pubDate;
  }

  
  /**
   * How many articles does this magazine have?
   * @return number of articles
   */
  public int numArticles() {
    return contents.size();
  }
  
  
  /**
   * Add an article to the magazine at an indicated starting page.
   * 
   * If an article is already at that page, replaces the existing one.
   * 
   * @param startingPage first page of the article
   * @param article author to be added
   */
  public void addArticle(int startingPage, Article article) {
    contents.put(startingPage, article);
  }
  
  /**
   * Get the article previously placed at a given starting page.
   * 
   * @param startingAtPage a page number in the magazine
   * @return the article starting at that page, or null if no article
   *     has been put there.
   */
  public Article getArticle (int startingAtPage) {
      return contents.get(startingAtPage);
  }
  
  
  /**
   * Render the magazine as a string in a format guaranteed to
   * contain all fields.
   */
  public String toString() {
    StringBuffer buf = new StringBuffer();
    buf.append(title);
    buf.append(", ");
    buf.append(date.toString());
    buf.append("\nContents:");
    for (Integer p: contents.keySet()) {
        buf.append("\n   ");
        buf.append(p);
        buf.append("  ");
        buf.append(contents.get(p).toString());
    }
    return buf.toString();
  }
  
  

 /**
   * Compares two magazines for equality. They are considered equal if
   * all functions on them return equal results..
   *
   * @param obj object to be compared for equality with this magazine
   * @return <tt>true</tt> if the specified object is equal to this one
   */
  public boolean equals(Object obj) {
    if (!(obj instanceof Magazine)) {
      return false;
    }
    Magazine other = (Magazine)obj;
    if (!title.equals(other.title))
        return false;
    if (!date.equals(other.date))
        return false;
    if (!contents.equals(other.contents))
        return false;
    return true;
  }


  /**
   * Return a (deep) copy of this object.
   */
  @Override
  public Object clone()  {
    Magazine theClone = new Magazine(title, date);
    for (Integer p: startingPages()) {
      theClone.addArticle(p, getArticle(p));
    }
    return theClone;
  }

  /**
   * Provide access to the table of contents of
   * this magazine. e.g.,
   *     Magazine magazine = new Magazine(...);
   *        ...
   *     for (Integer page: magazine,startingPages()) {
   *        Article art = magazine.getArticle(page);
   *        doSomethingWith (page, art);
   *     }
   * 
   * @return iterator over the starting page numbers. Numbers
   *        are returned in ascending order.
   */
  public Set<Integer> startingPages() {
    return contents.keySet();
  }


}
