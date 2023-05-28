package ru.stqa.javaCourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;
import ru.stqa.javaCourse.addressbook.model.Contacts;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends BaseTest {

    @BeforeMethod
    public void checkForContactAndGroupToExist(){
        if (app.contact().all().size() == 0){
            app.goTo().groupPage();
            if (app.group().all().size() == 0){
                app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
            }
            app.goTo().homePage();
            app.contact().initContactCreation();
            app.contact().createContact(new ContactData().withFirstName("Vladislav").withLastName("Artyomenko").withAddress("Moscow")
                    .withMobilePhone("+79999999999").withPrimaryEmail("javaCourse@test.ru"));
        }
    }

    @Test
    public void testContactModification() {
        checkForContactAndGroupToExist();
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contactData = new ContactData().withFirstName("VladislavModified").withLastName("ArtyomenkoModified")
                .withAddress("MoscowModified").withMobilePhone("+79999999998").withPrimaryEmail("javaCourseModified@test.ru")
                .withId(modifiedContact.getId());
        app.contact().modify(contactData);
        Contacts after = app.contact().all();

        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactData)));
    }
}
