package ru.stqa.javaCourse.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;

public class ContactModificationTests extends BaseTest {

    @Test
    public void testContactModification() throws Exception {
        ContactData contactData = new ContactData("javaCourse@test.ru");
        app.getContactHelper().initContactModification(contactData.getPrimaryEmail());
        app.getContactHelper().modifyPrimaryFields(By.xpath("//*[@id='content']/form[1]/input[3]"));
        app.getContactHelper().updateContactModification();
        app.getNavigationHelper().returnToHomePage();
    }
}
