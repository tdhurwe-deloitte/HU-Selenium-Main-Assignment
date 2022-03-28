package Tests;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.lang.Thread;
import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.*;

public class BaseClass {
    public static WebDriver driver;
    public static WebDriverWait wait;
    static Logger logger = LogManager.getLogger(BaseClass.class);
    String url = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";

    @BeforeTest
    public void initialSetup() throws Exception{
        System.setProperty("webdriver.chrome.driver", "C:/Users/tdhurwe/Downloads/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        logger.info("Opening Browser");
        Thread.sleep(2);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        takeScreenShot("Opening browser");
    }

    public XSSFSheet excelSheetLoader(int sheetNum) throws Exception{
        String path = "C:/Users/tdhurwe/Documents/Main_Assignment_Selenium/customer.xlsx";
        FileInputStream file = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(sheetNum);
        return sheet;
    }

    public static void takeScreenShot(String filename) throws Exception{    // need to fix
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yy h-m-s");
        String folderPath = "C:/Users/tdhurwe/Documents/Main_Assignment_Selenium/selenium logs/";
        Date date = new Date();
        FileUtils.copyFile(screenShot,new File(folderPath+filename+"-"+dateFormat.format(date)+".png"));
    }

    public boolean isAlertPresent(){
        try{
            driver.switchTo().alert().accept();
            return true;
        }
        catch (NoAlertPresentException e){
            return false;
        }
    }

    @AfterTest
    public void closeBrowser(){
        driver.close();
    }
}
