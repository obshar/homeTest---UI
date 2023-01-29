package tools;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.tenBIsWeb.LoginPage;

import java.time.Duration;

public class BaseTest {

    private WebDriver driver;
    private LoginPage loginPage;

    public void setUp(@Optional("local-ff") String browser) {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = DriverFactory.getInstance().getDriver(browser);
    }

    @BeforeMethod
    public void setUp() {
        setUp("local-chrome");
        loginPage = getLandingPage();
    }

    /**
     * This method is protected to restrict changing the driver instance.
     *
     * @return instance of {@link WebDriver}
     */
    protected WebDriver getDriver() {
        return driver;
    }

    protected LoginPage getLandingPage() {
        getDriver().get(Config.getInstance().getUrl());

        return new LoginPage(getDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
