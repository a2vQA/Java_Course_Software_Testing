package ru.stqa.javaCourse.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.javaCourse.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactFormRequiredFields(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getPrimaryEmail());

        if(creation){
            new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void checkerForContactExists(ContactData contactData) {
        isDisplayed(By.xpath(format("//tbody/tr/td[text()='%s']", contactData.getLastName())));
        isDisplayed(By.xpath(format("//tbody/tr/td[text()='%s']", contactData.getFirstName())));
        isDisplayed(By.xpath(format("//tbody/tr/td[text()='%s']", contactData.getAddress())));
        isDisplayed(By.xpath(format("//tbody/tr/td[text()='%s']", contactData.getMobilePhone())));
        isDisplayed(By.xpath(format("//tbody//tr//a[text()='%s']", contactData.getPrimaryEmail())));
    }

    public void initContactModification(int index) {
        wd.findElements(By.cssSelector("[title='Edit']")).get(index).click();
    }

    public void updateContactModification() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.xpath("//*[@value='Delete']"));
    }

    public void createContact(ContactData contactData) {
        fillContactFormRequiredFields(contactData, true);
        submitContactCreation();
        returnToHomePage();
    }

    public void modify(int index, ContactData changedContactData) {
        initContactModification(index);
        fillContactFormRequiredFields(changedContactData, false);
        updateContactModification();
    }

    public boolean isThereAnyContact() {
        return isElementPresent(By.xpath("(//img[last()][@title='Edit'])[1]"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//*[@name='entry']"));
        for (WebElement element : elements) {
            String lastName = element.findElement(By.xpath("td[2]")).getText();
            String firstName = element.findElement(By.xpath("td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withFirstName(firstName).withLastName(lastName).withId(id));
        }
        return contacts;
    }
}
