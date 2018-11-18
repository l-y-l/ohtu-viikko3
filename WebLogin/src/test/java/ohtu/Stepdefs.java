package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        navigateByLink("login");
    }
    
    @Given("^command new user is selected$")
    public void new_user_selected() throws Throwable {
        driver.get(baseUrl);
        navigateByLink("register new user");
    }
    
    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void new_user_successfully_created(String username, String password) throws Throwable {
        driver.get(baseUrl);
        navigateByLink("register new user");
        registerWith(username, password, password);
        pageHasContent("Welcome to Ohtu Application!");
        pageHasContent("info for newbie");
        navigateByLink("continue to application mainpage");
        navigateByLink("logout");
    }
    
    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
    public void user_with_username_and_password_is_tried_to_be_created(String username, String password) throws Throwable {
        driver.get(baseUrl);
        navigateByLink("register new user");
        registerWith(username, password, password);
        pageHasContent("Create username and give password");
        navigateByLink("back to home");
    }
    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^nonexistent username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void nonexistent_username_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void register_valid_username_and_password_and_confirmation_are_given(String username, String password) throws Throwable {
        registerWith(username, password, password);
    }
    
    @When("^a username too short \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void register_username_too_short_and_password_and_confirmation_are_given(String username, String password) throws Throwable {
        registerWith(username, password, password);
    }
    
    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" too short and matching password confirmation are entered$")
    public void register_valid_username_and_password_too_short_and_matching_confirmation_are_given(String username, String password) throws Throwable {
        registerWith(username, password, password);
    }
    
    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and incorrect password confirmation \"([^\"]*)\" are entered$")
    public void register_valid_username_and_password_and_incorrect_confirmation_are_given(String username, String password, String passwordConfirm) throws Throwable {
        registerWith(username, password, passwordConfirm);
    }
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }
    
    @Then("^a new user is created$")
    public void new_user_is_created() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
        pageHasContent("info for newbie");
    }
    
    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void new_user_is_not_created_and_error_message_is_given(String error) throws Throwable {
        pageHasContent("Create username and give password");
        pageHasContent(error);
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
    
    private void navigateByLink(String linkName) {
        WebElement element = driver.findElement(By.linkText(linkName));
        element.click();
    }
    
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }
        
    private void registerWith(String username, String password, String passwordConfirm) {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirm);
        element = driver.findElement(By.name("signup"));
        element.submit();
    } 
}
