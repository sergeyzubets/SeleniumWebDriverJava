package com.coherentsolutions.driver;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

@Slf4j
public class Utils {

    public static void makeScreenshot(WebDriver driver) {
        String dateTime = String.valueOf(Calendar.getInstance().getTime()).replace(" ", "_").replace(":", "_");
        String filesName = dateTime + ".png";
        Path screenshotFolderLocation = Paths.get("src", "test", "resources", "screenshots", filesName);

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(screenshotFolderLocation.toUri());

        try {
            FileUtils.copyFile(screenshotFile, destinationFile);
            log.info("Screenshot is created and saved to screenshots folder.");
        } catch (IOException e) {
            log.error("Error during copying screenshot to destination folder.");
            throw new RuntimeException(e);
        }
    }
}