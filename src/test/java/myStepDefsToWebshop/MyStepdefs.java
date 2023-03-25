package myStepDefsToWebshop;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.openqa.selenium.*;

import org.openqa.selenium.chrome.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {
    private WebDriver driver;
    @Test
    @Before

    public void setUp() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        driver.get("https://demowebshop.tricentis.com/register"); //进入网站
        driver.manage().window().maximize();

        Thread.sleep(2000);  // Let the user actually see something! 等待5秒

    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Given("I have ticked the {string}")
    public void iHaveTickedTheGender(String gender) {
        if(gender.equals("Male")){
            driver.findElement(By.cssSelector("#gender-male")).click();
        } else if (gender.equals("Female")) {
            driver.findElement(By.cssSelector("#gender-female")).click();
        }

    }

    @And("I have entered my {string}")
    public void iHaveEnteredMyFirstName(String firstname) {
        driver.findElement(By.cssSelector("#FirstName")).sendKeys(firstname);
    }


    @And("I also have entered {string}")
    public void iAlsoHaveEntered(String name) {
        if(name.equals("last")) {
            driver.findElement(By.cssSelector("#LastName")).sendKeys(name);
        } else if (name.equals("first")) {
            driver.findElement(By.cssSelector("#FirstName")).sendKeys(name);
        }


    }

    @And("I have typed in {string}")
    public void iHaveTypedIn(String email) {
        driver.findElement(By.cssSelector("#Email")).sendKeys(email);
    }

    @And("I enter a {string}")
    public void iEnterA(String password) {
        driver.findElement(By.cssSelector("#Password")).sendKeys(password);
    }

    @And("I confirm {string}")
    public void iConfirm(String password) {
        driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys(password);
    }

    @When("I click the Register {string}")
    public void iClickTheRegister(String button) {
        driver.findElement(By.cssSelector("#register-button")).click();
    }

    @Then("My registration will be {string}")
    public void myRegistrationWillBe(String result) throws InterruptedException{

        if(result.equals("completed")){
            String actual= (driver.findElement(By.className("result")).getText()); //getText 从元素中获取text
            String expected="Your registration completed";
            driver.quit();
            assertEquals(expected,actual);
            Thread.sleep(3000);
        } else if (result.equals("rejected")) {
            String actual= (driver.findElement(By.cssSelector(".validation-summary-errors li")).getText()); //getText 从元素中获取text
            String expected="The specified email already exists";
            driver.quit();
            assertEquals(expected,actual);
            Thread.sleep(3000);
        }

    }

    @Then("I will not be {string}")
    public void iWillNotBe(String register) {
        if(register.equals("noFirstName")) {
            String actual = (driver.findElement(By.cssSelector("div.form-fields > div:nth-child(2) > span.field-validation-error > span")).getText()); //getText 从元素中获取text
            String expected = "First name is required.";
            assertEquals(expected, actual);
        } else if (register.equals("noLastName")) {
            String actual = (driver.findElement(By.cssSelector(" div.form-fields > div:nth-child(3) > span.field-validation-error > span")).getText()); //getText 从元素中获取text
            String expected = "Last name is required.";
            assertEquals(expected, actual);
        }


    }
}
//getAttribute