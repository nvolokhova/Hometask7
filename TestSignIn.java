package dataProviderExcel;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.Constant;
import utility.ExcelUtils;

public class TestSignIn {

private static WebDriver driver;

// Here we are calling the Data Provider object with its Name
@Test(dataProvider = "Authentication")
public void DataProvidertest(String sUsername, String sPassword) throws Exception {

// Click Sign In link
driver.findElement(By.id("tab_signin")).click();
Reporter.log("Step 1 - Click Sign In link - PASSED");

// Click Sign In with Email link
driver.findElement(By.cssSelector("#tabs-func > li.dropdown > div > a.si_menuitem_last")).click();
Reporter.log("Step 2 - Click Sign In with Email link - PASSED");

// Enter email address
driver.findElement(By.cssSelector("#screen-login > form > input:nth-child(2)")).sendKeys(sUsername);
Reporter.log("Step 3 - Enter email address - PASSED");

// Enter password
driver.findElement(By.cssSelector("#screen-login > form > input:nth-child(4)")).sendKeys(sPassword);
Reporter.log("Step 4 - Enter password - PASSED");

// Click SignIn button
driver.findElement(By.cssSelector("#screen-login > form > div:nth-child(6) > input")).click();
Reporter.log("Step 5 - Click SignIn button - PASSED");

// Logout
WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tab_account"))).click();

try {
driver.findElement(By.cssSelector("#tabs-func > li.dropdown > div > a.si_menuitem_last")).click();
Reporter.log("Step 7 - Click Logout - PASSED");
} catch(org.openqa.selenium.TimeoutException e) {
	Reporter.log("Step 7 - Click Logout - took longer than 5 seconds - FAIL");}
}

@DataProvider(name = "Authentication")
public static Object[][] credentials() throws Exception {

    Object[][] testObjArray = ExcelUtils.getTableArray(Constant.Path_TestData+Constant.File_TestData, Constant.Sheet_TestData);
    
    return (testObjArray);
}

@BeforeMethod
public void beforeMethod() {
	// Create a new instance of Chrome driver
	driver = new ChromeDriver();
	Reporter.log("Create a new instance of Chrome driver");

	// We are waiting for the page to load for up to 5 seconds and then throw an exception
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	
	// Launch the Website
    try {
	driver.navigate().to(Constant.URL);
		Reporter.log("Launch https://www.worldtimebuddy.com website - PASS");
    } catch (org.openqa.selenium.TimeoutException e) {
    	Reporter.log("Launch of https://www.worldtimebuddy.com website took longer than 5 seconds - FAIL");
    }
    
	Reporter.log("Launch " + Constant.URL + " website");

}

@AfterMethod
public void afterMethod() {
	  driver.close();
}

}