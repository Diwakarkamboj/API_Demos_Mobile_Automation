package demo;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class DragAndDrop {

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
			WebElement viewBtn = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]"));
			viewBtn.click();
			driver.findElements(By.id("android:id/text1")).get(7).click();
			
			WebElement source = driver.findElement(By.xpath("//android.view.View[@resource-id=\"io.appium.android.apis:id/drag_dot_1\"]"));
			WebElement target = driver.findElement(By.xpath("//android.view.View[@resource-id=\"io.appium.android.apis:id/drag_dot_2\"]"));

			//calling our method
			Point sourceElementCenter = getCenter(source);
			Point targetElementCenter = getCenter(target);
			
			//Pointer input class to create a sequence of actions
			//that move the pointer to the center of the element
			//press down on the element and then release the element
			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					//move finger to the starting position
					.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sourceElementCenter))
					//finger coming down to contact with screen
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(588)))
					//move finger to the end position
					.addAction(finger1.createPointerMove(Duration.ofMillis(588), PointerInput.Origin.viewport(), targetElementCenter))
					//lift the finger up
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			//perform sequence of actions
			driver.perform(Arrays.asList(sequence));
			Thread.sleep(1000);
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static Point getCenter(WebElement element) {
		//get location of the element
		Point location = element.getLocation();
		
		//get dimension (height & width of the element)
		Dimension size = element.getSize();
		//get center point
		Point center = new Point(location.x + size.width/2, location.y + size.height/2);
		
		return center;
	}

}
