package ru.stqa.javaCourse.addressbook.model;

public class ContactData {
    private String firstName;
    private String lastName;
    private String address;
    private String mobilePhone;
    private final String primaryEmail;
    private String group;

    // constructor for required params
    public ContactData(String firstName, String lastName, String address, String mobilePhone, String primaryEmail, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobilePhone = mobilePhone;
        this.primaryEmail = primaryEmail;
        this.group = group;
    }

    // constructor for primaryEmail field
    public ContactData(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public String getGroup() {
        return group;
    }
}
