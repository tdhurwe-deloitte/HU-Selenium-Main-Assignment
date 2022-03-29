package Tests;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.*;


public class CustomerTest extends BaseClass{
    AddCustomers addCustomers;
    OpenAccount openAccount;
    Deposit deposit;
    Withdraw withdraw;
    Transactions transaction;
    XSSFRow row = null;
    XSSFCell cell = null;
    String firstName = null;
    String lastName = null;
    String postCode = null;
    @Test(priority = 1)
    public void validateAddCustomers() throws Exception{
        XSSFSheet sheet = excelSheetLoader(0);
        addCustomers = new AddCustomers(driver);
//        System.out.println(sheet.getLastRowNum()+"\n"+sheet.getRow(1).getCell(0).getStringCellValue());
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    if (j == 0) {
                        firstName = cell.getStringCellValue();
                    }
                    if (j == 1) {
                        lastName = cell.getStringCellValue();
                    }
                    if (j == 2) {
                        postCode = cell.getStringCellValue();
                    }
                }
//                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                addCustomers.clickManagerLogin();
//                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                Thread.sleep(2000);
                addCustomers.clickAddCustomer();
//                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                Thread.sleep(2000);
                addCustomers.addFname(firstName);
                addCustomers.addLname(lastName);
                addCustomers.addCode(postCode);
                addCustomers.submit();
//                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                Thread.sleep(2000);

                if (isAlertPresent()) {
                    logger.info("Customer added successfully");
                } else {
                    logger.error("Error occurred in validateAddCustomer function");
                }
            }
    }

    @Test(priority = 2)
    public void validateOpenAccount() throws Exception{
        openAccount = new OpenAccount(driver);
        openAccount.clickOpenAccount();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(2000);
        openAccount.setSelectCustomer();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(2000);
        openAccount.setSelectCurrency();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(2000);
        takeScreenShot("Account creation");
        openAccount.submit();
        Thread.sleep(2000);
        if (isAlertPresent()){
            logger.info("Account created successfully");
        }
        else{
            logger.error("Error in validateOpenAccount function");
            takeScreenShot("Opening account error");
        }
    }

    @Test(priority = 3)
    public void backHome() throws Exception{
        driver.findElement(By.xpath("//*[text()='Home']")).click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(2000);
        String homeURL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";
        String actualURL = driver.getCurrentUrl();
        if (homeURL.equals(actualURL)) {
            logger.info("Successfully returned to home page");
        }
        else{
            logger.error("Error occurred in backHome method");
        }

        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void verifyDeposit() throws Exception{
        deposit = new Deposit(driver);
        Thread.sleep(2000);
        deposit.setCustomerLoginButton();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(2000);
        deposit.setSelectUser();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(2000);
        deposit.setLoginButton();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(2000);
        String originalBal = driver.findElement(By.xpath("//strong[@class='ng-binding'][2]")).getText();
        int originalBalance = Integer.parseInt(originalBal);
        deposit.makeDeposit();
        Thread.sleep(2000);
        String actualMessage = driver.findElement(By.xpath("//span[text()='Deposit Successful']")).getText();
        String expectedMessage = "Deposit Successful";
        Assert.assertEquals(actualMessage, expectedMessage, "Unsuccessful Deposit");
        String updatedBal = driver.findElement(By.xpath("//strong[@class='ng-binding'][2]")).getText();
        int updatedBalance = Integer.parseInt(updatedBal);
        if (updatedBalance == originalBalance + 10000){
            logger.info("Balance updated");
            takeScreenShot("Balance update");
        }
        else{
            logger.error("Error in verify deposit method");
        }
    }

    @Test(priority = 5)
    public void verifyWithdraw1() throws Exception{
        withdraw = new Withdraw(driver);
        withdraw.clickWithdraw();
        String originalBal = driver.findElement(By.xpath("//strong[@class='ng-binding'][2]")).getText();
        int originalBalance = Integer.parseInt(originalBal);
        Thread.sleep(2000);
        withdraw.enterAmount();
        Thread.sleep(2000);
        withdraw.submit();
        Thread.sleep(2000);
        String updatedBal = driver.findElement(By.xpath("//strong[@class='ng-binding'][2]")).getText();
        int updatedBalance = Integer.parseInt(updatedBal);
        if (updatedBalance == originalBalance - 6000){
            logger.info("Withdraw successful");
            takeScreenShot("valid transaction");
        }
        else{
            logger.error("Error in verifyWithdraw method");
            takeScreenShot("Error : transaction");
        }
    }

    @Test(priority = 5)
    public void verifyWithdraw2() throws Exception{
        withdraw = new Withdraw(driver);
        String originalBal = driver.findElement(By.xpath("//strong[@class='ng-binding'][2]")).getText();
        int originalBalance = Integer.parseInt(originalBal);
        Thread.sleep(2000);
        withdraw.enterAmount();
        Thread.sleep(2000);
        withdraw.submit();
        Thread.sleep(2000);
        String updatedBal = driver.findElement(By.xpath("//strong[@class='ng-binding'][2]")).getText();
        int updatedBalance = Integer.parseInt(updatedBal);
        if (originalBalance == updatedBalance){
            logger.info("Unsuccessful transaction");
            takeScreenShot("Invalid transaction");
        }
        else{
            logger.error("Error : Debited amount is more than the available amount");
            takeScreenShot("Error");
        }
    }

    @Test(priority = 6)
    public void verifyTransaction() throws Exception{
        transaction = new Transactions(driver);
        transaction.clickTransaction();
        Thread.sleep(2000);
        Boolean verify = transaction.verifyTransaction();
        if (verify){
            logger.info("Successful transactions");
            takeScreenShot("Transactions");
        }
        else {
            logger.error("Error in verifyTransaction method");
        }
    }
}
