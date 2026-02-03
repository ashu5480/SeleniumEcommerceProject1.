package ecommerce.test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ecommerce.listner.ITestListnerClass;
import ecommerce.main.UserAuthentication;
import ecommerce.project.base.BaseClass;
import ecommerce.utils.ExcelUtility;
import ecommerce.utils.UtilityFunctions;

@Listeners(ITestListnerClass.class)
public class UserAuthenticationTest extends BaseClass{

	public UserAuthenticationTest() {
		super();
	}
	
	UserAuthentication userAuthentication;
	
	@DataProvider(name="signupData")
	public Object[][] getSignUpDate(){
		return new Object[][] {
			{"Ashu", "singhashu80@gmail.com", "Male", "Ashu","singhashu80@gmail.com", "Ashu@123","17",
				"March", "1997", "Ashu", "Singh", "AE", "A 21", "Dabua Colony", "India", "HR", "FBD","121001","7042579843"},
				/*
				 * {"Jiya", "singhashu40@gmail.com", "Female", "Jiya","singhashu40@gmail.com",
				 * "Jiya@123","8", "March", "2020", "Jiya", "Singh", "AI", "A 21",
				 * "Dabua Colony", "India", "HR", "FBD","121001","9810134486"}, {"Tulika",
				 * "singhashu50@gmail.com", "Female", "Tulika","singhashu50@gmail.com",
				 * "Tulika@123","27", "March", "2000", "Tulika", "Singh", "KP", "A 21",
				 * "Dabua Colony", "India", "HR", "FBD","121001","9810134496"}
				 */
		};
	}
	
	@DataProvider(name="loginDataValid")
	public Object[][] getloginDataValid() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		return new Object[][] {
			{"singhashu772@gmail.com", UtilityFunctions.encrypt("Ashu@123")},
			{"singhashu10@gmail.com", UtilityFunctions.encrypt("Ashu@123")}
		};
	}
	
	@DataProvider(name="loginDataInValid")
	public Object[][] getloginDataInvalid() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		return new Object[][] {
			{"singhashu7788@gmail.com", UtilityFunctions.encrypt("Ashu@123")},
			{"singhashu1000@gmail.com", UtilityFunctions.encrypt("Ashu@123")}
		};
	}
	
	/*
	 * @DataProvider(name="getDataFromExcel") public Object[][] excelDataProvider()
	 * throws IOException{ ExcelUtility.testDataInExcel(
	 * "D:\\SeleniumPractice\\ecommerce.project\\test-data\\UserSignUPData.xlsx",
	 * "Sheet1"); return ExcelUtility.getTestData(); }
	 */

	//@Test(dataProvider = "signupData")
	public void signUpUser(String Name, String Email, String gender, String Uname, String UEmail, String UPwd, String Udays,
			String UMonths, String UYears, String fname, String lname, String Company, String add1, String add2,
			String Cntry, String State, String City, String ZipCode, String PhoneNum) throws IOException {
		userAuthentication = new UserAuthentication();
		userAuthentication.signUP(Name, Email, gender, Uname, UEmail, UPwd, Udays, UMonths, UYears, fname, lname,Company, add1, add2,
				 Cntry, State, City, ZipCode, PhoneNum);
	}
	
	//@Test(dataProvider = "loginDataValid")
	public void loginUsersWithValidCreds(String Email, String Pwd) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		userAuthentication = new UserAuthentication();
		userAuthentication.loginUser(Email, Pwd);
		System.out.println("Entered Username is : "+Email+" "+"Entered Password"+Pwd);
	}
	
	@Test(dataProvider = "loginDataInValid")
	public void loginUsersWithInvalidCreds(String Email, String Pwd) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		userAuthentication = new UserAuthentication();
		userAuthentication.loginUser(Email, Pwd);
		System.out.println("Entered Username is : "+Email+" "+"Entered Password"+Pwd);
	}
}
