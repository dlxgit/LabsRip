package com.avasilyev.sprboot;

import org.hibernate.boot.jaxb.SourceType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;



@RunWith(SpringRunner.class)
@SpringBootTest
public class SprbootApplicationTests {
	private static ChromeDriverService service;
	private WebDriver driver;

	private static final String BASE_URL = "http://localhost:8090/index/";

	@Before
	public void createDriver() {
		System.setProperty("webdriver.gecko.driver", "D:\\sdk\\geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		driver = new FirefoxDriver(capabilities);
		driver.get(BASE_URL);
	}

	@After
	public void quitDriver() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	@Test
	public void testValidBook() {
		System.out.println("html!:" + driver.getPageSource());
		fillField("name", "Effective Java");
		fillField("author", "Joshua Bloch");
		fillField("date", "12/12/1400");
		fillField("genre", "Popular");
		fillField("rating", "4");
		System.out.println("Filled form" + driver.getPageSource());
		submitForm();
		checkBooksCount(1);
	}

	@Test
	public void testBadDate() {
		fillField("name", "Qwerty");
		fillField("author", "Alexander");
		fillField("date", "1224.20");
		fillField("genre", "Popular");
		fillField("rating", "2");
		submitForm();
		checkBooksCount(0);
	}

	@Test
	public void testMissingDate() {
		fillField("name", "2zxc2");
		fillField("author", "tt5");
		fillField("genre", "historic");
		fillField("rating", "5");
		submitForm();
		checkBooksCount(0);
	}

	@Test
	public void testMissingAllFields() {
		submitForm();
		checkBooksCount(0);
	}

	@Test
	public void testStringRating() {
		fillField("name", "xzc");
		fillField("author", "sfdr");
		fillField("date", "12.12.2012");
		fillField("genre", "science");
		fillField("rating", "a");
		submitForm();
		checkBooksCount(0);
	}

	@Test
	public void testVeryLongRating() {
		fillField("name", "zxcvc");
		fillField("author", "gfdtrtt");
		fillField("date", "10.01.2015");
		fillField("genre", "science");
		fillField("rating", "111111111111111111111111111111111" +
			"1111111111111111111111111111111111111111111111111111111" +
			"11111111111111111111111111111111111111111111111111111111" +
			"1111111111111111111111111111111");
		wait(7000);
		submitForm();
		checkBooksCount(0);
	}


	private void fillField(String field, String value) {
		System.out.println("HTML!: " + driver.getPageSource());

		WebElement input = driver.findElement(By.name(field));
		assertNotNull(input);
		input.sendKeys(value);
	}

	private void submitForm() {
		WebElement input = driver.findElement(By.id("addBtn"));
		assertNotNull(input);
		input.click();
		wait(4000);
	}

	private void showAllBooks() {
		wait(1000);
	}


	private void wait(int milliSeconds) {
		driver.manage().timeouts().implicitlyWait(milliSeconds, TimeUnit.MILLISECONDS);
	}

	private int getCountOfBooks(String html) {
		Pattern pattern = Pattern.compile("<p>_______________</p>");
		Matcher matcher = pattern.matcher(html);
		int count = 0;
		while (matcher.find()) {
			count++;
		}
		return count;
	}

	private boolean existsBookWithPattern(String pattern, String html) {
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(html);
		return matcher.find();
	}

	private void checkBooksCount(int count) {
		showAllBooks();
		String html = driver.getPageSource();
		System.out.println("booksShow: " + html);
		assertEquals(count, getCountOfBooks(html));
	}
}
