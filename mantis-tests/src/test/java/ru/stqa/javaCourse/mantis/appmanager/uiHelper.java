package ru.stqa.javaCourse.mantis.appmanager;

import org.openqa.selenium.By;

public class uiHelper extends BaseHelper {

    public uiHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public void loginAsAdmin() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), app.getProperty("web.adminLogin"));
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[type='submit']"));
    }

    public String dropPasswordForUser() {
        click(By.xpath("(//span[@class='menu-text'])[6]"));
        click(By.xpath("(//li[@class])[9]"));
        click(By.xpath("//a[text()='UserForChangingPassword']"));
        String email = wd.findElement(By.xpath("//input[@id='email-field']")).getAttribute("value");
        click(By.xpath("(//input[@type='submit'])[2]"));
        return email;
    }

//    public void startSettingNewPassword(String username, String email) {
//        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
//        type(By.name("username"), username);
//        type(By.name("email"), email);
//        click(By.cssSelector("input[type='submit']"));
//    }
//
//    public void finishSettingNewPassword(String findResetLink) {
//        wd.get(findResetLink);
//        type(By.name("realname"), "TestUser");
//        type(By.name("password"), "as");
//        type(By.name("password_confirm"), "as");
//        click(By.cssSelector("button[type='submit']"));
//    }
}
