package ru.stqa.javaCourse.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

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

        groupData.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(groupData);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
