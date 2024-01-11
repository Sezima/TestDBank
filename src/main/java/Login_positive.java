

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class Login_positive {

    static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup(); // Use chromedriver instead of firefoxdriver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://dbank-qa.wedevx.co/bank/login");

        WebElement signUpBtn = driver.findElement(By.xpath("//a[text()=' Sign Up Here']"));
        signUpBtn.click();
    }

    @BeforeEach
    public void waitForElement() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


    public void fillPersonalData() {
//        need change all data if i want to check again
        WebElement selectTitle = driver.findElement(By.id("title"));
        selectTitle.click();
        Select select = new Select(selectTitle);
        select.selectByValue("Ms.");
        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("Tester");
        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("TesterLast");
        WebElement genderCheckBox = driver.findElement(By.id("gender"));
        genderCheckBox.click();
        WebElement dob = driver.findElement(By.id("dob"));
        dob.sendKeys("10/02/2004");
        WebElement ssn = driver.findElement(By.id("ssn"));
        ssn.sendKeys("001-11-0442");
        WebElement emailAddressTxt = driver.findElement(By.id("emailAddress"));
        emailAddressTxt.sendKeys(generateMockedEmail("tester001", "tester00101"));
        WebElement pwdTxt = driver.findElement(By.id("password"));
        pwdTxt.sendKeys("Tester$001");
        WebElement pwdConfirmTxt = driver.findElement(By.id("confirmPassword"));
        pwdConfirmTxt.sendKeys("Tester$001");
        WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        submitBtn.click();
    }


    public void addressData() {
//need change all data everytime when we check new register
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement addressTxt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address")));
        addressTxt.sendKeys("123 Jedi Temple");
        WebElement localityTxt = driver.findElement(By.id("locality"));
        localityTxt.sendKeys("course");

        WebElement stateTxt = driver.findElement(By.id("region"));
        stateTxt.sendKeys("region001");

        WebElement postalCode = driver.findElement(By.id("postalCode"));
        postalCode.sendKeys("001001");

        WebElement country = driver.findElement(By.id("country"));
        country.sendKeys("country001");

        WebElement homePhone = driver.findElement(By.id("homePhone"));
        homePhone.sendKeys("1123-1234");

        WebElement mobilePhone = driver.findElement(By.id("mobilePhone"));
        mobilePhone.sendKeys("789-5678");

        WebElement agreeTermCheckBox = driver.findElement(By.id("agree-terms"));
        agreeTermCheckBox.click();

        // Add a delay to make sure all data is populated
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement registerBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        registerBtn.click();
    }


    public void validateAccountCreation() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        // Increase the wait time for the success element
        WebElement successTxt = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Success']")));
        assertEquals("Success", successTxt.getText());
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private String generateMockedEmail(String firstName, String lastName) {
        return firstName.toLowerCase() + "." + lastName.toLowerCase() + "@jedi.com";
    }

    @Test
    public void positiveLoginTest() {
        fillPersonalData(); // Call the method to fill personal data
        addressData(); // Call the method to fill address data

        validateAccountCreation(); // Validate the account creation
    }



}
