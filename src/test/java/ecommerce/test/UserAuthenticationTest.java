package ecommerce.test;

import org.testng.annotations.Test;

import ecommerce.main.UserAuthentication;
import ecommerce.project.base.BaseClass;

public class UserAuthenticationTest extends BaseClass{

	public UserAuthenticationTest() {
		super();
	}
	
	UserAuthentication userAuthentication;
	
	
	
	@Test
	public void signUpUser() {
		userAuthentication = new UserAuthentication();
		userAuthentication.signUP("Ashu", "singhashu774@gmail.com", "Male", "Ashu","singhashu77@gmail.com", "Ashu@123","17",
				2, "1997", "Ashu", "Singh", "AE", "A 21", "Dabua Colony", "India", "HR", "FBD","121001","7042579843");
	}
}
