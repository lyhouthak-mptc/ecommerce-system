package co.istad.lyhout.ecommerce.user.dto;

import jakarta.persistence.Column;

import java.util.Date;

public class UserCreateRequest {
    @Column()
    String username;
    String email;
    String familyName;
    String givenName;
    String gender;
    Date birthday;
    String mobilePhone;
    String address;
}
