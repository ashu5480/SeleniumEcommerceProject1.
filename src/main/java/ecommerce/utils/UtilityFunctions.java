package ecommerce.utils;
import java.time.Duration;
import java.util.ArrayList;

import java.util.List;

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
	
	public static void selectionFromDropDownViaContainsVisibleText(WebElement elm, String value) {
		Select sel = new Select(elm);
		sel.selectByContainsVisibleText(value);
	}
	
	public static void selectionFromDropDownViaValue(WebElement elm, String value) {
		Select sel = new Select(elm);
		sel.selectByContainsVisibleText(value);
	}
	public static void selectionFromDropDownViaIndex(WebElement elm, int index) {
		Select sel = new Select(elm);
		sel.selectByIndex(index);
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
}
