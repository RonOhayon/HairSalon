package com.example.hair_salon;

public class User {
    private String Name;
    private String email;
    private String password;
    private String phoneNumber;

    public User(String Name, String email, String password, String phoneNumber) {
        this.Name = Name;
        this.email = email;
        this.password =password;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return Name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.Name = firstName;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
