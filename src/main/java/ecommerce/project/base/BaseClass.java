package ecommerce.project.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import ecommerce.utils.ExcelUtility;

public class BaseClass {

	protected static WebDriver driver;
	private static Properties prop;
	protected static ExtentReports extentReports;
	protected static ExtentTest extentTest;
	public static Logger log = LogManager.getLogger(BaseClass.class);

	public BaseClass() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"D:\\SeleniumPractice\\ecommerce.project\\src\\main\\resource\\config.properties");
			prop.load(fis);
		} catch (IOException e) {
			log.info("There is Some issue while fetching info from properties file.");
			e.printStackTrace();
		}
	}

	public static String getScreenshots(WebDriver driver, String Screenshots) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String dateTime = new SimpleDateFormat("yy-MM-dd-hh-mm-ss").format(new Date());
		String destPath = "D:\\SeleniumPractice\\ecommerce.project\\Screenshots\\" + Screenshots + "_" + dateTime
				+ ".png";
		File destfile = new File(destPath);
		FileUtils.copyFile(srcFile, destfile);
		log.info("Screenshots Captured for failed Test Case..");
		return destPath;
	}

	@BeforeTest
	public static ExtentReports setReports() {
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(
				"D:\\SeleniumPractice\\ecommerce.project\\ProjectExtentReports\\ECommerceReport.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Author", "Ashutosh Singh");
		extentReports.setSystemInfo("Project Name", "Ecommerce");
		extentReports.setSystemInfo("Tools", "Selenium Based Project");
		extentReports.setSystemInfo("Project-Based", "WebBased");

		extentSparkReporter.config().setDocumentTitle("ECommerce Project");
		extentSparkReporter.config().setReportName("\\ECommerceProjectReport.html");
		extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setEncoding("UTF8");

		return extentReports;
	}

	@BeforeMethod
	public static void userAuthenticate() {
		String BrowserName = prop.getProperty("BrowserName");
		if (BrowserName.equals("chrome")) {
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--disable-notifications--");
			opt.addArguments("--disable-infobars");
			opt.addArguments("--disable-extensions");
			opt.addArguments("--disable-popup-blocking");
			opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
			driver = new ChromeDriver();

			log.info("Chrome Browser Initialized");
		} else if (BrowserName.equals("firefox")) {
			driver = new FirefoxDriver();
			log.info("Firefox Driver Initialized");
		} else if (BrowserName.equals("Edge")) {
			driver = new EdgeDriver();
			log.info("Edge Browser Initialized");
		} else {
			System.out.println("No Browser Found");
			log.info("No Browser Found for test case executions");
		}
		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
	}

	@AfterMethod
	public void setStatus(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.SUCCESS) {
			ExcelUtility.setStatus("Pass", 2, "D:\\SeleniumPractice\\ecommerce.project\\TestCases\\TestCase.xlsx");
			log.debug("Erro");
		}
		else if(result.getStatus()==ITestResult.FAILURE) {
			ExcelUtility.setStatus("Fail", 2, "D:\\SeleniumPractice\\ecommerce.project\\TestCases\\TestCase.xlsx");
		}
	}
	
	@AfterTest
	public void flushTest() {
		extentReports.flush();
		driver.quit();
		log.info("Test Cases Executions are Done..");
	}
}
