package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseTest;

public class TC001_AccountRegistrationTest extends BaseTest
{

	@Test(groups={"Regression","Master"})
	public void verifyAccountRegistration() 
	{	
		logs.info("***Starting TC001_AccountRegistrationTest***");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logs.info("Clicked on MyAccount link");
		hp.clickRegister();
		logs.info("Clicked on Register link");
		
		AccountRegistrationPage ar=new AccountRegistrationPage(driver);
		logs.info("Providing Customer Details...");
		ar.setFirstName(randomString().toUpperCase());
		
		ar.setLastName(randomString().toUpperCase());
		
		ar.setEmail(randomString()+"@gmail.com");
		
		ar.setTelephone(randomNumber());
		
		String pwd=randomAlphaNumeric();
		ar.setPassword(pwd);
		ar.setConfirmPassword(pwd);
		
		ar.setPrivacyPolicy();
		
		ar.clickContinue();
		
		logs.info("Validating expected Message");
		String confirmmsg=ar.getConfirmMsg();
		
		if(confirmmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logs.error("Test Failed due to wrong title");
			logs.debug("Debug Logs.....");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confirmmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			Assert.fail();
			e.getMessage();
		}
		logs.info("***Finished TC001_AccountRegistrationTest***");
	}
	
}
