package pages.tenBIsWeb;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private static final By addressTextBox = By.cssSelector("input[id*='homePage_SelectAddress']");
    private static final By addressDDL = By.cssSelector("ul[id*='homePage_DropDownAddress']");
    private static final By searchButton = By.cssSelector("button[aria-label*='send']");


    @Step
    public void addAnAddressToSearchBar(String address) {
        driver.findElement(addressTextBox).sendKeys(address);
        sleep(1);
    }

    @Step
    public ResListPage clickSearchButton() {
        click(searchButton);
        return new ResListPage(driver);
    }

    @Step
    public void waitForSearchButtonToBeClickable() {
        waitForElementToBeClickable(searchButton);
    }

    @Step
    public void selectFirstOptionFromDDL() {
        WebElement addressTB = driver.findElement(By.id("homePage_SelectAddress"));
        new Actions(driver).moveToElement(addressTB).perform();
        waitForElementToBeVisible(addressDDL);
        findList(addressDDL).get(0).click();
    }
}

