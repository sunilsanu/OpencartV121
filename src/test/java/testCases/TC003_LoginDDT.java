package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* data is valid - login successful  - test pass - passed
   data is valid --- loging failed   --- test failed
   
   data is invalid -login successful - test faild - logout
   data is invalid ---- login is failed --- test pass
*/


public class TC003_LoginDDT extends BaseClass
{

@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="Datadriven") //gettong data provider from different class
public void verify_loginDDT(String email, String pwd, String exp)
{
    logger.info("********** Started TC003_LoginDDT *******************");

    try {
        // Home Page
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickLogin();

        // Login Page
        LoginPage lp = new LoginPage(driver);
        lp.setEmail(email);       // FIXED
        lp.setPassword(pwd);      // FIXED
        lp.clickLogin();

        // My Account Page
        MyAccountPage macp = new MyAccountPage(driver);
        boolean targetpage = macp.isMyAccountPageExists();

        if (exp.equalsIgnoreCase("Valid")) {
            if (targetpage) {
                macp.clicklogout();
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }
        }
        else if (exp.equalsIgnoreCase("Invalid")) {
            if (targetpage) {
                macp.clicklogout();
                Assert.fail();
            } else {
                Assert.assertTrue(true);
            }
        }

    } catch (Exception e) {
        Assert.fail("Test failed due to exception: " + e.getMessage());
    }

    logger.info("*********** Finished TC003_LoginDDT ******************");
}
}
