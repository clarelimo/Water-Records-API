package models;

import java.util.Objects;

public class User {
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String shopName;
    private String password;
    private int id;

    public User(String email, String phoneNumber, String firstName, String lastName, String shopName, String password) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shopName = shopName;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                email.equals(user.email) &&
                phoneNumber.equals(user.phoneNumber) &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                shopName.equals(user.shopName) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phoneNumber, firstName, lastName, shopName, password, id);
    }
}
