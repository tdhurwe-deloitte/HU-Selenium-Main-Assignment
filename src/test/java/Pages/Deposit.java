package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class Deposit {
    WebDriver driver;
    By customerLoginButton = By.xpath("//*[text()='Customer Login']");
    By selectUser = By.xpath("//*[@id='userSelect']");
    By loginButton = By.xpath("//*[text()='Login']");
    By depositButton = By.xpath("//button[@ng-class='btnClass2']");
    By amountInput = By.xpath("//input[@ng-model='amount']");
    By submitButton = By.xpath("//button[text()='Deposit']");
    By originalBalance = By.xpath("//strong[@class='ng-binding'][2]");

    public Deposit(WebDriver driver){
        this.driver = driver;
    }
    public void setCustomerLoginButton(){
        driver.findElement(customerLoginButton).click();
    }
    public void setSelectUser(){
        Select select = new Select(driver.findElement(selectUser));
        select.selectByIndex(6);
    }
    public void setLoginButton(){
        driver.findElement(loginButton).click();
    }

    public void makeDeposit() throws Exception{
        String originalAmount = driver.findElement(originalBalance).getText();
        driver.findElement(depositButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(amountInput).sendKeys("10000");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(submitButton).click();
//        return originalAmount;
    }

}
