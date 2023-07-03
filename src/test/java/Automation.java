
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class Automation {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        String weburl = "https://www.digitalunite.com/practice-webform-learners";
        driver.get(weburl);
        driver.findElement(By.id("onetrust-reject-all-handler")).click();


    }
            @DisplayName("Web form fillup and submiting")
            @Test
            public void submitForm(){
                    // Insert Name
                WebElement userName = driver.findElement(By.id("edit-name"));
                userName.sendKeys("Pranab Paul");

                    // Insert Phone Number
                WebElement phoneNumber = driver.findElement(By.id("edit-number"));
                phoneNumber.sendKeys("01823867450");

                    // Select Age
                driver.findElement(By.cssSelector("label[for = 'edit-agnew-20-30']")).click();

                    // Set Date
                driver.findElement(By.id("edit-date")).click();
                driver.findElement(By.id("edit-date")).sendKeys(Keys.CONTROL + "A" + Keys.BACK_SPACE);
                DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                Date date = new Date();
                String currentDate = dateFormat.format(date);
                driver.findElement(By.id("edit-date")).sendKeys(currentDate, Keys.ENTER);

                    // Inser Email
                WebElement eMail = driver.findElement(By.id("edit-email"));
                eMail.sendKeys("pranabpaul@test.com");

                    // Write on Text Box
                WebElement textarea = driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-"));
                textarea.sendKeys("Hi, I'm Pranab. Recently I completed my BSc in CSE hon's final exam. Now I'm learning SQA Automation at Road To SDET.");

                    // Upload a file
                WebElement uploadElement = driver.findElement(By.id("edit-uploadocument-upload"));
                uploadElement.sendKeys("F:\\Java Practice\\Java SDET\\AssignmentOnJUnit\\src\\test\\resources\\ROADTOSDET.jpg");

                    // Click on Checkbox
                driver.findElement(By.id("edit-age")).click();

                    // Click on Submit Button
                driver.findElement(By.id("edit-submit")).click();

                    // Handle Alert
                driver.switchTo().alert().accept();

                    // Confirming successful submit
                String text = driver.findElement(By.className("page-title")).getText();
                Assertions.assertTrue(text.contains("Thank you for your submission!"));
            }

    @AfterAll
            public void closeDriver(){
            driver.quit();
        }
}
