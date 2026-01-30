package ecommerce.main;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ecommerce.project.base.BaseClass;
import ecommerce.utils.AssertionMessage;
import ecommerce.utils.UtilityFunctions;

public class UserAuthentication extends BaseClass {

	public UserAuthentication() {
		PageFactory.initElements(driver, this);
	}

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(xpath = "//a[@href='/login']")
	WebElement loginSingupBtn;

	@FindBy(xpath = "//form[@action='/login']//input[2]")
	WebElement loginEmail;

	@FindBy(xpath = "//form[@action='/login']//input[3]")
	WebElement loginPwd;

	@FindBy(xpath = "//form[@action='/login']//button")
	WebElement loginBtn;

	@FindBy(xpath = "//form[@action='/signup']//input[2]")
	WebElement signupName;

	@FindBy(xpath = "//form[@action='/signup']//input[3]")
	WebElement signupEmail;

	@FindBy(xpath = "//form[@action='/signup']//button")
	WebElement signUPBtn;

	@FindBy(xpath = "//b[text()='Enter Account Information']")
	WebElement signUPMessage1;

	@FindBy(xpath = "//p[text()='Email Address already exist!']")
	WebElement signUPAlreadyExistMessage;

	@FindBy(id = "id_gender1")
	WebElement gender1;

	@FindBy(id = "id_gender2")
	WebElement gender2;

	@FindBy(id = "name")
	WebElement uname;

	@FindBy(id = "email")
	WebElement email;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "days")
	WebElement days;

	@FindBy(id = "months")
	WebElement months;

	@FindBy(id = "years")
	WebElement years;

	@FindBy(id = "newsletter")
	WebElement newsletter;

	@FindBy(id = "optin")
	WebElement optin;

	@FindBy(id = "first_name")
	WebElement first_name;

	@FindBy(id = "last_name")
	WebElement last_name;

	@FindBy(id = "company")
	WebElement company;

	@FindBy(id = "address1")
	WebElement address1;

	@FindBy(id = "address2")
	WebElement address2;

	@FindBy(id = "country")
	WebElement country;

	@FindBy(id = "state")
	WebElement state;

	@FindBy(id = "city")
	WebElement city;

	@FindBy(id = "zipcode")
	WebElement zipcode;

	@FindBy(id = "mobile_number")
	WebElement mobile_number;

	@FindBy(xpath = "//form[@action='/signup']//button")
	WebElement createAccount;

	@FindBy(xpath = "//b[text()='Account Created!']")
	WebElement successMessage1;

	public void signUP(String Name, String Email, String gender, String Uname, String UEmail, String UPwd, String Udays,
			int UMonths, String UYears, String fname, String lname, String Company, String add1, String add2,
			String Cntry, String State, String City, String ZipCode, String PhoneNum) {
		
		js.executeScript("arguments[0].click()", loginSingupBtn);
		wait.until(ExpectedConditions.visibilityOf(loginEmail));
		signupName.sendKeys(Name);
		log.info("Name Enterd");
		js.executeScript("arguments[0].value=arguments[1];", signupEmail, Email);
		log.info("Email Enterd");
		js.executeScript("arguments[0].click()", signUPBtn);

		wait.until(ExpectedConditions.visibilityOf(signUPMessage1));
		log.info("Now Provide all Details");
		String ActualMessage = signUPMessage1.getText();
		String ExpectedMessage = AssertionMessage.signupAssertionMessage;

		Assert.assertEquals(ActualMessage, ExpectedMessage, "Message Mismatch");
		if(gender.equalsIgnoreCase("Male")) {
			gender1.click();
		}
		else {
			gender2.click();
		}
		js.executeScript("arguments[0].value=arguments[1];", uname, Uname);
		js.executeScript("arguments[0].value=arguments[1];", email, UEmail);
		js.executeScript("arguments[0].value=arguments[1];", password, UPwd);

		UtilityFunctions.selectionFromDropDownViaContainsVisibleText(days, Udays);
		UtilityFunctions.selectionFromDropDownViaIndex(months, UMonths);
		UtilityFunctions.selectionFromDropDownViaVisibleText(years, UYears);

		newsletter.click();
		optin.click();

		first_name.sendKeys(fname);
		last_name.sendKeys(lname);
		company.sendKeys(Company);
		address1.sendKeys(add1);
		address2.sendKeys(add2);
		UtilityFunctions.selectionFromDropDownViaContainsVisibleText(country, Cntry);
		state.sendKeys(State);
		city.sendKeys(City);
		zipcode.sendKeys(ZipCode);
		mobile_number.sendKeys(PhoneNum);

		js.executeScript("arguments[0].click()",createAccount);

		String Actual_Msg = successMessage1.getText();
		String Expected_Message = AssertionMessage.UserAccountCreationMsg;

		Assert.assertEquals(Actual_Msg, Expected_Message);
		log.info("Account Created");
		System.out.println("Account Created");
	}

}
