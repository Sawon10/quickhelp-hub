package com.oauth.quickhelp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.oauth.quickhelp.constants.CommonConstant.*;

@Entity
@Getter
@Setter
@Table(name = USER_DETAILS)
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_details_seq_gen")
    @SequenceGenerator(
            name = "user_details_seq_gen",
            sequenceName = "user_details_seq",
            allocationSize = 1
    )
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
    private Set<UserRole> roles = new HashSet<>();
    @OneToMany(mappedBy = USER_DETAILS, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses;

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) {
            return false;
        }
        UserDetails that = (UserDetails) o;
        return userId != null && Objects.equals(userId, that.userId);
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
