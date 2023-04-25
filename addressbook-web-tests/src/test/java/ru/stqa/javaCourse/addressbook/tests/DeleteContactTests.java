package ru.stqa.javaCourse.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteContactTests extends BaseTest {

    @Test
    public void testDeleteContact() throws Exception {
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContact();
    }
}
