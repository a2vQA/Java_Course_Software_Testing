package ru.stqa.javaCourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;

public class DeleteContactTests extends BaseTest {

    @Test
    public void testDeleteContact() throws Exception {
        if (! app.getContactHelper().isThereAnyContact()){
            app.getContactHelper().createContact(new ContactData("Vladislav",
                                                        "Artyomenko",
                                                        "Moscow",
                                                        "+79999999999",
                                                        "javaCourse@test.ru",
                                                        "test1"));
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContact();
    }
}
