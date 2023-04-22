package ru.stqa.javaCourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;

public class DeleteContactTests extends BaseTest {

    @Test
    public void testDeleteContact() throws Exception {
        ContactData contactData = new ContactData("javaCourse@test.ru");
        app.getContactHelper().initContactModification(contactData.getPrimaryEmail());
        app.getContactHelper().deleteContact();
    }
}
