/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.phenotips;

import com.mycompany.logger.LogFileWriter;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author sa841
 */
public class ExportToPhenotips {

    /**
     * Facilities of selenium web driver are exploited by this class; Login to
     * the Phenotips web server, export pedigree(json format) to he new patient.
     *
     * @return
     */
    public WebDriver login() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sa841\\Documents\\chromedriver_win32\\chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\sa841\\Documents\\chromedriver_win32\\phantomjs.exe");
        String URL = "http://Admin:admin@192.168.1.63:8080/phenotips123/bin/loginsubmit/XWiki/XWikiLogin/";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1024,768");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //WebDriver driver = new PhantomJS();
        driver.get(URL);
        driver.findElement(By.id("j_username")).sendKeys("Admin");
        driver.findElement(By.id("j_password")).sendKeys("admin");
        driver.findElement(By.className("button")).click();
        return driver;
    }

    public void exportPedigreeToPhenotips(WebDriver driver, String data, String patientPage) throws InterruptedException {
        driver.navigate().to("http://192.168.1.63:8080/phenotips123/bin/edit/data/" + patientPage + "?sheet=PhenoTips.PedigreeEditor#");
        driver.findElement(By.cssSelector("#canvas svg"));
        driver.findElement(By.cssSelector("[class='picture-box'][title='Proband']")).click();
        driver.findElement(By.cssSelector("#action-import.menu-item")).click();
        if (driver.findElement(By.cssSelector("[name='select-type'][value='simpleJSON'][type='radio']")).isEnabled()) {
            driver.findElement(By.cssSelector("[name='select-type'][value='simpleJSON'][type='radio']")).click();
        }
        driver.findElement(By.id("import")).sendKeys(data);
        driver.findElement(By.id("import_button")).click();
        if (isAlertPresented(driver, data, patientPage) == false) {
            driver.findElement(By.cssSelector("#action-save.menu-item")).click();
        }
    }

    public boolean isAlertPresented(WebDriver driver, String data, String patientPage) {
        try {
            Alert alert = driver.switchTo().alert();
            LogFileWriter.log("Can't draw pedigree for " + patientPage + ". Error in the data format: " + data + ". Cause: " + alert.getText());
            alert.accept();
            driver.findElement(By.cssSelector("#action-save.menu-item")).click();
            DeletePatientRecord deletePatientRecord = new DeletePatientRecord();
            deletePatientRecord.sendDelete(patientPage);
            System.out.println("Deleting patient " + patientPage + ".......");
            TimeUnit.SECONDS.sleep(2);
            return true;
        } catch (Exception e) {
            LogFileWriter.log("Data: " + data + " exported successfully to :" + patientPage);
            return false;
        }
    }

    public void logout(WebDriver driver) {
        driver.close();
    }
}
