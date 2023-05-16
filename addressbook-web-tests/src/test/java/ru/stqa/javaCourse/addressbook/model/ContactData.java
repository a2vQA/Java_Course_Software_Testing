package ru.stqa.javaCourse.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String mobilePhone;
    private final String primaryEmail;

    // constructor for required params
    public ContactData(String firstName, String lastName, String address, String mobilePhone, String primaryEmail, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobilePhone = mobilePhone;
        this.primaryEmail = primaryEmail;
        this.id = id;
    }

    public ContactData(String firstName, String lastName, String address, String mobilePhone, String primaryEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobilePhone = mobilePhone;
        this.primaryEmail = primaryEmail;
        this.id = Integer.MAX_VALUE;
    }

    // constructor for name params
    public ContactData(String firstName, String lastName, int id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = null;
        this.mobilePhone = null;
        this.primaryEmail = null;
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
