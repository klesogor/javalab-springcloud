package it61.springlabs.eurekaclient.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "acount_details")
public final class AccountDetails {
    @Column
    private String phone;
    @Column
    private String city;
    @Column
    private String company;
    @Column
    private int age;
    @Column
    private boolean deleted;
    @Column
    private String email;

    public AccountDetails() {
    }

    public AccountDetails(String phone, String city, String company, String email, int age) {
        this.phone = phone;
        this.city = city;
        this.company = company;
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email;}
}
