package ecommerce.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ecommerce.listner.ITestListnerClass;
import ecommerce.main.UserAuthentication;
import ecommerce.project.base.BaseClass;

@Listeners(ITestListnerClass.class)
public class UserAuthenticationTest extends BaseClass{

	public UserAuthenticationTest() {
		super();
	}
	
	UserAuthentication userAuthentication;
	
	@DataProvider(name="signupData")
	public Object[][] getSignUpDate(){
		return new Object[][] {
			{"Ashu", "singhashu01@gmail.com", "Male", "Ashu","singhashu01@gmail.com", "Ashu@123","17",
				2, "1997", "Ashu", "Singh", "AE", "A 21", "Dabua Colony", "India", "HR", "FBD","121001","7042579843"},
			{"Jiya", "singhashu02@gmail.com", "Female", "Jiya","singhashu02@gmail.com", "Jiya@123","8",
			05, "2020", "Jiya", "Singh", "AI", "A 21", "Dabua Colony", "India", "HR", "FBD","121001","9810134486"},
			{"Tulika", "singhashu03@gmail.com", "Female", "Tulika","singhashu03@gmail.com", "Tulika@123","27",
				10, "2000", "Tulika", "Singh", "KP", "A 21", "Dabua Colony", "India", "HR", "FBD","121001","9810134496"}
		};
	}

	@Test(dataProvider = "signupData")
	public void signUpUser(String Name, String Email, String gender, String Uname, String UEmail, String UPwd, String Udays,
			int UMonths, String UYears, String fname, String lname, String Company, String add1, String add2,
			String Cntry, String State, String City, String ZipCode, String PhoneNum) {
		userAuthentication = new UserAuthentication();
		userAuthentication.signUP(Name, Email, gender, Uname, UEmail, UPwd, Udays, UMonths, UYears, fname, lname,Company, add1, add2,
				 Cntry, State, City, ZipCode, PhoneNum);
	}
}
