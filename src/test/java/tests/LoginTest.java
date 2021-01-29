package tests;

import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {




    @Test
    public void STG_1120() {
        // Test Data       -- Arrange
        final String url        =  getData("$.env.uat");;
        final String username   =  getData("$.credential.manager.username");
        final String password   =  getData("$.credential.manager.password");
        final String expected   =  getData("$.STG_1120.expected");

        // Test Steps      -- Act
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log In")));
         // WebElement loginLink = driver.findElement(By.linkText("Log In"));
        loginLink.click();

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user")));
        //   WebElement emailInput = driver.findElement(By.id("user"));
        emailInput.sendKeys(username);

        //WebElement passInput = driver.findElement(By.id("password"));
        WebElement passInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passInput.sendKeys(password);

        pause(3);

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login")));
        loginButton.click();


        pause(3);

        WebElement errorBanner = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#error > p")));
        String actual = errorBanner.getText();

        // Test Assertion  -- Assert
        Assert.assertEquals(actual, expected);
    }


}
