package com.oauth.quickhelp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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
    @Column(name = PASSWORD, nullable = false)
    private String password;
    @Column(name = EMAIL, nullable = false, unique = true)
    private String email;
    @Column(name = PHONE_NUMBER, nullable = true, unique = true)
    private String phoneNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = USER_ID),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRole> roles;
    @OneToMany(mappedBy = USER_DETAILS, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses;
}
