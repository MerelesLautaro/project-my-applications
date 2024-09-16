package com.lautadev.MyApplications.util;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SelJobScraperIndeed {
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

    public static void scraperIndeed() {
        System.setProperty("webdriver.edge.driver", "D:/escritorio/project MyApplications/webDriver/msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://ar.indeed.com/jobs?q=Java&start=10&vjk=3c5dcf270be3eed5");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Default value for data of job application
        String defaultValue = "No disponible";

        try {
            //Company
            String companyName = getElementText(driver, wait, By.cssSelector("a.css-1ioi40n.e19afand0"), defaultValue);

            //Location
            String location = getElementText(driver, wait, By.cssSelector("div.css-waniwe.eu4oa1w0 div"), defaultValue);

            //Modality
            // Default value because the page not have info.
            String modality = defaultValue;

            //Position
            String position = getElementText(driver, wait, By.cssSelector("h2.jobsearch-JobInfoHeader-title"), defaultValue);

            //Work Schedule
            String workSchedule = getElementText(driver, wait, By.cssSelector("#salaryInfoAndJobType span"), defaultValue);

            //Description
            String description = getElementText(driver, wait, By.cssSelector("#jobDescriptionText"), defaultValue);

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
}
