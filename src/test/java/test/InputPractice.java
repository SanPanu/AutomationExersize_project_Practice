package test;

import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InputPractice 
{
	WebDriver driver;
	@Test
	public void prcticeInputFields()
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://letcode.in/test");
		driver.findElement(By.xpath("//a[normalize-space()='Edit']")).click();
		driver.findElement(By.id("fullName")).sendKeys("Test data");
		driver.findElement(By.id("join")).sendKeys("Text Appended Newly"+Keys.END);
		String textpresentinfeild = driver.findElement(By.id("getMe")).getAttribute("value");
		Assert.assertEquals(textpresentinfeild, "ortonikc","Text Not matching");
		WebElement clearText = driver.findElement(By.id("clearMe"));
		clearText.clear();
		WebElement disabledElement = driver.findElement(By.id("noEdit"));
		Assert.assertFalse(disabledElement.isEnabled(),"Field is not disabled");
		driver.close();	
	}

}
