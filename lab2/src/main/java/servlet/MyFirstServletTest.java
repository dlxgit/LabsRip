package servlet;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * test class
 */
public class MyFirstServletTest {
  /**
   * Webdriver
   */
  private WebDriver driver;

  /**
   * beginning url
   */
  private static final String BASE_URL = "http://localhost:8080/";

  /**
   *
   */
  @Before
  public void createDriver() {
    System.setProperty("webdriver.gecko.driver", "D:\\sdk\\geckodriver.exe");
    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
    capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
    driver = new FirefoxDriver(capabilities);
    driver.get(BASE_URL);
  }

  /**
   *
   */
  @After
  public void quitDriver() {
    driver.manage().deleteAllCookies();
    driver.quit();
  }

  /**
   * book is valid
   */
  @Test
  public void testValidBook() {
    System.out.println("html!:" + driver.getPageSource());
    fillField("book", "Effective Java");
    fillField("author", "Joshua Bloch");
    fillField("date", "12.12.1400");
    fillField("genre", "Popular");
    fillField("rating", "4");
    submitForm();
    checkBooksCount(1);
  }

  /**
   * incorrect date format
   */
  @Test
  public void testBadDate() {
    fillField("book", "Qwerty");
    fillField("author", "Alexander");
    fillField("date", "1224.20");
    fillField("genre", "Popular");
    fillField("rating", "2");
    submitForm();
    checkBooksCount(0);
  }

  /**
   * request without date param
   */
  @Test
  public void testMissingDate() {
    fillField("book", "2zxc2");
    fillField("author", "tt5");
    fillField("genre", "historic");
    fillField("rating", "5");
    submitForm();
    checkBooksCount(0);
  }

  /**
   * request without any filled field
   */
  @Test
  public void testMissingAllFields() {
    submitForm();
    checkBooksCount(0);
  }

  /**
   * if we put string value into rating field
   */
  @Test
  public void testStringRating() {
    fillField("book", "xzc");
    fillField("author", "sfdr");
    fillField("date", "12.12.2012");
    fillField("genre", "science");
    fillField("rating", "a");
    submitForm();
    checkBooksCount(0);
  }

  /**
   * test for very long integer
   */
  @Test
  public void testVeryLongRating() {
    fillField("book", "zxcvc");
    fillField("author", "gfdtrtt");
    fillField("date", "10.01.2015");
    fillField("genre", "science");
    fillField("rating",
        "111111111111111111111111111111111"
        + "111111111111111111111111111111111"
        + "111111111111111111111111111111111"
        + "111111111111111111111111111111111"
        + "111111111111111111111111111111111");
    wait(7000);
    submitForm();
    checkBooksCount(0);
  }


  /**
   * @param field   field that we want to fill
   * @param value   value of this field
   */
  private void fillField(String field, String value) {
    WebElement input = driver.findElement(By.name(field));
    assertNotNull(input);
    input.sendKeys(value);
  }

  /**
   * click on input
   */
  private void submitForm() {
    WebElement input = driver.findElement(By.id("addBtn"));
    assertNotNull(input);
    input.click();
    wait(4000);
  }

  /**
   * go to page with all books
   */
  private void showAllBooks() {
    WebElement input = driver.findElement(By.id("showBtn"));
    assertNotNull(input);
    input.click();
    wait(1000);

  }


  /**
   * @param milliSeconds waiting time
   */
  private void wait(final int milliSeconds) {
    driver.manage().timeouts().implicitlyWait(milliSeconds, TimeUnit.MILLISECONDS);
  }

  private int getCountOfBooks(final String html) {
    Pattern pattern = Pattern.compile("<p>_______________</p>");
    Matcher matcher = pattern.matcher(html);
    int count = 0;
    while (matcher.find()) {
      count++;
    }
    return count;
  }

  /**
   * @param count expected number
   */
  private void checkBooksCount(final int count) {
    showAllBooks();
    String html = driver.getPageSource();
    assertEquals(count, getCountOfBooks(html));
  }
}
