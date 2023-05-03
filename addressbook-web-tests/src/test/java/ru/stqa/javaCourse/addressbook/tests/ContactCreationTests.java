package ru.stqa.javaCourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;
import ru.stqa.javaCourse.addressbook.model.GroupData;

public class ContactCreationTests extends BaseTest {

    @Test
    public void testContactCreation() throws Exception {
        ContactData contactData = new ContactData("Vladislav", "Artyomenko", "Moscow", "+79999999999", "javaCourse@test.ru");
        app.getContactHelper().initContactCreation();
        checkForGroupToExist();
        app.getContactHelper().createContact(contactData);
        app.getContactHelper().checkerForContactExists(contactData);
    }

    public void checkForGroupToExist(){
    if (!app.getGroupHelper().isThereAnyGroupInContactCreation()) {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        app.getContactHelper().initContactCreation();
        }
    }
}
