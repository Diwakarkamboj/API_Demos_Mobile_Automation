package demo;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AutomateDialer {

	public static void main(String[] args) {

		try {
			UiAutomator2Options options = new UiAutomator2Options();
			options.setDeviceName("SmallPhone"); // Must match connected/emulator device name
			options.setPlatformName("Android");
			options.setAutomationName("UiAutomator2");
			options.setApp("C:\\Users\\diwak\\Downloads\\apk files\\ApiDemos-debug.apk"); // Adjust path to your .apk
			options.setAppPackage("com.google.android.dialer");
			options.setAppActivity("com.google.android.dialer.extensions.GoogleDialtactsActivity");
			
			URL appiumServerURL = new URL("http://127.0.0.1:4723/");

			AndroidDriver driver = new AndroidDriver(appiumServerURL, options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			System.out.println("App launched successfully!");
			
			driver.findElement(By.id("com.google.android.dialer:id/dialpad_fab")).click();
			driver.findElement(By.id("com.google.android.dialer:id/four")).click();
			driver.findElement(By.id("com.google.android.dialer:id/six")).click();
			driver.findElement(By.id("com.google.android.dialer:id/two")).click();
			driver.findElement(By.id("com.google.android.dialer:id/one")).click();
			driver.findElement(By.id("com.google.android.dialer:id/six")).click();
			driver.findElement(By.id("com.google.android.dialer:id/zero")).click();
			driver.findElement(By.id("com.google.android.dialer:id/four")).click();
			driver.findElement(By.id("com.google.android.dialer:id/six")).click();
			driver.findElement(By.id("com.google.android.dialer:id/two")).click();
			driver.findElement(By.id("com.google.android.dialer:id/one")).click();
			driver.findElement(By.id("com.google.android.dialer:id/dialpad_voice_call_button")).click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.findElement(By.id("com.google.android.dialer:id/incall_end_call")).click();
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
