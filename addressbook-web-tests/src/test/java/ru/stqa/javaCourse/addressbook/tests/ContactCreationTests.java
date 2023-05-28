package ru.stqa.javaCourse.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;
import ru.stqa.javaCourse.addressbook.model.Contacts;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends BaseTest {

    @BeforeMethod
    public void checkForGroupToExist(){
        app.contact().initContactCreation();
        if (!app.group().isThereAnyGroupInContactCreation()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
            app.contact().initContactCreation();
        }
    }

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        File photo = new File("./src/test/resources/1.jpg");
        ContactData contactData = new ContactData().withFirstName("Vladislav").withLastName("Artyomenko").withAddress("Moscow")
                        .withMobilePhone("+79999999999").withPrimaryEmail("javaCourse@test.ru").withPhoto(photo);
        app.contact().initContactCreation();
        checkForGroupToExist();
        app.contact().createContact(contactData);
        app.contact().checkerForContactExists(contactData);
        Contacts after = app.contact().all();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contactData.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
