package TestSample;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TesteCalculadora {

    private AppiumDriver driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();

    public void iniciar() throws MalformedURLException {
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability("appPackage", "com.google.android.calculator");
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/"), capabilities);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void tirarPrintTela(String nomeEtapa) throws IOException {
        File evidencia = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String nomeEtapaFormatado = nomeEtapa
                .toLowerCase()
                .replace("ã", "a")
                .replace("ç", "c")
                .replace(" ", "_");
        FileUtils.moveFile(evidencia, new File("target/screenshots/evidencia_" + nomeEtapaFormatado + ".jpg"));
    }

    public void tirarPrintEImprimirNoLog(String passoAtual) throws IOException {
        System.out.println(passoAtual);
        tirarPrintTela(passoAtual);
    }

    @Test
    public void TestCalculadora_Somar() throws IOException {
        iniciar();

        driver.findElementById("com.google.android.calculator:id/digit_7").click();
        tirarPrintEImprimirNoLog("Clicar no botão 7");

        driver.findElementByAccessibilityId("plus").click();
        tirarPrintEImprimirNoLog("Clicar no botão plus");

        driver.findElementById("com.google.android.calculator:id/digit_8").click();
        tirarPrintEImprimirNoLog("Clicar no botão 8");

        driver.findElementByAccessibilityId("equals").click();
        tirarPrintEImprimirNoLog("Clicar no botão equals");

        if (("15").equals(driver.findElementById("com.google.android.calculator:id/result_final").getText())) {
            tirarPrintEImprimirNoLog("Verificou o valor do resultado final com sucesso");
        } else {
            tirarPrintEImprimirNoLog("Verificou que o valor era diferente do esperado");

        }
        Assert.assertEquals("15", driver.findElementById("com.google.android.calculator:id/result_final").getText());
    }
}
