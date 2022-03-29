package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Transactions {
    WebDriver driver;
    By transactionButton = By.xpath("//button[@ng-class='btnClass1']");
    By amount1 = By.xpath("//tr[@id='anchor0']//td[2]"); // amount = 10000
    By transactionType1 = By.xpath("//tr[@id='anchor0']//td[3]"); //type = credit
    By amount2 = By.xpath("//tr[@id='anchor1']//td[2]"); // amount = 6000
    By transactionType2 = By.xpath("//tr[@id='anchor1']//td[3]"); // type = debit

    public Transactions(WebDriver driver){
        this.driver = driver;
    }
    public void clickTransaction(){
        driver.findElement(transactionButton).click();
    }

    public boolean verifyTransaction(){
        String amt1, amt2, type1, type2;
        amt1 = driver.findElement(amount1).getText();
        amt2 = driver.findElement(amount2).getText();
        type1 = driver.findElement(transactionType1).getText();
        type2 = driver.findElement(transactionType2).getText();
//        System.out.println(amt1+" "+amt2+" "+type1+" "+type2);
        if ((amt1.equals("10000") && type1.equals("Credit")) && (amt2.equals("6000") && type2.equals("Debit"))){
            return true;
        }
        else {
            return false;
        }
    }
}
