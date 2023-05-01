package ru.stqa.javaCourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;

public class ContactModificationTests extends BaseTest {

    @Test
    public void testContactModification() throws Exception {
        checkForContactToExist();
        app.getContactHelper().initContactModification();
        ContactData changedContactData = new ContactData("VladislavModified", "ArtyomenkoModified", "MoscowModified", "+79999999998", "javaCourseModified@test.ru", null);
        app.getContactHelper().fillContactFormRequiredFields(changedContactData, false);
        app.getContactHelper().updateContactModification();
        app.getNavigationHelper().goToHomePage();
    }

    public void checkForContactToExist(){
        if (! app.getContactHelper().isThereAnyContact()){
            app.getContactHelper().createContact(new ContactData("Vladislav",
                    "Artyomenko",
                    "Moscow",
                    "+79999999999",
                    "javaCourse@test.ru",
                    "test1"));
        }
    }
}
