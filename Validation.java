package co.eaguayo.com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

public class Validation {

	//Created some global variables so if anypath changes, I only need to update the variables, not each and every test case
	protected static WebDriver driver;
	By linkedInLink = By.xpath("//a[@href='https://www.linkedin.com/in/enrique-aguayo-98142a58/']");
	By gitHubLink = By.xpath("//a[@class='fab fa-github-alt']");
	By gitHubScreenName = By.xpath("//span[text()='AZWildcats2014']");
	String homeURL = "https://www.eaguayo.com";
	

	//Initializing Mozilla Driver, and maximizing windo
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Enriqu\\Downloads\\WebDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.get("http://www.eaguayo.com");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	//Validating the Title is correct
	@Test(priority=0)
	public void validateTitle () {		
		Assert.assertTrue(driver.getTitle().contains("Enrique Aguayo - For Hire"));
	}
	
	//Validating that the Header contains Software Tester for the lead in the classname 'lead'
	@Test(priority=1)
	public void validateH1 () {
		Assert.assertTrue(driver.findElement(By.className("lead")).getText().contains("Software Tester"));
	}
	
	//Validating using XPATH that my Jumbotron intro contains the word 'Welcome'
	@Test(priority=2)//tests using XPATH location
	public void validateJumbotron () {
		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='display-4']")).getText().contains("Welcome"));
	}
	
	//Validates that clicking on the LinkedIn icon/link will actually take you to LinkedIn, if signed in, you should be able to see my profile
	@Test(priority=3, groups="links")
	public void validateLinkedIn() {
		driver.findElement(linkedInLink).click();
		Assert.assertTrue(driver.getCurrentUrl().contains("https://www.linkedin.com/"));
	}
	
	//Validates that when you click on GitHub, it waits for the page to load the element to find my Screen name, and validates that it equals AZWildcats2014
	@Test(priority=4, groups="links")
	public void validateGitHub() {
		driver.get(homeURL);
		driver.findElement(gitHubLink).click();
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement gitHub;
		
		gitHub = wait.until(ExpectedConditions.visibilityOfElementLocated(gitHubScreenName));
		
		String gitHubUserNameActual = driver.findElement(gitHubScreenName).getText();
		System.out.println("" + gitHubUserNameActual);
		
		Assert.assertTrue(gitHubUserNameActual.equals("AZWildcats2014"));
		
	}
	
	//Shuts off the driver and closes the window
	@AfterTest
	public void shutDown() {
		driver.quit();
	}

}
