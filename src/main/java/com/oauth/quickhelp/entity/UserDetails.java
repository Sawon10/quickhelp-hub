package com.oauth.quickhelp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.oauth.quickhelp.constants.CommonConstant.*;

@Entity
@Data
@Table(name = USER_DETAILS)
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    @Column(name = USER_ID, nullable = false, unique = true)
    private Long userId;
    @Column(name = USERNAME, nullable = false, unique = true)
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
}
