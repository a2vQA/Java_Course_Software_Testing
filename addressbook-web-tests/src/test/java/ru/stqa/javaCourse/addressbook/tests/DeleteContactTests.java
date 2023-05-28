package ru.stqa.javaCourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import java.util.Set;

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
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteContact(deletedContact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }
}
