package ru.stqa.javaCourse.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.javaCourse.addressbook.model.ContactData;

import static java.lang.String.format;

public class ContactHelper {
    WebDriver wd;

    public ContactHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void initContactCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void fillContactFormRequiredFields(ContactData contactData) {
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
        wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
        wd.findElement(By.name("mobile")).sendKeys(contactData.getMobilePhone());
        wd.findElement(By.name("email")).sendKeys(contactData.getPrimaryEmail());
    }

    public void submitContactCreation() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void checkerForContactExists(ContactData contactData) {
        wd.findElement(By.xpath(format("//tbody/tr[last()]/td[text()='%s']", contactData.getLastName()))).isDisplayed();
        wd.findElement(By.xpath(format("//tbody/tr[last()]/td[text()='%s']", contactData.getFirstName()))).isDisplayed();
        wd.findElement(By.xpath(format("//tbody/tr[last()]/td[text()='%s']", contactData.getAddress()))).isDisplayed();
        wd.findElement(By.xpath(format("//tbody/tr[last()]/td[text()='%s']", contactData.getMobilePhone()))).isDisplayed();
        wd.findElement(By.xpath(format("//tbody//tr[last()]//a[text()='%s']", contactData.getPrimaryEmail()))).isDisplayed();
    }
}
