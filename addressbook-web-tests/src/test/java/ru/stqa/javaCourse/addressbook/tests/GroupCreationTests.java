package ru.stqa.javaCourse.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.GroupData;
import ru.stqa.javaCourse.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends BaseTest {

    @DataProvider
    public Iterator<Object[]> validGroups() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[] {new GroupData().withName("test1").withHeader("header 1").withFooter("footer 1")});
        list.add(new Object[] {new GroupData().withName("test2").withHeader("header 2").withFooter("footer 2")});
        list.add(new Object[] {new GroupData().withName("test3").withHeader("header 3").withFooter("footer 3")});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData groupData) {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(groupData);
        app.wd.findElement(By.xpath(format("//span[text()='%s']", groupData.getName()))).isDisplayed();

        assertThat(app.group().count(), equalTo(before.size() + 1));

        Groups after = app.group().all();

        assertThat(after,
                equalTo(before.withAdded(groupData.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData groupData = new GroupData().withName("test2'");
        app.group().create(groupData);

        assertThat(app.group().count(), equalTo(before.size()));

        Groups after = app.group().all();

        assertThat(after, equalTo(before));
    }
}
