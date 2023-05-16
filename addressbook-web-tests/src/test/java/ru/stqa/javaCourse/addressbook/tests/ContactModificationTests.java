package ru.stqa.javaCourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends BaseTest {

    @Test
    public void testContactModification() throws Exception {
        checkForContactToExist();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData changedContactData = new ContactData("VladislavModified", "ArtyomenkoModified",
                "MoscowModified", "+79999999998", "javaCourseModified@test.ru", before.get(before.size() - 1).getId());
        app.getContactHelper().fillContactFormRequiredFields(changedContactData, false);
        app.getContactHelper().updateContactModification();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        before.remove(before.size() - 1);
        before.add(changedContactData);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
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
