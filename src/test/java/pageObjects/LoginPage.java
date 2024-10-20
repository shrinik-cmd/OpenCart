package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	

	@FindBy(xpath="//input[@id='input-email']") WebElement txtMailAddress;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtpwd;
	@FindBy(xpath="//input[@value='Login']") WebElement loginBtn;
	
	public void Email(String email) 
	{
		txtMailAddress.sendKeys(email);
	}
	
	public void Password(String pwd) 
	{
		txtpwd.sendKeys(pwd);
	}
	
	public void clickLoginBtn() 
	{
		loginBtn.click();;
	}

}
