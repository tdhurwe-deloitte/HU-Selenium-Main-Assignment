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
                Thread.sleep(2000);
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
//                    takeScreenShot(firstName);
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
        openAccount.submit();
        if (isAlertPresent()){
            logger.info("Account created successfully");
        }
        else{
            logger.error("Error in validateOpenAccount function");
        }
    }

    @Test(priority = 3)
    public void backHome() throws Exception{
        driver.findElement(By.xpath("//*[text()='Home']")).click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void verifyDeposit() throws Exception{
        deposit = new Deposit(driver);
        deposit.setCustomerLoginButton();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(2000);
        deposit.setSelectUser();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(2000);
        deposit.setLoginButton();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(2000);
//        String originalBal = deposit.makeDeposit();
        deposit.makeDeposit();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(2000);
        String actualMessage = driver.findElement(By.xpath("//span[text()='Deposit Successful']")).getText();
        String expectedMessage = "Deposit Successful";
        Assert.assertEquals(actualMessage, expectedMessage);
//        String updatedBal = driver.findElement(By.xpath("//strong[@class='ng-binding'][2]")).getText();
        // Need to enter method to check updated amount
    }

    @Test(priority = 5)
    public void verifyWithdraw() throws Exception{
        withdraw = new Withdraw(driver);
        withdraw.clickWithdraw();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(2000);
        withdraw.enterAmount();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(2000);
        withdraw.submit();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(2000);
    }
}
