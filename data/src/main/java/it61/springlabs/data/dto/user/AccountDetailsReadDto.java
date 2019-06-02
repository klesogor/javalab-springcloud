package it61.springlabs.data.dto.user;


import java.util.UUID;

public class AccountDetailsReadDto {
    private UUID id;
    private String phone;
    private String city;
    private String company;
    private int age;
    private String email;

    public AccountDetailsReadDto(UUID id, String phone, String city, String company, int age, String email) {
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

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}
