package TestSample;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TesteCalculadora {

    private AppiumDriver driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();

    public void iniciar() throws MalformedURLException {
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator");
        capabilities.setCapability(MobileCapabilityType.UDID,"emulator-5554");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        capabilities.setCapability("appPackage","com.google.android.calculator");
        capabilities.setCapability("appActivity","com.android.calculator2.Calculator");

        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/"), capabilities);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void TestCalculadora_Somar() throws MalformedURLException {
        iniciar();

        driver.findElementById("com.google.android.calculator:id/digit_7").click();
        driver.findElementByAccessibilityId("plus").click();
        driver.findElementById("com.google.android.calculator:id/digit_8").click();
        driver.findElementByAccessibilityId("equals").click();
        Assert.assertEquals("15",driver.findElementById("com.google.android.calculator:id/result_final").getText());

    }
}
