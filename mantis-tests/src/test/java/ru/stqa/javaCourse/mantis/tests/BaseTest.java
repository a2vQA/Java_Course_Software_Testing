package ru.stqa.javaCourse.mantis.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.javaCourse.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

public class BaseTest {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config/config_inc.php", "config/config_inc.php.backup");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.stop();
        app.ftp().restore("config/config_inc.php.backup", "config/config_inc.php");
    }
}
