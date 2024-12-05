package com.mayab.quality.functional;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.time.Duration;

public class SeleniumTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  
  @Before
  public void setUp() throws Exception {
	WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    baseUrl = "https://www.facebook.com/";
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testUntitledTestCase() throws Exception {
      driver.get("https://www.facebook.com/");
      pause(5000);
      
      driver.findElement(By.id("email")).clear();
      driver.findElement(By.id("email")).sendKeys("puppies");
      
      driver.findElement(By.id("pass")).clear();
      driver.findElement(By.id("pass")).sendKeys("password");

      driver.findElement(By.name("login")).click();
      
      pause(5000);

      String actualResult = driver.findElement(By.xpath("//*[@id=\"email_container\"]/div[2]")).getText();
      assertThat(actualResult, is("El correo electrónico o número de móvil que has introducido no está "
      		+ "conectado a una cuenta. Encuentra tu cuenta e inicia sesión."));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
  
  private void pause(long mils) {
      try {
          Thread.sleep(mils);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}