package ru.stqa.javaCourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

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
        List<ContactData> before = app.contact().list();
        ContactData contactData = new ContactData().withFirstName("Vladislav").withLastName("Artyomenko").withAddress("Moscow")
                        .withMobilePhone("+79999999999").withPrimaryEmail("javaCourse@test.ru");
        app.contact().initContactCreation();
        checkForGroupToExist();
        app.contact().createContact(contactData);
        app.contact().checkerForContactExists(contactData);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contactData);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
