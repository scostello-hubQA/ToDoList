package com.qa.todolist.seleniumTesting.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
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
@Sql(scripts =  {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class TaskSeleniumTest {
	
	private static RemoteWebDriver driver;
	private static WebElement targ;
	private final String URL = "http://localhost:8080/Tasks.html";
	
	@BeforeAll
	public static void beforeAll() {
		
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
		
		ChromeOptions config = new ChromeOptions();
		config.setHeadless(!true);// this stops the window pop up if true - this can reduce the time of the
									// runtime test
		driver = new ChromeDriver(config);
		driver.manage().window().setSize(new Dimension(1366, 786)); // sets the size of the window that runs the test
	}
	

	@AfterAll
	public static void afterAll() {
		// closes the chrome driver! this is essential
		driver.quit();

	}
	@Disabled
	@Test
	public void createTask() {
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
		//targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\\\"createConfirmed\\\"]/p")));
		
		targ = driver.findElement(By.xpath("//*[@id=\"createConfirmed\"]/p[1]"));
		String result = targ.getText();
		
		//assert equals confirmed 
		
		assertEquals("your task selenium testing was created, use read all to find the id", result);
		
		
	}
	
	@Disabled
	@Test
	public void updateTest() {
		
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
				
				targ = driver.findElement(By.xpath("//*[@id=\"updateConfirmed\"]/p[1]"));
				String result = targ.getText();
				
				//assert equals confirmed 
				
				assertEquals("your task selenium testing was updated, use read all to view changes", result);
		
	}
	
}
