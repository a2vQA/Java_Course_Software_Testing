package ru.stqa.javaCourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;

public class ContactCreationTests extends BaseTest {

    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().initContactCreation();
        ContactData contactData = new ContactData("Vladislav", "Artyomenko", "Moscow", "+79999999999", "javaCourse@test.ru");
        app.getContactHelper().fillContactFormRequiredFields(contactData);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().returnToHomePage();
        app.getContactHelper().checkerForContactExists(contactData);
    }
}
