package homeworks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumWaitingStrategy {
    public static void main(String[] args) {
        // Khoi tao WebDriver
        WebDriver driver = new ChromeDriver();

        // Step 1: Set implicit wait for loading the web page (30 seconds)
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Step 2: Open the web page
        driver.get("https://dev.mytus.ch/login");

        // Step 3: Set implicit wait for loading all elements (10 seconds)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Step 4: Find and enter the username
        WebElement usernameElement = driver.findElement(By.xpath("//input[@data-test='login_page.username_label']"));
        usernameElement.click();
        usernameElement.sendKeys("mytus-admin@soxes.ch");

        // Step 5: Find and click the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@data-test='login-button']"));
        nextButton.click();

        // Step 6: Find and enter the password
        WebElement passwordElement = driver.findElement(By.xpath("//input[@data-test='login_page.password_label']"));

        // Wait for overlapping element to disappear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='bottom-controls']")));

        // Click using JavaScript Executor
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", passwordElement);
        passwordElement.sendKeys("abcABC!!3");

        // Step 7: Click the "Next" button
        nextButton.click();

        // Step 8: Find and enter the code
        WebElement codeElement = driver.findElement(By.xpath("//input[@data-test='login_page.code_label']"));

        // Wait for overlapping element to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='bottom-controls']")));

        // Explicit wait for the "Code" input field to be visible and clickable
        WebElement codeInputElement = wait.until(ExpectedConditions.elementToBeClickable(codeElement));
        codeInputElement.sendKeys("123456");

        // Step 9: Click the "Login" button using explicit wait
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-test='login-button']")));
        loginButton.click();

        // Close the browser
        // driver.quit();

    }
}