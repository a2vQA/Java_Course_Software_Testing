package ru.stqa.javaCourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends BaseTest {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPage();
        checkForGroupToExist();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        GroupData groupData = new GroupData("test3", "test2", "test1", before.get(before.size() - 1).getId());
        app.getGroupHelper().fillGroupForm(groupData);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(groupData);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

    public void checkForGroupToExist() {
        if (! app.getGroupHelper().isThereAnyGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
    }
}
