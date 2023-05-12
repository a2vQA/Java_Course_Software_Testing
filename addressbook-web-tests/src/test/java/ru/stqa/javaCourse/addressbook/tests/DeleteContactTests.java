package ru.stqa.javaCourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;

public class DeleteContactTests extends BaseTest {

    @Test
    public void testDeleteContact() throws Exception {
        checkForContactToExist();
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContact();
    }

    public void checkForContactToExist(){
        if (! app.getContactHelper().isThereAnyContact()){
            app.getContactHelper().initContactCreation();
            app.getContactHelper().createContact(new ContactData("Vladislav",
                    "Artyomenko",
                    "Moscow",
                    "+79999999999",
                    "javaCourse@test.ru"));
        }
    }
}
