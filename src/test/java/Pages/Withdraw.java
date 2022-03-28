package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Withdraw {
    WebDriver driver;
    By withDrawButton = By.xpath("//button[@ng-class='btnClass3']");
    By withDrawAmount = By.xpath("//input[@ng-model='amount']");
    By submitButton = By.xpath("//button[text()='Withdraw']");

    public Withdraw(WebDriver driver){
        this.driver = driver;
    }

    public void clickWithdraw(){
        driver.findElement(withDrawButton).click();
    }

    public void enterAmount(){
        driver.findElement(withDrawAmount).sendKeys("6000");
    }

    public void submit(){
        driver.findElement(submitButton).click();
    }
}
