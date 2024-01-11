import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


public class UpdateProfile_positive {
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
    public void loginAndNavigateToUpdateProfile() {
        // Log in to the application
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement signInBtn = driver.findElement(By.xpath("//button[@type='submit']"));

        usernameField.sendKeys("your_username");  // replace with your valid username
        passwordField.sendKeys("your_password");  // replace with your valid password
        signInBtn.click();

        // Navigate to Update Profile page
        WebElement profilePicture = driver.findElement(By.xpath("//div[@class='profile-pic']"));
        profilePicture.click();

        WebElement myProfileOption = driver.findElement(By.xpath("//a[text()='My profile']"));
        myProfileOption.click();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void updateProfileWithValidData() {
        // Update profile fields with valid data
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        WebElement emailField = driver.findElement(By.id("emailAddress"));
        WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));

        // Modify the fields with valid data
        firstNameField.clear();
        firstNameField.sendKeys("NewFirstName");

        lastNameField.clear();
        lastNameField.sendKeys("NewLastName");

        emailField.clear();
        emailField.sendKeys("newemail@example.com");

        // Click on the Save button
        saveButton.click();

        // Validate success message is displayed
        WebElement successMessage = driver.findElement(By.xpath("//div[@class='sufee-alert alert with-close alert-success alert-dismissible fade show']"));
        assertTrue(successMessage.isDisplayed());

        // Validate that the updated data is saved
        // You may need to navigate back to the profile page or refresh the page to check the updated data
        // The validation depends on the structure of your application and how it displays updated user information
    }

}
