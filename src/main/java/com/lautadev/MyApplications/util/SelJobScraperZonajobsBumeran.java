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

public class SelJobScraperZonajobsBumeran {
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
    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "D:/escritorio/project MyApplications/webDriver/msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        //zonajobs
        //driver.get("https://www.zonajobs.com.ar/empleos/facilitador-a-de-mantenimiento-electronico-coca-cola-femsa-de-argentina-2124716.html");

        //bumeran
        driver.get("https://www.bumeran.com.ar/empleos/asistente-comercial-jr-1116471859.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Default value for data of job application
        String defaultValue = "No disponible";

        try {
            //Company
            String companyName = getElementText(driver, wait, By.cssSelector("div.sc-cHRTLU.dHXXut"), defaultValue);

            //Location
            String location = getLocation(driver,defaultValue);

            //Modality
            String modality = getModality(driver,defaultValue);

            //Position
            String position = getElementText(driver, wait, By.cssSelector("h1.sc-lbihag"), defaultValue);

            //Work Schedule
            String workSchedule = getWorkSchedule(driver, defaultValue);

            //Description
            String description = getElementText(driver, wait, By.cssSelector("div.sc-jYIdPM.kTYgTC"), defaultValue);

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

    private static String getLocation(WebDriver driver, String defaultValue) {
        try {
            List<WebElement> paragraphs = driver.findElements(By.cssSelector("div.sc-htoDjs.boUuTC h2.sc-gAjsMU.coOCyB"));
            if (!paragraphs.isEmpty()) {
                WebElement locationElement = paragraphs.get(1);
                return locationElement != null ? locationElement.getText() : defaultValue;
            }
            return defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private static String getModality(WebDriver driver, String defaultValue) {
        try {
            List<WebElement> paragraphs = driver.findElements(By.cssSelector("div.sc-htoDjs.iMtavu h2.sc-gAjsMU.coOCyB"));
            if (!paragraphs.isEmpty()) {
                WebElement modalityElement = paragraphs.get(0);
                return modalityElement != null ? modalityElement.getText() : defaultValue;
            }
            return defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private static String getWorkSchedule(WebDriver driver, String defaultValue) {
        try {
            List<WebElement> paragraphs = driver.findElements(By.cssSelector("div.sc-htoDjs.iMtavu h2.sc-gAjsMU.coOCyB"));
            if (!paragraphs.isEmpty()) {
                WebElement workScheduleElement = paragraphs.get(2);
                return workScheduleElement != null ? workScheduleElement.getText() : defaultValue;
            }
            return defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

}
