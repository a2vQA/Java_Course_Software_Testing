package ru.stqa.javaCourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import java.util.List;

public class DeleteContactTests extends BaseTest {

    @Test
    public void testDeleteContact() {
        checkForContactToExist();
        List<ContactData> before = app.contact().list();
        app.contact().initContactModification(before.size() - 1);
        app.contact().deleteContact();
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

    public void checkForContactToExist(){
        if (app.contact().list().size() == 0){
            app.goTo().groupPage();
            if (app.group().all().size() == 0){
                app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
            }
            app.goTo().homePage();
            app.contact().initContactCreation();
            app.contact().createContact(new ContactData("Vladislav",
                    "Artyomenko",
                    "Moscow",
                    "+79999999999",
                    "javaCourse@test.ru"));
        }
    }
}
