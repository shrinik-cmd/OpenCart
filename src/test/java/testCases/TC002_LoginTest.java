package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;

public class TC002_LoginTest extends BaseTest
{
	@Test(groups={"Sanity","Master"})
	public void verify_Login() 
	{
		//logs.info("TC002_LoginTest - Test case started");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLoginLink();
		
		LoginPage lp=new LoginPage(driver);
		lp.Email(p.getProperty("email"));
		lp.Password(p.getProperty("password"));
		lp.clickLoginBtn();
		
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean status=myacc.isMyAccountPageExists();
		Assert.assertTrue(status);
		logs.info("TC002_LoginTest - Test case finished");
		}
		catch(Exception e)
		{
			e.getMessage();
			Assert.fail();
		}
	}

}
