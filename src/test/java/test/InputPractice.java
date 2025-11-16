package test;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class InputPractice 
{
	
	//WebDriver driver;
	
	 private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	 public WebDriver driver()
	 {
		 return driver.get();
	 }
	
	@BeforeMethod
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver());
		driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver().manage().window().maximize();
		driver().get("https://letcode.in/test");
	}
	@AfterMethod
	public void tearDown()
	{
		driver().quit();
		 driver.remove();
	}
	
	@Test(priority=1,groups= {"sanity"})
	public void prcticeInputFields()
	{
		driver().findElement(By.xpath("//a[normalize-space()='Edit']")).click();
		driver().findElement(By.id("fullName")).sendKeys("Test data");
		driver().findElement(By.id("join")).sendKeys(" + Text Appended Newly",Keys.TAB);
		String textpresentinfeild = driver().findElement(By.id("getMe")).getAttribute("value");
		Assert.assertEquals(textpresentinfeild, "ortonikc");
		WebElement clearText = driver().findElement(By.id("clearMe"));
		clearText.clear();
		WebElement disabledElement = driver().findElement(By.id("noEdit"));
		Assert.assertFalse(disabledElement.isEnabled(),"Field is not disabled");

	}
	@Test(priority=2,groups= {"sanity"})
	public void practicebuttons()
	{
		driver().findElement(By.xpath("//a[normalize-space()='Click']")).click();
		driver().findElement(By.id("home")).click();
		driver().navigate().back();
		WebElement positionButton = driver().findElement(By.id("position"));
		int X = positionButton.getLocation().getX();
		int Y = positionButton.getLocation().getY();
		System.out.println("X-Coordinate : "+X+"Y-Coordinate :"+Y);
		WebElement SizeButon = driver().findElement(By.id("property"));
		int Height = SizeButon.getSize().getHeight();
		int Width = SizeButon.getSize().getWidth();
		System.out.println(Height+"  "+Width);
		boolean disableButton = driver().findElement(By.id("isDisabled")).isEnabled();
		Assert.assertFalse(disableButton,"Button is Enabled");
		
	}
	@Test(priority=3,groups= {"sanity","regression"})
	public void practiceAlerts()
	{
		driver().findElement(By.xpath("//a[normalize-space()='Dialog']")).click();
		//Simple Alert
		
		driver().findElement(By.id("accept")).click();
		Alert alerrt = driver().switchTo().alert();
		alerrt.accept();
		//Dismiss the Alert & print the alert text
		driver().findElement(By.id("confirm")).click();
		alerrt = driver().switchTo().alert();
		System.out.println("Alert text : "+alerrt.getText());
		alerrt.dismiss();
		//Type your name & accept
		driver().findElement(By.id("prompt")).click();
		alerrt = driver().switchTo().alert();
		alerrt.sendKeys("Entering My Name : Sangamesh");
		alerrt.accept();
		
		
	}
	@Test(priority=4,groups= {"sanity","regression","ui"})
	public void practiceFrames()
	{
		driver().findElement(By.xpath("//a[normalize-space()='Inner HTML']")).click();
		WebElement firstFrameId = driver().findElement(By.id("firstFr"));
		driver().switchTo().frame(firstFrameId);
		driver().findElement(By.name("fname")).sendKeys("Sangamesh");
		driver().findElement(By.name("lname")).sendKeys("Sangamesh lase");
		driver().switchTo().defaultContent();
		driver().switchTo().frame(firstFrameId);
		WebElement innerframess = driver().findElement(By.xpath("//iframe[@src='innerframe']"));
		driver().switchTo().frame(innerframess);
		driver().findElement(By.name("email")).sendKeys("Test@gmail.com");
		
	}
	@Test(priority=5,groups= {"sanity","ui"})
	public void practiceRadioButtons() throws InterruptedException
	{
		driver().findElement(By.xpath("//a[normalize-space()='Toggle']")).click();
		//Select any one
		WebElement FirstradioButton = driver().findElement(By.id("yes"));
		if(!FirstradioButton.isSelected())
		{
			FirstradioButton.click();
		}
		Assert.assertTrue(FirstradioButton.isSelected(),"Radio button is not selected!");
		//Same if want select based on label 
		//Selecting option which is equal to Yes 
		
//		List<WebElement> options = driver.findElements(By.xpath("//input[@name='answer']"));
//		for(WebElement option:options)
//		{
//			if(option.getAttribute("value").trim().equals("Yes"))
//			{
//				option.click();
//				Assert.assertTrue(option.isSelected());
//				break;
//			}
//		}
	
		
	}
	


}
