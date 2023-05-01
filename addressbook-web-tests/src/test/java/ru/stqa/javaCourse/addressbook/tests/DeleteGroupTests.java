package ru.stqa.javaCourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.GroupData;

public class DeleteGroupTests extends BaseTest {

    @Test
    public void testGroupDeletion() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        checkForGroupToExist();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }

    public void checkForGroupToExist() {
        if (! app.getGroupHelper().isThereAnyGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
    }
}
