package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    // ===== Constructor =====
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ===== WebElements =====
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement lnkMyaccount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement lnkRegister;
    
    @FindBy(linkText = "Login")
    WebElement linkLogin;              //login link added in 5th step

    // ===== Action Methods =====
    public void clickMyAccount() 
    {
        lnkMyaccount.click();
    }

    public void clickRegister() 
    {
        lnkRegister.click();
    }
    
    public void clickLogin()
    {
    	linkLogin.click();
    }
    
    



}


