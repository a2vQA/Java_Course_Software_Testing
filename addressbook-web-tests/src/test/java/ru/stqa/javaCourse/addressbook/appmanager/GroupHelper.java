package ru.stqa.javaCourse.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.javaCourse.addressbook.model.GroupData;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void initDeleteGroup() {
        click(By.name("edit"));
    }

    public void createGroup(GroupData groupData) {
        initGroupCreation();
        fillGroupForm(groupData);
        submitGroupCreation();
        returnToGroupPage();
    }

    public boolean isThereAnyGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public boolean isThereAnyGroupInContactCreation() {
        return isElementPresent(By.xpath("//select[@name='new_group']/option[2]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
