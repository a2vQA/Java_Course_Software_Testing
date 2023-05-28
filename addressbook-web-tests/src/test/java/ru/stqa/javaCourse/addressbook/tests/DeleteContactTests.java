package ru.stqa.javaCourse.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;
import ru.stqa.javaCourse.addressbook.model.Contacts;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class DeleteContactTests extends BaseTest {

    @BeforeMethod
    public void checkForContactToExist(){
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
    public void testDeleteContact() {
        checkForContactToExist();
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteContact(deletedContact);
        app.goTo().homePage();
        Contacts after = app.contact().all();

        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
