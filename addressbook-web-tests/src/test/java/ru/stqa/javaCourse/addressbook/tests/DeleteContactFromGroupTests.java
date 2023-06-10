package ru.stqa.javaCourse.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;
import ru.stqa.javaCourse.addressbook.model.GroupData;
import ru.stqa.javaCourse.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static ru.stqa.javaCourse.addressbook.tests.BaseTest.app;

public class DeleteContactFromGroupTests {

    @BeforeMethod
    public void doPreconditions(){
        if (app.db().contacts().size() == 0){
            app.goTo().homePage();
            app.contact().initContactCreation();
            app.contact().createContactWithoutGroup(new ContactData().withFirstName("Vladislav").withLastName("Artyomenko").withAddress("Moscow")
                    .withMobilePhone("+79999999999").withHomePhone("999999").withWorkPhone("888888")
                    .withPrimaryEmail("javaCourse@test.ru").withSecondaryEmail("javaCourse2@test.ru").withThirdEmail("javaCourse3@test.ru"));
        }

        ContactData contact = app.db().contacts().iterator().next();
        if (contact.getGroups().size() == 0) {
            Groups groupsBeforeCreation = app.db().groups();
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
            app.goTo().homePage();
            Groups groupsAfterCreation = app.db().groups();
            List<GroupData> newGroup = new ArrayList<>(groupsAfterCreation);
            newGroup.removeAll(groupsBeforeCreation);
            int groupToUse = newGroup.iterator().next().getId();
            app.contact().addToGroup(contact.getId(), groupToUse);
        }
    }

    @Test
    public void testDeleteContactFromGroup() {
        app.goTo().homePage();
        ContactData contact = app.db().contacts().iterator().next();
        Groups groupsBeforeDeletion = contact.getGroups();
        int groupId = contact.getGroups().iterator().next().getId();
        app.contact().deleteFromGroup(groupId, contact.getId());
//        ContactData contact = app.db().contacts().iterator().next();
        Groups groupsAfterDeletion = contact.getGroups();

        assertEquals(groupsBeforeDeletion.size() - 1, groupsAfterDeletion.size());
    }
}
