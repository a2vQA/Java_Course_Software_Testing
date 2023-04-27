package ru.stqa.javaCourse.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactModificationTests extends BaseTest {

    @Test
    public void testContactModification() throws Exception {
        app.getContactHelper().initContactModification();
        app.getContactHelper().modifyFieldInContact(By.xpath("//*[@id='content']/form[1]/input[3]"), "Vladislav2");
        app.getContactHelper().updateContactModification();
        app.getNavigationHelper().returnToHomePage();
    }
}
