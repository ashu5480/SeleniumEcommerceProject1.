package ecommerce.utils;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecommerce.project.base.BaseClass;

public class UtilityFunctions extends BaseClass{

	public UtilityFunctions() {
		super();
	}
	
	public static final String KEY = prop.getProperty("SECRET_KEY");
	
	public static void selectionFromDropDownViaContainsVisibleText(WebElement elm, String value) {
		Select sel = new Select(elm);
		sel.selectByContainsVisibleText(value);
	}
	
	public static void selectionFromDropDownViaValue(WebElement elm, String value) {
		Select sel = new Select(elm);
		sel.selectByContainsVisibleText(value);
	}
	
	public static void selectionFromDropDownViaVisibleText(WebElement elm, String value) {
		Select sel = new Select(elm);
		sel.selectByVisibleText(value);
	}
	
	public static void deselectionFromDropDownViaContainsVisibleText(WebElement elm, String value) {
		Select sel = new Select(elm);
		sel.deSelectByContainsVisibleText(value);
	}
	
	public static void deselectionFromDropDownViaValue(WebElement elm, String value) {
		Select sel = new Select(elm);
		sel.selectByValue(value);
	}
	public static void deselectionFromDropDownViaIndex(WebElement elm, int index) {
		Select sel = new Select(elm);
		sel.deselectByIndex(index);
	}
	
	public static void deselectionFromDropDownViaVisibleText(WebElement elm, String value) {
		Select sel = new Select(elm);
		sel.deselectByVisibleText(value);
	}

	public static ArrayList<String> getAllOPtions(WebElement elm) {
		Select sel = new Select(elm);
		List<WebElement> arrList = sel.getOptions();
		ArrayList<String> arr = new ArrayList<String>();
		for(WebElement option : arrList) {
			arr.add(option.getText().trim());
		}
		return arr;
	}
	
	public static boolean isElelmentDisplayed(WebDriver driver, WebElement elm, int sec) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
			wait.until(ExpectedConditions.visibilityOf(elm));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String encrypt(String data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
	}
	
	public static String decrypt(String data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
	}
}
