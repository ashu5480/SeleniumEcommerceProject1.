package ecommerce.project.base;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {

	protected static WebDriver driver;
	private static Properties prop;
	private static ExtentReports extentReports;
	private static ExtentTest extentTest;
	public static Logger log = LogManager.getLogger(BaseClass.class);

	public BaseClass() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("D:\\SeleniumPractice\\ecommerce.project\\src\\main\\resource\\config.properties");
			prop.load(fis);
		} catch (IOException e) {
			log.info("There is Some issue while fetching info from properties file.");
			e.printStackTrace();
		}
	}
	
	public static String getScreenshots(WebDriver driver, String Screenshots) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String dateTime = new SimpleDateFormat().format("yy-MM-dd-hh-mm-ss").formatted(new Date());
		String destFile = prop.getProperty("user.dir")+"/Screenshots"+dateTime+".png";
		File destfile = new File(destFile);
		FileUtils.copyDirectory(srcFile, destfile);
		return destFile;
	}

	@BeforeTest
	public static ExtentReports setReports() {
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(
				"D:\\SeleniumPractice\\ecommerce.project\\ProjectExtentReports");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Author", "Ashutosh Singh");
		extentReports.setSystemInfo("Project Name", "Ecommerce");
		extentReports.setSystemInfo("Tools", "Selenium Based Project");
		extentReports.setSystemInfo("Project-Based", "WebBased");

		extentSparkReporter.config().setDocumentTitle("ECommerce Project");
		extentSparkReporter.config().setReportName("ECommerceProjectReport");
		extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setEncoding("UTF8");

		return extentReports;
	}

	@BeforeMethod
	public static void userAuthenticate() {
		String BrowserName = prop.getProperty("BrowserName");
		if (BrowserName.equals("chrome")) {
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20000));
	}

	@AfterMethod
	public void afterResultSetReport(ITestResult result) throws IOException { 
		if(result.getStatus()== ITestResult.SUCCESS) {
			extentTest = extentReports.createTest(result.getName());
			extentTest.log(Status.PASS, MarkupHelper.createLabel("This Test Case is Passed :"+result.getName()+" ",ExtentColor.GREEN));
			log.info("This Test Case is passed "+result.getName());
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			extentTest = extentReports.createTest(result.getName());
			extentTest.log(Status.SKIP,MarkupHelper.createLabel("This Test Case is Skipped : "+result.getName()+" ",ExtentColor.YELLOW));
			log.info("This Test Case is Skipped : "+result.getName());
		}
		else if(result.getStatus()==ITestResult.FAILURE) {
			extentTest = extentReports.createTest(result.getName());
			extentTest.log(Status.FAIL,MarkupHelper.createLabel("This Test Case is failed : "+result.getName()+" ",ExtentColor.RED));
			String Screenshots = getScreenshots(driver, result.getName());
			extentTest.fail(result.getThrowable());
			extentTest.addScreenCaptureFromBase64String(Screenshots);
			log.info("Failure Screenshots are Captured");
			}
	}
	
	@AfterTest
	public void flushTest() {
		extentReports.flush();
		driver.close();
		log.info("Test Cases Executions are Done..");
	}
}
