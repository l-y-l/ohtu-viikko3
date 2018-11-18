package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {
        static WebDriver driver = new HtmlUnitDriver();
        static Random r = new Random();

    public static void main(String[] args) {
        driver.get("http://localhost:4567");
        
        System.out.println(driver.getPageSource());
        sleep(2);
        
        navigateByLink("register new user");
        attemptRegistration("aa", "bad", "bad");
        navigateByLink("back to home");
        
        navigateByLink("login");
        attemptLogin("pekka", "akkep");
        navigateByLink("logout");
        
        navigateByLink("login");
        attemptLogin("pekka", "pekka");
        sleep(1);
        attemptLogin("akkep", "akkep");
        navigateByLink("back to home");
        
        navigateByLink("register new user");
        attemptRegistration("arto" + r.nextInt(1000000), "otra", "otra");
        navigateByLink("continue to application mainpage");
        navigateByLink("logout");
        
        driver.quit();
    }
    
    private static void navigateByLink(String linkName) {
        WebElement element = driver.findElement(By.linkText(linkName));
        element.click();
        
        System.out.println(driver.getPageSource());
        sleep(2);
    }
    
    private static void attemptLogin(String username, String password) {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        
        System.out.println(driver.getPageSource());
        sleep(2);
        
        element.submit();
        
        System.out.println(driver.getPageSource());
        sleep(2);
    }
    
    private static void attemptRegistration(String username, String password, String pwConfirm) {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(pwConfirm);
        element = driver.findElement(By.name("signup"));
        
        System.out.println(driver.getPageSource());
        sleep(2);
        
        element.submit();
        
        System.out.println(driver.getPageSource());
        sleep(2);
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
