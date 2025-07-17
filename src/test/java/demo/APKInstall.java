package demo;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class APKInstall {
	
	
	 public static void main(String[] args) {
	        try {
	            UiAutomator2Options options = new UiAutomator2Options();
	            options.setDeviceName("SmallPhone"); // Must match connected/emulator device name
	            options.setPlatformName("Android");
	            options.setAutomationName("UiAutomator2");
	            options.setApp("C:\\Users\\diwak\\Downloads\\apk files\\ApiDemos-debug.apk"); // Adjust path to your .apk

	            URL appiumServerURL = new URL("http://127.0.0.1:4723/");

	            AndroidDriver driver = new AndroidDriver(appiumServerURL, options);
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	            System.out.println("App launched successfully!");

	            driver.quit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
