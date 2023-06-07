package ru.stqa.javaCourse.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;
import ru.stqa.javaCourse.addressbook.model.Contacts;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends BaseTest {

    @BeforeMethod
    public void checkForContactAndGroupToExist(){
        if (app.db().contacts().size() == 0){
            app.goTo().groupPage();
            if (app.db().groups().size() == 0){
                app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
            }
            app.goTo().homePage();
            app.contact().initContactCreation();
            app.contact().createContactWithGroup(new ContactData().withFirstName("Vladislav").withLastName("Artyomenko").withAddress("Moscow")
                    .withMobilePhone("+79999999999").withHomePhone("999999").withWorkPhone("888888")
                    .withPrimaryEmail("javaCourse@test.ru").withSecondaryEmail("javaCourse2@test.ru").withThirdEmail("javaCourse3@test.ru"));
        }
    }

    @Test
    public void testContactModification() {
        checkForContactAndGroupToExist();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contactData = new ContactData().withFirstName("VladislavModified").withLastName("ArtyomenkoModified")
                .withAddress("MoscowModified").withMobilePhone("+79999999998").withHomePhone("999998").withWorkPhone("888889")
                .withPrimaryEmail("javaCourseModified@test.ru").withSecondaryEmail("javaCourse2Modified@test.ru").withThirdEmail("javaCourse3Modified@test.ru")
                .withId(modifiedContact.getId());
        app.contact().modify(contactData);
        Contacts after = app.db().contacts();

        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactData)));
        verifyContactListInUI();
    }
}
