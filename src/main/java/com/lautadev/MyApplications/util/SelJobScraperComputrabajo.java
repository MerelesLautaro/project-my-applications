package com.lautadev.MyApplications.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SelJobScraperComputrabajo {
    /*
    Data of Job Application (Model.Application)
        private String company;
        private String location;
        private String modality;
        private String position;
        private String workSchedule;
        private LocalDate dateOfApplication;
        private String description;
    */

        public static void scraperComputrabajo() {
        System.setProperty("webdriver.edge.driver", "D:/escritorio/project MyApplications/webDriver/msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://ar.computrabajo.com/empleos-en-misiones-en-posadas#E1D76D19623D8BE461373E686DCF3405");

        //driver.get("https://ar.computrabajo.com/empleos-en-misiones-en-posadas#8E053F4415BE11CD61373E686DCF3405");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Default value for data of job application
        String defaultValue = "No disponible";

        try {
            //Company
            String companyName = getElementText(driver, wait, By.cssSelector("a.dIB.mr10"), defaultValue);

            //Location
            String location = getElementText(driver, wait, By.cssSelector("p.fs16.mb5"), defaultValue);

            //Modality
            String modality = getModality(driver,defaultValue);

            //Position
            String position = getElementText(driver, wait, By.cssSelector("h2"), defaultValue);

            //Work Schedule
            String workSchedule = getWorkSchedule(driver, defaultValue);

            //Description
            String description = getElementText(driver, wait, By.cssSelector("div.box_detail div.fs16"), defaultValue);

            // Data
            System.out.println("Nombre de la empresa: " + companyName);
            System.out.println("Location: " + location);
            System.out.println("Modality: " + modality);
            System.out.println("Position: " + position);
            System.out.println("Work Schedule: " + workSchedule);
            System.out.println("Description: " + description);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            driver.quit();
        }
    }

    private static String getElementText(WebDriver driver, WebDriverWait wait, By by, String defaultValue) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return element != null ? element.getText() : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private static String getWorkSchedule(WebDriver driver, String defaultValue) {
        try {
            List<WebElement> paragraphs = driver.findElements(By.cssSelector("div.mbB p.dFlex.mb10"));
            if (paragraphs.size() >= 3) {
                WebElement workScheduleElement = paragraphs.get(1);
                return workScheduleElement != null ? workScheduleElement.getText() : defaultValue;
            }
            return defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private static String getModality(WebDriver driver, String defaultValue) {
        try {
            List<WebElement> paragraphs = driver.findElements(By.cssSelector("div.mbB p.dFlex.mb10"));
            if (paragraphs.size() >= 3) {
                WebElement modalityElement = paragraphs.get(2);
                return modalityElement != null ? modalityElement.getText() : defaultValue;
            }
            return defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }
}