package com.mystore.utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenShot {

	public static void capture(WebDriver driver) {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        LocalDateTime dateTime = LocalDateTime.now();
        String path = System.getProperty("user.dir") + "\\ScreenShots\\"+dateTime+"_SS.png";
        File destFile = new File(path);

        try {
            // Save the screenshot to the desired location
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved to: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
