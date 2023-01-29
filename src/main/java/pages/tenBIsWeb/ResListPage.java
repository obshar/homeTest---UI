package pages.tenBIsWeb;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class ResListPage extends BasePage {

    public ResListPage(WebDriver driver) {
        super(driver);
    }

    private static final By expandCuisinesList = By.cssSelector("button[data-test-id='cuisinesCollapseToggle']");
    private static final By resListIndicator = By.className("RestaurantItem__Root-sc-1771gcw-0");
    //    private static final By cuisinePanel = By.cssSelector("div[class*='RestaurantItem__CuisineTypes']");
    private static final By mutatedResList = By.className("cPVOqm");
    private static final By openSortListByButton = By.cssSelector("button[data-test-id='sortListByCollapseToggle']");


    @Step
    public boolean isURLCorrect(String expectedPageURL) {
        return driver.getCurrentUrl().equals(expectedPageURL);
    }

    @Step
    public void selectPreference(String title) {
        java.util.List<WebElement> listOfPref = driver.findElements(By.cssSelector("ul[class*='styled__List-sc-15zwduo-3']>li"));
        for (WebElement preference : listOfPref) {
            if (preference.getText().equals(title)) {
                preference.click();
                return;
            }
        }
    }

    @Step
    public boolean validateTenbisLogoExists() {
        boolean isIconThere = true;
        java.util.List<WebElement> resList = driver.findElements(By.cssSelector("div[class*='RestaurantItem']>div>[class*='ScooberBadge__ScooberText']"));
        for (WebElement icon : resList) {
            if (!icon.getText().equals("10bis Delivery")) {
                System.out.println("the bad elelemt is " + icon.getText());
                isIconThere = false;
            }
        }
        return isIconThere;
    }

    @Step
    public void clickExpandCuisinesList() {
        click(expandCuisinesList);
    }

    @Step
    public void clickOpenSortListByButton() {
        click(openSortListByButton);
    }

    @Step
    public void selectCuisine(String title) {
        java.util.List<WebElement> listOfcuisine = driver.findElements(By.cssSelector("li[data-test-id='cuisineItem']"));
        for (WebElement cuisine : listOfcuisine) {
            if (cuisine.getText().equals(title)) {
                cuisine.click();
                return;
            }
        }
    }

    @Step
    public boolean validateCuisineFiltered() {
        boolean isCuisineSelected = true;
        java.util.List<WebElement> resList = driver.findElements(By.cssSelector("div[class*='RestaurantItem__CuisineTypes']"));
        for (WebElement cuisineLabel : resList) {
            if (!cuisineLabel.getText().contains("Humus")) {
                System.out.println(cuisineLabel.getText());
                isCuisineSelected = false;
            }
        }
        return isCuisineSelected;
    }

    @Step
    public void waitForResListToAppear() {
        waitForElementToAppear(resListIndicator);
    }

    @Step
    public void waitForFiltersToBeClickable() {
        sleep(1);
        waitForElementToBeClickable(By.cssSelector("div[class*='RestaurantItem__CuisineTypes']"));
    }

    @Step
    public void waitForNewResListToLoad() {
        waitForElementToAppear(mutatedResList);
    }

    @Step
    public boolean isRecommendedSelectedByDefault() {
        sleep(2);
        boolean isRecommendedSelected = false;
        java.util.List<WebElement> sortListByOptions = driver.findElements(By.cssSelector("ul[class*='hlKOqi']>li"));
        for (WebElement option : sortListByOptions) {
            if (option.getAttribute("data-test-id").equals("sortByItem_selected") && option.getText().equals("10bis recommended")) {
                isRecommendedSelected = true;
                break;
            }
        }
        return isRecommendedSelected;
    }

    @Step
    public void selectSortListByOption(String optionName) {
        java.util.List<WebElement> sortListByOptions = driver.findElements(By.cssSelector("ul[class*='hlKOqi']>li"));
        for (WebElement option : sortListByOptions) {
            if (option.getText().equals(optionName)) {
                option.click();
            }
        }
    }

    @Step
    public boolean validateRatingIsSelected() {
        java.util.List<WebElement> resCards = driver.findElements(By.cssSelector("div[data-test-id='restaurantTag-Rating']"));
        String firsCard = resCards.get(0).getText().replaceAll("(\\w+\\s){4}", "");
        String secondCard = resCards.get(1).getText().replaceAll("(\\w+\\s){4}", "");
        return Double.parseDouble(firsCard) >= Double.parseDouble(secondCard);
    }
}
