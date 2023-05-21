package ru.stqa.javaCourse.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;

public class GroupCreationTests extends BaseTest {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();
        GroupData groupData = new GroupData().withName("test2");
        app.group().create(groupData);
        app.wd.findElement(By.xpath(format("//span[text()='%s']", groupData.getName()))).isDisplayed();
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        groupData.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(groupData);
        Assert.assertEquals(before, after);
    }
}
