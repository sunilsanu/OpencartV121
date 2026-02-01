package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{
	// ===== Constructor =====
	public MyAccountPage(WebDriver driver) 
	{
		super(driver);
		
	}
	
	// ===== WebElements =====
	@FindBy(xpath = "//h2[text()='My Account']")      //my account heading
	WebElement msgHeader;
	
	@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")    //added on step number 6the
	WebElement linkLogout;
	
	
	//=====implementation method=====
	public boolean isMyAccountPageExists()
	{
		try
		{
			return (msgHeader.isDisplayed());
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public void clicklogout()
	{
		linkLogout.click();
	}
	
	

}
