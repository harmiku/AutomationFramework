package com.hl.rough;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.hl.base.TestBase;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Calendar;
import java.util.Date;
public class VerifyMisc extends TestBase {
	public static String convertToPhoneFormat(String phoneNumber) {
		return "(" + phoneNumber.substring(0, 3) + ") " + phoneNumber.substring(3, 6) + " - "
				+ phoneNumber.substring(6, 10);
	}

	public static String convertToSSNFormat(String SSNNumber) {
		return SSNNumber.substring(0, 3) + "-" + SSNNumber.substring(3, 5) + "-" + SSNNumber.substring(5, 9);
	}

	public static String invertWithoutStringBuffer(String inputString) {
		String outputString = "";
//		for(int i=0;i<inputString.length();i++) {
//			outputString = inputString.charAt(i)+outputString;
//		}
		for (int i = inputString.length() - 1; i >= 0; i--) {
			outputString = outputString + inputString.charAt(i);
		}
		return outputString;
	}

	public static String invertWithStringBuffer(String inputString) {
		String outputString = "";
		StringBuffer sb = new StringBuffer(inputString);
		sb.reverse();
		outputString = sb.toString();
		return outputString;
	}

	public static String removeDuplicateSpaces(String inputString) {
		String outputString = "";
		/*
		 * StringTokenizer stz = new StringTokenizer(inputString, " "); StringBuffer sb
		 * = new StringBuffer(); while (stz.hasMoreElements()) {
		 * sb.append(stz.nextElement()).append(" "); } outputString = sb.toString();
		 */
		String[] originalArray = inputString.split("\\s+");
		for (String item : originalArray) {
			outputString = outputString + " " + item;
		}
		return outputString.trim();
	}

	public static int[] getTwoMaxNumbers(int[] intList) {
		int[] maxTwoNumbers = { 0, 0 };
		int maxOne = 0;
		int maxTwo = 0;
		for (int n : intList) {
			if (maxOne < n) {
				maxTwo = maxOne;
				maxOne = n;
			} else {
				if (maxTwo < n) {
					maxTwo = n;
				}
			}
		}
		maxTwoNumbers[0] = maxOne;
		maxTwoNumbers[1] = maxTwo;
		return maxTwoNumbers;
	}

	public static boolean isPrime(int n) {
		if (n <= 1)
			return false;
		for (int i = 2; i <= n / 2; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static long reverseNumber(long inputNumber) {
		long outputNumber = 0;
		while (inputNumber != 0) {
			outputNumber = outputNumber * 10 + inputNumber % 10;
			inputNumber = inputNumber / 10;
		}
		return outputNumber;
	}

	public static Set<String> findDuplicates(List<String> originalList) {
		Set<String> duplicateList = new HashSet<String>();
		Set<String> newList = new HashSet<String>();
		for (String item : originalList) {
			if (!newList.add(item)) {
				duplicateList.add(item);
			}
		}
		return duplicateList;
	}

	public static Set<Character> findDuplicateChars(String inputString) {
		Set<Character> duplicateList = new HashSet<Character>();
		Set<Character> newList = new HashSet<Character>();
		for (int i = 0; i < inputString.length(); i++) {
			if (!newList.add(inputString.charAt(i))) {
				duplicateList.add(inputString.charAt(i));
			}
		}
		return duplicateList;
	}

	public static String reverseString(String inputString) {
		String outputString = "";
		String[] originalArray = inputString.split("\\s+");
		for (String item:originalArray) {
			outputString=item+" "+outputString;
		}
		return outputString;
	}

	public static void main(String[] args) {
		if (driver == null) {
			PropertyConfigurator.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j.properties");
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			browser = (System.getenv("browser") != null && !System.getenv("browser").isEmpty())
					? System.getenv("browser")
					: config.getProperty("browser");
			if (browser.contains("chrome")) {
				ChromeOptions options = new ChromeOptions();
				WebDriverManager.chromedriver().setup();
				if (browser.contains("headless")) {
					options.addArguments("headless");
				}
				driver = new ChromeDriver(options);
				log.debug("Chrome Lunched.");
			}

			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
		}
		driver.get("https://hlfoundationsapp-test.hl.com/app/home");
		
		isElementVisible(By.xpath("//*[text()='Uchian, Harmik']"),"webElementName");
		
		WebElement webElement;
		webElement = getWebElement(By.xpath("//*[text()='Uchian, Harmik']"),"webElementName");

	}
	
	public static void main1(String[] args) throws InterruptedException {
		
		Date currentTime = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		System.out.println("UTC time: " + sdf.format(currentTime));

		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		System.out.println(sdf.format(dt));
		//System.out.println(reverseString("this   is   a     test string"));
		
		//System.out.println(removeDuplicateSpaces("67     7  5 5 5 55555555   455"));

		// System.out.println(reverseString(""));

		// System.out.println(findDuplicateChars("6771133255555555555552334455"));

		// List<String> ll=new ArrayList<String>();
		// ll.add("1");ll.add("2");ll.add("3");ll.add("3");
		// System.out.println(findDuplicates(ll));

		// int[] testList = {1,2,3,4,5,6,7,8,9};
		// System.out.println(getTwoMaxNumbers(testList)[1]);

		/*
		 * System.setProperty("browser", "chrome");
		 * //System.setProperty("webdriver.chrome.driver",
		 * System.getProperty("user.dir")+
		 * "\\src\\test\\resources\\executables\\chromedriver.exe");
		 * WebDriverManager.chromedriver().setup(); WebDriver driver = new
		 * ChromeDriver(); driver.get("http://google.com/"); Thread.sleep(5000);
		 * WebElement searchBox= driver.findElement(By.name("q"));
		 * searchBox.sendKeys("ChromeDriver"); searchBox.submit(); Thread.sleep(5000);
		 * driver.quit(); ArrayList<WebElement> ar = new ArrayList<WebElement>();
		 * Scanner console = new Scanner(System.in); int w = console.nextInt();
		 */
	}

}
