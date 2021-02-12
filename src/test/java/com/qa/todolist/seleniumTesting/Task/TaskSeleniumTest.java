package com.qa.todolist.seleniumTesting.Task;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
@Sql(scripts =  {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class TaskSeleniumTest {
	
	private static RemoteWebDriver driver;
	private static WebElement targ;
	private final String URL = "http://localhost:8080/Tasks.html";
	private static ExtentReports report;
	private static ExtentTest test;
	
	//remove a @Disabled tag to run a test and view the updated report in the folder.
	
	
	@BeforeAll
	public static void beforeAll() {
		//extent report
		report = new ExtentReports("target/TestReports/AllTaskReport.html", true); 
		
		
		//driver
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
		// closes the chrome driver! this is essential
		driver.quit();
		//clean up extent reports
		report.flush();
		report.close();

	} 
	//@Disabled
	@Test
	public void createTask() {
		test = report.startTest("Create Task Test");
		
		//given
		driver.get(URL);
		
		//when i click on the create button
		targ = driver.findElement(By.xpath("/html/body/button[1]"));
		targ.click();
		
		//and enter title,priority,date,addinfo,bool,idlink,
		targ = driver.findElement(By.id("act"));
		targ.sendKeys("selenium testing");
		
		targ = driver.findElement(By.id("priority"));
		targ.sendKeys("1");
		
		targ = driver.findElement(By.id("date"));
		targ.sendKeys("22072022");
		
		targ = driver.findElement(By.id("notes"));
		targ.sendKeys("selenium testing");
		
		targ = driver.findElement(By.id("TaskCompletion1"));
		targ.click();
		
		targ = driver.findElement(By.id("addToDoTask"));
		targ.sendKeys("1");
		
		//and click on submit 
		targ = driver.findElement(By.xpath("//*[@id=\"createbutton\"]/div/div/div[3]/button"));
		targ.click();
		
		//then check it has worked 
		targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"createConfirmed\"]/p")));
		
		//targ = driver.findElement(By.xpath("//*[@id=\"createConfirmed\"]/p"));
		String result = targ.getText();
		String expected = "was created, use read all to find the id";
		
		//assert equals confirmed 
		if(result.contains(expected)) {
			test.log(LogStatus.PASS, expected);
		}else {
			test.log(LogStatus.FAIL, "failed creation");
		}
		assertThat(result.contains(expected));
		
		
	}
	
	@Test
	public void updateTest() {
		test = report.startTest("Update Task Test");
		
		//given
				driver.get(URL);
				
				//when i click on the create button
				targ = driver.findElement(By.xpath("/html/body/button[2]"));
				targ.click();
				
				//and enter id, title,priority,date,addinfo,bool,idlink,
				targ = driver.findElement(By.id("updateId"));
				targ.sendKeys("1");
				
				
				targ = driver.findElement(By.id("updateTask"));
				targ.sendKeys("selenium testing");
				
				targ = driver.findElement(By.id("updatePriority"));
				targ.sendKeys("1");
				
				targ = driver.findElement(By.id("updateDate"));
				targ.sendKeys("22072022");
				
				targ = driver.findElement(By.id("updateAddInfo"));
				targ.sendKeys("selenium testing");
				
				targ = driver.findElement(By.id("TaskNotCompleted2"));
				targ.click();
				
				targ = driver.findElement(By.xpath("//*[@id=\"updateToDoTask\"]"));
				targ.sendKeys("1");
				
				//and click on submit
				

				targ = driver.findElement(By.xpath("//*[@id=\"updatebutton\"]/div/div/div[3]/button"));
				targ.click();
				targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"updateConfirmed\"]/p")));
				//targ = driver.findElement(By.xpath("//*[@id=\"updateConfirmed\"]/p"));
				String result = targ.getText();
				String expected = "your task selenium testing was updated, use read all to view changes";
				
				//assert equals confirmed 
				if(result.equals(expected)) {
					test.log(LogStatus.PASS, expected);
				}else {
					test.log(LogStatus.FAIL, "failed update");
				}
				
				assertEquals(expected, result);
		
	}
	
	@Test
	public void readByIdTest() {
		test = report.startTest("Read Individual Task Test");
		
		//given
		driver.get(URL);
		
		//when i click the read button
		targ = driver.findElement(By.xpath("/html/body/button[3]"));
		targ.click();
		
		//and pass in an id 
		targ = driver.findElement(By.id("readbyid"));
		targ.sendKeys("1");
		targ = driver.findElement(By.xpath("//*[@id=\"readbutton\"]/div/div/div[3]/button"));
		targ.click();
		
		
		//then i shoudl see the task appear on screen
		targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"readByID\"]/p")));
		//targ = driver.findElement(By.xpath("//*[@id=\"readByID\"]/p"));
		String result = targ.getText();
		String expected = "{\"taskId\":1,";
		
		//assert
		if(result.contains(expected)) {
			test.log(LogStatus.PASS, expected);
		}else {
			test.log(LogStatus.FAIL, "failed to read");
		}
		
		assertThat(result.contains(expected));
	
	}
	
	@Test
	public void readAllTest() {
		test = report.startTest("Read All Task Test");
		//given
		driver.get(URL);
		
		//when i click the read all button
		targ = driver.findElement(By.id("readAllButton"));
		targ.click();
		
		//then i shouls recieve a list of all the tasks in the system
		targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"printToScreenTask\"]/p[2]")));
		
		String result = targ.getText();
		String expected = "2 | Hoover | need to hoover all of downstairs";
		
		if(result.equals(expected)) {
			test.log(LogStatus.PASS, expected);
		}else {
			test.log(LogStatus.FAIL, "Read All Failed");
		}
		
		//assert this is true 
		assertEquals(expected, result);
	}
	
	@Test
	public void deleteTest() {
		
		test = report.startTest("Delete Task Test");
		
		//given
		driver.get(URL);
		
		//when i click on the delete button
		targ = driver.findElement(By.xpath("/html/body/button[5]"));
		targ.click();
		
		//and pass in an id
		targ = driver.findElement(By.id("taskDeleteId"));
		targ.sendKeys("3");
		//targ.submit();
		//and click submit
		
		
		
		targ = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[3]/button"));
		targ.click();
		
		//then i should recieve a confirm meesssage
		targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"confirmationOfDelete\"]/p")));
		//targ = driver.findElement(By.xpath("//*[@id=\"confirmationOfDelete\"]/p"));
		String result = targ.getText();
		String expected = "was deleted";
		
		//assert this is true 
		if(result.contains(expected)) {
			test.log(LogStatus.PASS, expected);
		}else {
			test.log(LogStatus.FAIL, "failed to delete");
		}
		
		assertThat(result.contains(expected));
		
		
	}
	
}
