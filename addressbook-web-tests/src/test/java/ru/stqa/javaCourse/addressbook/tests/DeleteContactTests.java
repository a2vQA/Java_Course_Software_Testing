package ru.stqa.javaCourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import java.util.List;

public class DeleteContactTests extends BaseTest {

    @Test
    public void testDeleteContact() throws Exception {
        checkForContactToExist();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
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
