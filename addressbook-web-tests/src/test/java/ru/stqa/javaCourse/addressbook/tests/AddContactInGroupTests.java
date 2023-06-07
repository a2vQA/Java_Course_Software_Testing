package ru.stqa.javaCourse.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;
import ru.stqa.javaCourse.addressbook.model.GroupData;

public class AddContactInGroupTests extends BaseTest {

    @BeforeMethod
    public void checkForContactToExistAndCreateGroup(){
        if (app.db().contacts().size() == 0){
                app.goTo().homePage();
                app.contact().initContactCreation();
                app.contact().createContactWithoutGroup(new ContactData().withFirstName("Vladislav").withLastName("Artyomenko").withAddress("Moscow")
                        .withMobilePhone("+79999999999").withPrimaryEmail("javaCourse@test.ru"));
        }
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        GroupData group = app.db().groups().iterator().next();
        int newGroupId = group.getId();
    }

    @Test
    public void testAddContactInGroup() {
        app.goTo().homePage();
        ContactData contact = app.db().contacts().iterator().next();
        app.contact().activateContactCheckbox(contact.getId());
        app.contact().addToGroupButton();
        contact.getGroups();
        System.out.println(contact);

    }
}
