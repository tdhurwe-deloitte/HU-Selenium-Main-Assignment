package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddCustomers {
    WebDriver driver;
    By managerLogin = By.xpath("//button[text()='Bank Manager Login']");
    By addCustomer = By.xpath("//button[@ng-class='btnClass1']");
    By firstName = By.xpath("//div[@class='form-group'][1]//input");
    By lastName = By.xpath("//div[@class='form-group'][2]//input");
    By postCode = By.xpath("//div[@class='form-group'][3]//input");
    By submitButton = By.xpath("//button[text()='Add Customer']");

    public AddCustomers(WebDriver driver){
        this.driver = driver;
    }

    public void clickManagerLogin(){
        driver.findElement(managerLogin).click();
    }

    public void clickAddCustomer(){
        driver.findElement(addCustomer).click();
    }

    public void addFname(String fname){
        driver.findElement(firstName).sendKeys(fname);
    }

    public void addLname(String lname){
        driver.findElement(lastName).sendKeys(lname);
    }

    public void addCode(String code){
        driver.findElement(postCode).sendKeys(code);
    }

    public void submit(){
        driver.findElement(submitButton).click();
    }

}
