package ru.stqa.javaCourse.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.javaCourse.addressbook.model.ContactData;

import static java.lang.String.format;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactFormRequiredFields(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getPrimaryEmail());
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void checkerForContactExists(ContactData contactData) {
        isDisplayed(By.xpath(format("//tbody/tr[last()]/td[text()='%s']", contactData.getLastName())));
        isDisplayed(By.xpath(format("//tbody/tr/td[text()='%s'][last()]", contactData.getFirstName())));
        isDisplayed(By.xpath(format("//tbody/tr[last()]/td[text()='%s']", contactData.getAddress())));
        isDisplayed(By.xpath(format("//tbody/tr[last()]/td[text()='%s']", contactData.getMobilePhone())));
        isDisplayed(By.xpath(format("//tbody//tr[last()]//a[text()='%s']", contactData.getPrimaryEmail())));
    }

    public void initContactModification() {
        click(By.xpath("(//img[last()][@title='Edit'])[1]"));
    }

    public void modifyPrimaryFields(By locator) {
        doubleTextInField(locator, "value");
    }

    public void updateContactModification() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.xpath("//*[@value='Delete']"));
    }
}
