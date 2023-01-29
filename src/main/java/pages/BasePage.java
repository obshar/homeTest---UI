package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.*;
import tools.Config;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BasePage {

  protected WebDriver driver;

  protected WebDriverWait wait;
  private Logger logger = Logger.getLogger(String.valueOf(BasePage.class));

  public BasePage(WebDriver driver) {
    wait = new WebDriverWait(driver, Config.POLLING_PERIOD);
    this.driver = driver;
  }

  public WebElement find(By locator) {
    logger.info(String.format("Locating element '%s'", locator.toString()));
    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
  }

  public void waitForElementToAppear(By locator) {
    logger.info(String.format("Locating element '%s'", locator.toString()));
     wait.until(ExpectedConditions.presenceOfElementLocated(locator));
  }

  public boolean isElementVisible(By locator) {
    return driver.findElement(locator).isDisplayed();
  }

  public List<WebElement> findList(By locator) {
    logger.info(String.format("Locating element '%s'", locator.toString()));
    return driver.findElements(locator);
  }

  public void click(By locator) {
    try {
      logger.info(String.format("Clicking element '%s'", locator.toString()));
      find(locator).click();
    }catch (NoSuchElementException e) {
       }
  }

  protected void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
  }

  protected WebElement waitForElementToBeVisible(final By locator) {
    Wait<WebDriver> wait = new FluentWait<>(driver)
        .withTimeout(10, TimeUnit.SECONDS)
        .pollingEvery(200, TimeUnit.MILLISECONDS);
    return wait.until(ExpectedConditions.visibilityOf(find(locator)));
  }

  protected void sleep(long intInSec) {
    System.out.println("sleep for " + intInSec + " seconds");
    try {
      Thread.sleep(Duration.ofSeconds(intInSec).toMillis());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
