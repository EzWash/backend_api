package com.ezwash.backend.resource.accounts;

public class ProfileResource {

    private String first_name;
    private String last_name;
    private String email;

    private String phone_number;

    private String gender;



    public String getFirst_name() {
        return first_name;
    }

    public ProfileResource setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public ProfileResource setLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public ProfileResource setPhone_number(String phone_number) {
        this.phone_number = phone_number;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public ProfileResource setGender(String gender) {
        this.gender = gender;
        return this;
    }
}
