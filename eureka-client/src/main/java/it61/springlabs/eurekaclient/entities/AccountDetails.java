package it61.springlabs.eurekaclient.entities;

import it61.springlabs.data.dto.user.AccountDetailsDto;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "acount_details")
@Where(clause = "deleted = 0")
public final class AccountDetails {
    @Id
    @Type(type = "uuid-char")
    private UUID id;
    @Column
    private String phone;
    @Column
    private String city;
    @Column
    private String company;
    @Column
    private Integer age;
    @Column
    private boolean deleted;
    @Column
    private String email;

    public AccountDetails() {
    }

    public AccountDetails(UUID id,String phone, String city, String company, String email, int age) {
        this.id = id;
        this.phone = phone;
        this.city = city;
        this.company = company;
        this.email = email;
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

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public AccountDetailsDto toDto(){
        return new AccountDetailsDto(id,phone,city,company,age,email);
    }
}
