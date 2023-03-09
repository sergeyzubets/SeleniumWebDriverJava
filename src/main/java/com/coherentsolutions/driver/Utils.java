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

import static com.coherentsolutions.utilities.Constants.TestErrorMessage.SCREENSHOT_CREATED;
import static com.coherentsolutions.utilities.Constants.TestErrorMessage.SCREENSHOT_FAILURE;

@Slf4j
public class Utils {

    public static byte[] makeScreenshot(WebDriver driver) {
        String dateTime = String.valueOf(Calendar.getInstance().getTime()).replace(" ", "_").replace(":", "_");
        String filesName = dateTime + ".png";
        Path screenshotFolderLocation = Paths.get("src", "test", "resources", "screenshots", filesName);

        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(screenshotFolderLocation.toUri());

        try {
            FileUtils.copyFile(screenshotFile, destinationFile);
            log.info(SCREENSHOT_CREATED);
        } catch (IOException e) {
            log.error(SCREENSHOT_FAILURE);
        }
        return screenshot;
    }
}