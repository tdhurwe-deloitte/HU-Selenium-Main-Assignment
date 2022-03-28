package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class OpenAccount {
    WebDriver driver;
    By openAccountButton = By.xpath("//button[@ng-class='btnClass2']");
    By selectCustomer = By.xpath("//*[@id='userSelect']");
    By selectCurrency = By.xpath("//*[@id='currency']");
    By submitButton = By.xpath("//button[text()='Process']");

    public OpenAccount(WebDriver driver){
        this.driver = driver;
    }

    public void clickOpenAccount(){
        driver.findElement(openAccountButton).click();
    }

    public void setSelectCustomer() throws Exception{
        Select select = new Select(driver.findElement(selectCustomer));
        Thread.sleep(2000);
        select.selectByIndex(6);
    }

    public void setSelectCurrency() throws Exception{
        Select select = new Select(driver.findElement(selectCurrency));
        Random random = new Random();
        int randomNum = random.nextInt(3);
        select.selectByIndex(randomNum);
        Thread.sleep(2000);
    }

    public void submit(){
        driver.findElement(submitButton).click();
    }
}
