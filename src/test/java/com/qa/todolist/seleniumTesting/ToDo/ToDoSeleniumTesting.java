package com.qa.todolist.seleniumTesting.ToDo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ToDoSeleniumTesting {

	private static RemoteWebDriver driver;
	private static WebElement targ;
	private final String URL = "http://localhost:8080/ToDo.html";
	private static ExtentReports report;
	private static ExtentTest test;

	@BeforeAll
	public static void beforeAll() {

		report = new ExtentReports("target/TestReports/AllToDoReport.html", true);

		// driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

		ChromeOptions config = new ChromeOptions();
		config.setHeadless(!true);// this stops the window pop up if true - this can reduce the time of the
									// runtime test
		driver = new ChromeDriver(config);
		driver.manage().window().setSize(new Dimension(1366, 786)); // sets the size of the window that runs the test
	}
	
	@AfterEach
	public void endTest() {
		report.endTest(test);
	}
	
	@AfterAll
	public static void afterAll() {
		//quit driver
		driver.quit();
		//clean up extent report
		report.flush();
		report.close();
		
	}
	@Test
	public void createToDo() {
		
		test = report.startTest("Create To Do Test");
		
		driver.get(URL);
		//when i click the creaet button
		targ = driver.findElement(By.xpath("/html/body/button[1]"));
		targ.click();
		
		//and enter a title 
		targ = driver.findElement(By.id("toDoTitle"));
		targ.sendKeys("selenium acceptance testing");
		
		targ = driver.findElement(By.xpath("//*[@id=\"createbutton\"]/div/div/div[3]/button"));
		targ.click();
		//then check it has worked 
		targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"createConfirm\"]/p")));
	//	targ = driver.findElement(By.xpath("//*[@id=\"createConfirm\"]/p"));
		String result = targ.getText();
		String expected = "was created, use read all to find the id";
		
		if(result.contains(expected)) {
			test.log(LogStatus.PASS, expected);
		}else {
			test.log(LogStatus.FAIL, "failed creation");
		}
		
		//assertions
		assertThat(result.concat(expected));
	}
	
	@Test
	public void updateToDo() {
		
		//test start
		test = report.startTest("Update To Do Test");
		
		//given
		driver.get(URL);
		
		//when
		targ = driver.findElement(By.xpath("/html/body/button[2]"));
		targ.click();
		//and enter id
		targ = driver.findElement(By.id("UpdateId"));
		targ.sendKeys("1");
		
		//and enter new name
		targ = driver.findElement(By.id("updatedname"));
		targ.sendKeys("Update Selenium");
		
		//and click submit 
		targ = driver.findElement(By.xpath("//*[@id=\"updatebutton\"]/div/div/div[3]/button"));
		targ.click();
		//then
		targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"updateConfirm\"]/p")));
		String result = targ.getText();
		String expected = "was updated, use read all to view changes";
		//test results
		
		if(result.contains(expected)) {
			test.log(LogStatus.PASS, expected);
		}else {
			test.log(LogStatus.FAIL, "Failed update");
		}
		
		//assert
		assertThat(result.contains(expected));
	}
	
	@Test
	public void readToDo() {
		
		//test start
		test = report.startTest("Read Individual To Do Test");
		
		//given
		driver.get(URL);
		
		//when
		targ = driver.findElement(By.xpath("/html/body/button[3]"));
		targ.click();
		//and - enter id of 1
		targ = driver.findElement(By.id("toDoReadId"));
		targ.sendKeys("1");
		//and click submit 
		targ = driver.findElement(By.xpath("//*[@id=\"ReadId\"]/div/div/div[3]/button"));
		targ.click();
		
		//then
		targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"readAllToDo\"]/p[2]")));
		String result = targ.getText();
		String expected = "\"taskId\":1";
		//test results
		if(result.contains(expected)) {
			test.log(LogStatus.PASS, expected);
		}else {
			test.log(LogStatus.FAIL, "Read Failed");
		}
		
		//assert
		assertThat(result.contains(expected));
		
	}
	
	@Test
	public void readAllToDo() {
		
		//test start
		test = report.startTest("Read All To Do Test");
		
		//given
		driver.get(URL);
		//when
		targ = driver.findElement(By.xpath("//*[@id=\"readAllButton\"]"));
		targ.click();
		
		//then
		targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"readAllToDo\"]/p[1]")));
		String result = targ.getText();
		String expected = "1.";
		
		//test results
		if (result.contains(expected)) {
			test.log(LogStatus.PASS, "Passed");
		}else {
			test.log(LogStatus.FAIL, "Failed to Read All");
		}
		
		//assert
		assertThat(result.contains(expected));
	}
	
	@Test
	public void deleteToDo() {
		
		//test start
		test = report.startTest("Delete To Do By Id Test");
		
		//given
		driver.get(URL);
		
		//when
		targ = driver.findElement(By.xpath("/html/body/button[5]"));
		targ.click();
		//and enter id
		targ = driver.findElement(By.id("toDoDeleteId"));
		targ.sendKeys("2");
		//and hit submit 
		targ = driver.findElement(By.xpath("//*[@id=\"deletebutton\"]/div/div/div[3]/button"));
		targ.click();
		
		
		//then
		targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"DeleteToDo\"]/p")));
		String result = targ.getText();
		String expected = "was deleted";
		
		//test results
		if(result.contains(expected)) {
			test.log(LogStatus.PASS, expected);
		}else {
			test.log(LogStatus.FAIL, "Delete Failed");
		}
		
		//assert
		assertThat(result.contains(expected));
	}

}
