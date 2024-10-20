package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;
import utilities.DataPro;




public class TC003_LoginTestDDT extends BaseTest
{
	@Test(dataProvider="LoginData",dataProviderClass = DataPro.class, groups="DataDriven")		// Getting DataProvider from different class
	public void verifyLoginDDT(String email1, String pwds1, String exp) throws Exception
	{
		logs.info("TC003_LoginTestDDT - Test case started");
		
		try
		{
		
			//Home Page
			HomePage hp1=new HomePage(driver);
			hp1.clickMyAccount();
			hp1.clickLoginLink();
			
			//Login
			LoginPage lp=new LoginPage(driver);
			lp.Email(email1);
			lp.Password(pwds1);
			lp.clickLoginBtn();
			Thread.sleep(2000);
			//My Account
			MyAccountPage myacc=new MyAccountPage(driver);
			boolean status=myacc.isMyAccountPageExists();
			
			logs.info("TC003_LoginTestDDT - Validation satrted");
			
			/*
			 * Data is valid ---> 	login success --> test pass --> logout
			 * 						login failed --> test fail
			 * 
			 * Data is invalid  --> login success  --> test fail --> logout
			 * 						login failed  --> test pass
			 */
			
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(status==true)
				{
					myacc.clickLogOut();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(status==true)
				{
					myacc.clickLogOut();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}	
		}
		catch(Exception e)
		{
			e.getMessage();
			Assert.fail();
		}
		logs.info("TC003_LoginTestDDT - Test case finished");
	}

}
