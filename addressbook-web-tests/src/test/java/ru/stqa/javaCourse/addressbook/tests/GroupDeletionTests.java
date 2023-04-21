package ru.stqa.javaCourse.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends BaseTest{

    @Test
    public void testGroupDeletion() throws Exception {
        app.goToGroupPage();
        app.selectGroup();
        app.deleteSelectedGroups();
        app.returnToGroupPage();
    }

}
