package ru.stqa.javaCourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends BaseTest {

    @BeforeMethod
    public void checkForGroupToExist(){
        if (!app.group().isThereAnyGroupInContactCreation()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
            app.contact().initContactCreation();
        }
    }

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        ContactData contactData = new ContactData().withFirstName("Vladislav").withLastName("Artyomenko").withAddress("Moscow")
                        .withMobilePhone("+79999999999").withPrimaryEmail("javaCourse@test.ru");
        app.contact().initContactCreation();
        checkForGroupToExist();
        app.contact().createContact(contactData);
        app.contact().checkerForContactExists(contactData);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contactData.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contactData);
        Assert.assertEquals(before, after);
    }
}
