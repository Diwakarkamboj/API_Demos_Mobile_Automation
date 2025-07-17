package demo;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AutomateApp {

	public static void main(String[] args) {

		try {
			UiAutomator2Options options = new UiAutomator2Options();
			options.setDeviceName("SmallPhone"); // Must match connected/emulator device name
			options.setPlatformName("Android");
			options.setAutomationName("UiAutomator2");
			options.setApp("C:\\Users\\diwak\\Downloads\\apk files\\ApiDemos-debug.apk"); // Adjust path to your .apk
			options.setAppPackage("io.appium.android.apis");
			options.setAppActivity("io.appium.android.apis.ApiDemos");
			
			URL appiumServerURL = new URL("http://127.0.0.1:4723/");

			AndroidDriver driver = new AndroidDriver(appiumServerURL, options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			System.out.println("App launched successfully!");
			
			driver.findElement(AppiumBy.androidUIAutomator(
					  "new UiScrollable(new UiSelector().scrollable(true))" +
					  ".scrollIntoView(new UiSelector().text(\"Views\"))"
					));
			
			List<WebElement> listNames = driver.findElements(By.id("android:id/text1"));

			for(WebElement listName : listNames) {
				if(listName.getText().equalsIgnoreCase("Views")) {
					listName.click();
				}
			}
			
			driver.findElements(By.id("android:id/text1")).get(4).click();
			driver.findElements(By.id("android:id/text1")).get(0).click();
			
			driver.findElement(By.id("io.appium.android.apis:id/edit")).sendKeys("Learn");
			driver.findElement(By.id("io.appium.android.apis:id/check1")).click();
			driver.findElement(By.id("io.appium.android.apis:id/radio2")).click();
			driver.findElement(By.id("io.appium.android.apis:id/star")).click();
			driver.findElement(By.id("io.appium.android.apis:id/toggle1")).click();
			driver.findElement(By.id("io.appium.android.apis:id/spinner1")).click();
			
			WebElement planetScreen = driver.findElement(By.id("android:id/contentPanel"));
			Actions action = new Actions(driver);
			action.moveToElement(planetScreen).build().perform();			
			List<WebElement> planetNames = driver.findElements(By.id("android:id/text1"));
			for(WebElement planetName : planetNames) {
				if(planetName.getText().equalsIgnoreCase("Earth")) {
					planetName.click();
					break;
				}
				
			}
			driver.findElement(By.id("io.appium.android.apis:id/button")).click();
			
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
