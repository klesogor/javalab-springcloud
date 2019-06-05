package it61.springlabs.data.dto.user;


import java.util.UUID;

public class AccountDetailsDto {
    private UUID id;
    private String phone;
    private String city;
    private String company;
    private Integer age;
    private String email;

    public AccountDetailsDto(UUID id, String phone, String city, String company, Integer age, String email) {
        this.id = id;
        this.phone = phone;
        this.city = city;
        this.company = company;
        this.age = age;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getCompany() {
        return company;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}
