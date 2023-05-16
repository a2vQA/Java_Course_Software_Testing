package ru.stqa.javaCourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends BaseTest {

    @Test
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contactData = new ContactData("Vladislav", "Artyomenko", "Moscow", "+79999999999", "javaCourse@test.ru");
        app.getContactHelper().initContactCreation();
        checkForGroupToExist();
        app.getContactHelper().createContact(contactData);
        app.getContactHelper().checkerForContactExists(contactData);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contactData);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

    public void checkForGroupToExist(){
    if (!app.getGroupHelper().isThereAnyGroupInContactCreation()) {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        app.getContactHelper().initContactCreation();
        }
    }
}
