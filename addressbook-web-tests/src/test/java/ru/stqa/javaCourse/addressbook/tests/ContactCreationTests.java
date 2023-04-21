package ru.stqa.javaCourse.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;

import static java.lang.String.format;

public class ContactCreationTests {
    private WebDriver wd;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        wd = new ChromeDriver();
        wd.get("http://localhost/addressbook/");
        login("admin", "secret");
    }

    private void login(String username, String password) {
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @Test
    public void testContactCreation() throws Exception {
        initContactCreation();
        ContactData contactData = new ContactData("Vladislav", "Artyomenko", "Moscow", "+79999999999", "javaCourse@test.ru");
        fillContactFormRequiredFields(contactData);
        submitContactCreation();
        returnToHomePage();
        checkerForContactExists(contactData);
        logout();
    }

    private void initContactCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    private void fillContactFormRequiredFields(ContactData contactData) {
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
        wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
        wd.findElement(By.name("mobile")).sendKeys(contactData.getMobilePhone());
        wd.findElement(By.name("email")).sendKeys(contactData.getPrimaryEmail());
    }

    private void submitContactCreation() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    private void returnToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    private void checkerForContactExists(ContactData contactData) {
        wd.findElement(By.xpath(format("//tbody/tr[last()]/td[text()='%s']", contactData.getLastName()))).isDisplayed();
        wd.findElement(By.xpath(format("//tbody/tr[last()]/td[text()='%s']", contactData.getFirstName()))).isDisplayed();
        wd.findElement(By.xpath(format("//tbody/tr[last()]/td[text()='%s']", contactData.getAddress()))).isDisplayed();
        wd.findElement(By.xpath(format("//tbody/tr[last()]/td[text()='%s']", contactData.getMobilePhone()))).isDisplayed();
        wd.findElement(By.xpath(format("//tbody//tr[last()]//a[text()='%s']", contactData.getPrimaryEmail()))).isDisplayed();
    }

    private void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        wd.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
