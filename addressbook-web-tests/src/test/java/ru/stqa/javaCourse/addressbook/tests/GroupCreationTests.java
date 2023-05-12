package ru.stqa.javaCourse.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import static java.lang.String.format;

public class GroupCreationTests extends BaseTest {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        GroupData groupData = new GroupData("test1", "test2", "test3");
        app.getGroupHelper().createGroup(groupData);
        app.wd.findElement(By.xpath(format("//span[text()='%s']", groupData.getName()))).isDisplayed();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
    }
}
