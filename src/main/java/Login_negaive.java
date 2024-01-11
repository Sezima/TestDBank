
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
        import org.openqa.selenium.*;
        import org.openqa.selenium.chrome.*;
        import org.openqa.selenium.support.ui.*;
        import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Login_negaive {

    static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://dbank-qa.wedevx.co/bank/login");
    }

    @BeforeEach
    public void waitForElement() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void incorrectUsernameTest() {
        // Fill in incorrect username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("incorrectUsername");

        // Fill in a valid password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("validPassword");

        // Click on the  remember me
        WebElement rememberMe = driver.findElement(By.id("remember-me"));
        rememberMe.click();

        WebElement signInBtn = driver.findElement(By.id("submit"));
        signInBtn.click();

        // Validate that the login is not successful and check the error message
        WebElement errorAlert = driver.findElement(By.xpath("//div[@class='sufee-alert alert with-close alert-danger alert-dismissible fade show']"));
        String actualErrorMessage = errorAlert.getText().trim();
        String expectedErrorMessage = "Error Invalid credentials or access not granted due to user account status or an existing user session.\n" +
                "×";
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void incorrectPasswordTest() {
        // Fill in a valid username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("validUsername");

        // Fill in incorrect password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("incorrectPassword");

        // Click on the sign-in button
        WebElement signInBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        signInBtn.click();

        // Validate that the login is not successful and check the error message
        WebElement errorAlert = driver.findElement(By.xpath("//div[@class='sufee-alert alert with-close alert-danger alert-dismissible fade show']"));
        String actualErrorMessage = errorAlert.getText().trim();
        String expectedErrorMessage = "Error Invalid credentials or access not granted due to user account status or an existing user session.\n" +
                "×";
        assertEquals(expectedErrorMessage, actualErrorMessage);

    }

    // Additional negative test cases can be added here

}

