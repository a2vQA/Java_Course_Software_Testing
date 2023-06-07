package ru.stqa.javaCourse.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.javaCourse.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ChangePasswordTests extends BaseTest {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        String user = "UserForChangingPassword";
        String password = String.valueOf(System.currentTimeMillis());
        app.uiHelper().loginAsAdmin();
        String email = app.uiHelper().dropPasswordForUser();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String resetLink = findResetLink(mailMessages, email);
        app.uiHelper().setNewPassword(resetLink, password);
        assertTrue(app.newSession().login(user, password));

    }

    private String findResetLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
