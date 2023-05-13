package ru.stqa.javaCourse.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

import static java.lang.CharSequence.compare;
import static java.lang.String.format;

public class GroupCreationTests extends BaseTest {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData groupData = new GroupData("test1", "test2", "test3");
        app.getGroupHelper().createGroup(groupData);
        app.wd.findElement(By.xpath(format("//span[text()='%s']", groupData.getName()))).isDisplayed();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(groupData);
        Comparator<? super GroupData> byName = (g1, g2) -> compare(g1.getName(), g2.getName());
        before.sort(byName);
        after.sort(byName);
        Assert.assertEquals(before, after);
    }
}
