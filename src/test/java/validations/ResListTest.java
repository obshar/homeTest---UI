package validations;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.tenBIsWeb.LoginPage;
import pages.tenBIsWeb.ResListPage;
import tools.BaseTest;

public class ResListTest extends BaseTest {

    @Test(priority = 1)
    @Description("go to res list and filter by preference.")
    public void filterByPreference() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.addAnAddressToSearchBar("Tel-Aviv, rotchild 22");
        loginPage.selectFirstOptionFromDDL();
        loginPage.waitForSearchButtonToBeClickable();
        ResListPage resListPage = loginPage.clickSearchButton();
        resListPage.waitForResListToAppear();
        Assert.assertTrue(resListPage.isURLCorrect("https://www.10bis.co.il/next/en/restaurants/search/"));
        resListPage.selectPreference("10bis Delivery");
        Assert.assertTrue(resListPage.validateTenbisLogoExists());
    }

    @Test(priority = 2)
    @Description("go to res list and filter by cuisine.")
    public void filterByCuisines() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.addAnAddressToSearchBar("Tel-Aviv, rotchild 22");
        loginPage.selectFirstOptionFromDDL();
        loginPage.waitForSearchButtonToBeClickable();
        ResListPage resListPage = loginPage.clickSearchButton();
        resListPage.waitForResListToAppear();
        Assert.assertTrue(resListPage.isURLCorrect("https://www.10bis.co.il/next/en/restaurants/search/"));
        resListPage.clickExpandCuisinesList();
        resListPage.waitForFiltersToBeClickable();
        resListPage.selectCuisine("Humus");
        resListPage.waitForNewResListToLoad();
        Assert.assertTrue(resListPage.validateCuisineFiltered());
    }

    @Test(priority = 3)
    @Description("go to res list and sort list by.")
    public void sortListBy() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.addAnAddressToSearchBar("Tel-Aviv, rotchild 22");
        loginPage.selectFirstOptionFromDDL();
        loginPage.waitForSearchButtonToBeClickable();
        ResListPage resListPage = loginPage.clickSearchButton();
        resListPage.waitForResListToAppear();
        Assert.assertTrue(resListPage.isURLCorrect("https://www.10bis.co.il/next/en/restaurants/search/"));
        resListPage.clickOpenSortListByButton();
        Assert.assertTrue(resListPage.isRecommendedSelectedByDefault());
        resListPage.waitForFiltersToBeClickable();
        resListPage.selectSortListByOption("Rating");
        resListPage.validateRatingIsSelected();
        Assert.assertTrue(resListPage.validateRatingIsSelected());
    }
}
