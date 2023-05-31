package ru.stqa.javaCourse.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.addressbook.model.ContactData;
import ru.stqa.javaCourse.addressbook.model.GroupData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDataCompareTests extends BaseTest {

    @BeforeMethod
    public void checkForContactAndGroupToExist(){
        if (app.contact().all().size() == 0){
            app.goTo().groupPage();
            if (app.group().all().size() == 0){
                app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
            }
            app.goTo().homePage();
            app.contact().initContactCreation();
            app.contact().createContact(new ContactData().withFirstName("Vladislav")
                    .withLastName("Arryomenko").withAddress("Moscow")
                    .withMobilePhone("+7(999)9999999").withWorkPhone("99-99-99").withHomePhone("999999")
                    .withPrimaryEmail("javaCourse@test.ru").withSecondaryEmail("javaCourse2@test.ru").withThirdEmail("javaCourse3@test.ru"));
        }
    }

    @Test
    public void testContactDataCompare() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().fullnamePhonesEmailsFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

        app.contact().deleteContact(contact);
    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()).filter((s) -> ! s.equals(""))
                .map(ContactDataCompareTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Stream.of(contact.getPrimaryEmail(), contact.getSecondaryEmail(), contact.getThirdEmail()).filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}