package ru.stqa.javaCourse.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactModificationTests extends BaseTest {

    @Test
    public void testContactModification() throws Exception {
        app.getContactHelper().initContactModification();
        app.getContactHelper().modifyPrimaryFields(By.xpath("//*[@id='content']/form[1]/input[3]"));
        app.getContactHelper().updateContactModification();
        app.getNavigationHelper().returnToHomePage();
    }
}
