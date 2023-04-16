package ru.stqa.javaCourse.addressbook;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String mobilePhone;
    private final String primaryEmail;

    // constructor for required params
    public ContactData(String firstName, String lastName, String address, String mobilePhone, String primaryEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobilePhone = mobilePhone;
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
}
