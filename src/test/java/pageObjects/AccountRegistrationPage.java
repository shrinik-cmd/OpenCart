package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
	}
	

	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstName;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastName;
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEMail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtPasswordConfirm;
	@FindBy(xpath="//input[@name='agree']") WebElement chkPolicy;
	@FindBy(xpath="//input[@value='Continue']") WebElement continuebtn;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirm;
	
	
	public void setFirstName(String fname) 
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) 
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email) 
	{
		txtEMail.sendKeys(email);
	}
	
	public void setTelephone(String telephone)
	{
		txtTelephone.sendKeys(telephone);
	}
	
	public void setPassword(String pwd) 
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd) 
	{
		txtPasswordConfirm.sendKeys(pwd);
	}
	
	public void setPrivacyPolicy() 
	{
		chkPolicy.click();
	}
	
	public void clickContinue() 
	{
		//Sol-1
		continuebtn.click();
		
		//Sol-2 
		//btnContinue.submit(); 
		
		//Sol-3
		//Actions act=new Actions (driver); 
		//act.moveToElement(btnContinue).click().perform(); 

		//Sol-4
		//JavascriptExecutor js=(JavascriptExecutor) driver; 
		//js.executeScript("arguments[0].click();", btnContinue); 

		//Sol-5
		//btnContinue.sendKeys (Keys.RETURN); 

		//Sol-6
		//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		//mywait.until (ExpectedConditions.elementToBeClickable (btnContinue)).click();
	}
	
	public String getConfirmMsg() 
	{
		try 
		{
			return (msgConfirm.getText());
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
	}
	
}
