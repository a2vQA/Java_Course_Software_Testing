package ru.stqa.javaCourse.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static java.lang.String.format;

public class GroupCreationTests extends BaseTest {

  @Test
  public void testGroupCreation() throws Exception {
    goToGroupPage();
    initGroupCreation();
    GroupData groupData = new GroupData("test1", "test2", "test3");
    fillGroupForm(groupData);
    submitGroupCreation();
    returnToGroupPage();
    wd.findElement(By.xpath(format("//span[last()][text()='%s']", groupData.getName()))).isDisplayed();
  }
}
