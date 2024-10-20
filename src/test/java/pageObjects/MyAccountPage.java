package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{

	public MyAccountPage(WebDriver driver) 
	{
		super(driver);	
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement myAccountHeading;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement logoutLink;	// Added in step-6
	
	public boolean isMyAccountPageExists() 
	{
		try
		{
			return myAccountHeading.isDisplayed();
		}
		catch(Exception e)
		{
			e.getMessage();
			return false;
		}
	}

	public void clickLogOut() 
	{
		logoutLink.click();
	}
}
