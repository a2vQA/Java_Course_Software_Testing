package ru.stqa.javaCourse.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;

public class ContactModificationTests extends BaseTest {

    @Test
    public void testContactModification() throws Exception {
        app.getContactHelper().initContactModification();
        ContactData changedContactData = new ContactData("VladislavModified", "ArtyomenkoModified", "MoscowModified", "+79999999998", "javaCourseModified@test.ru");
        app.getContactHelper().fillContactFormRequiredFields(changedContactData);
        app.getContactHelper().updateContactModification();
        app.getNavigationHelper().returnToHomePage();
    }
}
