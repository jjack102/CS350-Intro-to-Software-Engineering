package edu.odu.cs.cs350;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;


public class TestMagazine {

  Article loveless;
  Article ariAndDante;
  Article priory;
  Article telegraph;

  Article[] inputs;
  
  Magazine m1;
  Magazine m2;

  LocalDate pubDate;
  LocalDate date;
  LocalDate dateNow;
  
  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  public void setUp() throws Exception {

    loveless = new Article("Loveless", "Alice Oseman");
    ariAndDante = new Article("Aristotle and Dante Discover the Secrets of the Universe", "Benjamin Saenz");
    priory = new Article("The Priory of the Orange Tree", "Samantha Shannon");
    telegraph = new Article("Last Night at the Telegraph Club", "Malinda Lo");
   
    Article[] order = {loveless, telegraph, ariAndDante, priory};
    inputs = order;

    pubDate = LocalDate.of(2002, 01, 27);
    date = LocalDate.of(2003, 11, 07);
    dateNow = LocalDateTime.now().toLocalDate();

    m1 = new Magazine();
    m2 = new Magazine("War and Peace", pubDate);
  }


  @Test
  public void testDefaultConstructor() {
    assertEquals(0, m1.numArticles());
    assertFalse(m1.getTitle().contains("Heartstopper"));
    assertThat(m1.getTitle(), is(""));
    assertThat(m1.getPublicationDate(), is(dateNow));
    assertNull(m1.getArticle(0));
    Magazine m12 = new Magazine();
    assertEquals(m12, m1);
    assertNotNull(m1.toString());
    assertTrue(m1.toString().contains(dateNow.toString()));
  }

  @Test
  public void testConstructor() {
    assertEquals(0, m2.numArticles());
    assertFalse(m1.getTitle().contains("Heartstopper"));
    assertThat(m2.getTitle(), is("War and Peace"));
    assertThat(m2.getPublicationDate(), is(pubDate));
    assertNull(m2.getArticle(0));
    Magazine m22 = new Magazine("War and Peace", pubDate);
    assertEquals(m22, m2);
    assertTrue(m2.toString().contains("War and Peace"));
    assertTrue(m2.toString().contains(pubDate.toString()));
  }

  @Test
  public void testSetTitle() {
    m1.setTitle("Heartstopper");

    assertEquals("Heartstopper", m1.getTitle());
    assertEquals(dateNow, m1.getPublicationDate());

    assertFalse(m1.equals(m2));
    assertTrue(m1.toString().contains("Heartstopper"));
    assertTrue(m1.toString().contains(dateNow.toString()));
  }

  @Test
  public void testSetPubDate() {
    m1.setPublicationDate(date);

    assertEquals("", m1.getTitle());
    assertEquals(date, m1.getPublicationDate());

    assertFalse(m1.equals(m2));
    assertTrue(m1.toString().contains(date.toString()));
  }

  @Test
  public void testAddArticle() {
    m1.addArticle(0, priory);
    assertEquals(1, m1.numArticles());
    assertThat(m1.getArticle(0), equalTo(priory));
    assertFalse(m1.getArticle(0).equals(loveless));
    assertEquals(priory, m1.getArticle(0));
    assertNull(m1.getArticle(1));
    assertNotEquals(m1, new Magazine());
    Magazine m12 = new Magazine();
    m12.addArticle(0, priory);
    assertEquals(m12, m1);
    assertTrue(m1.toString().contains("The Priory of the Orange Tree"));
  }
/* 
  @Test
  public void testAddInBeginning() {
   
  }
  */

  @Test
  public void testAddInMiddle() {
    m1.addArticle(2, loveless);
    assertEquals(1, m1.numArticles());
    assertThat(m1.getArticle(2), equalTo(loveless));
    assertFalse(m1.getArticle(2).equals(priory));
    assertNull(m1.getArticle(0));
    assertEquals(loveless, m1.getArticle(2));
    assertNull(m1.getArticle(1));
    assertNotEquals(m1, new Magazine());
    Magazine m3 = new Magazine();
    m3.addArticle(2, loveless);
    assertEquals(m3, m1);
    assertTrue(m1.toString().contains("Loveless"));
  }
  
  /* 
  @Test
  public void testAddInEnd() {
    
  }

  */

  @Test
  public void testAddAndReplace() {
    m1.addArticle(0, priory);
    assertEquals(1, m1.numArticles());
    assertThat(m1.getArticle(0), equalTo(priory));
    assertFalse(m1.getArticle(0).equals(loveless));
    assertEquals(priory, m1.getArticle(0));
    assertNull(m1.getArticle(1));
    assertNotEquals(m1, new Magazine());
    Magazine m12 = new Magazine();
    m12.addArticle(0, priory);
    assertEquals(m12, m1);
    assertTrue(m1.toString().contains("The Priory of the Orange Tree"));

    m1.addArticle(0, ariAndDante);
    assertEquals(1, m1.numArticles());
    assertThat(m1.getArticle(0), equalTo(ariAndDante));
    assertFalse(m1.getArticle(0).equals(loveless));
    assertEquals(ariAndDante, m1.getArticle(0));
    assertNull(m1.getArticle(1));
    assertNotEquals(m1, new Magazine());
    m12.addArticle(0, ariAndDante);
    assertEquals(m12, m1);
    assertTrue(m1.toString().contains("Aristotle and Dante"));
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4})
  void testAddArticleMulti(int testSize) { 
    Article[][] expected = {{loveless}, 
          {loveless, telegraph},
          {loveless, telegraph, ariAndDante},
          {loveless, telegraph, ariAndDante, priory}
    };
    
    Magazine m10 = (Magazine)m1.clone();
    for(int i = 0; i < testSize; ++i) {
      m1.addArticle(i, inputs[i]);
    }

    assertEquals(testSize, m1.numArticles());
    for(int j = 0; j <= testSize - 1; ++j) {
      assertThat(m1.getArticle(j), equalTo(inputs[j]));
      assertEquals(inputs[j], m1.getArticle(j));
      assertTrue(m1.toString().contains(inputs[j].getTitle()));
    }
    for(int j = testSize; j < inputs.length; ++j) {
      assertFalse(m1.getTitle().contains(inputs[j].getTitle()));
      assertNull(m1.getArticle(j));
      assertFalse(m1.toString().contains(inputs[j].getTitle()));
    }
    assertNotEquals(m1, m10);
    //assertEquals(expected[testSize-1], m1);
    //assertEquals(testSize-1, m1.startingPages());

  }

  @Test
  public void testEquals() {
    Magazine m3 = new Magazine("Heartstopper", pubDate);
    Magazine m4 = new Magazine("Heartstopper", pubDate);

    Magazine m5 = new Magazine("Radio Silence", date);

    assertEquals(m3, m4);
    assertNotEquals(m3, m5);

    m3 = m5;
    assertEquals(m3, m5);
    assertNotEquals(m3, m4);
  }

  @Test
  public void testToString() {
    Magazine m3 = new Magazine("Heartstopper", pubDate);

    assertNotNull(m3);
    assertTrue(m3.toString().contains("Heartstopper"));
    assertTrue(m3.toString().contains(pubDate.toString()));
  }
  
  @Test
  public void testClone() {
    Magazine m10 = new Magazine();
    for(int i = 0; i < inputs.length; ++i) {
        m10.addArticle(i, inputs[i]);
    }
    
    Magazine m1 = (Magazine)m10.clone();

    assertThat(m1.numArticles(), is(m10.numArticles()));
    assertThat(m1, equalTo(m10));
    assertThat(m1.toString(), equalTo(m10.toString()));
  }
 
}
