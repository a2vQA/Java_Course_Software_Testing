package ru.stqa.javaCourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends BaseTest {

    @Test
    public void testContactModification() {
        checkForContactAndGroupToExist();
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData changedContactData = new ContactData("VladislavModified", "ArtyomenkoModified",
                "MoscowModified", "+79999999998", "javaCourseModified@test.ru", before.get(index).getId());
        app.contact().modify(index, changedContactData);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();

        before.remove(before.size() - 1);
        before.add(changedContactData);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

    public void checkForContactAndGroupToExist(){
        if (app.contact().list().size() == 0){
            app.goTo().groupPage();
            if (app.group().list().size() == 0){
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
