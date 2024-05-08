package com.workintech.s19d2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name= "member", schema="bank")
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="email")
    @NotNull(message = "Email can not be null")
    @NotEmpty(message = "Email can not be empty")
    @Size(min=40, message="Length of email can not be greater than 40 characters" )
    private String email;

    @Column(name="password")
    @NotNull(message = "Password can not be null")
    @NotEmpty(message = "Email can not be empty")
    @Size(min = 8, message = "Length of email can not be less than 8 characters")
    private String password;

    @ManyToMany(fetch= FetchType.EAGER) //memberla birlikte rolleri de getirmek için.(default->LAZY)
    // Rollere göre yetkilendirmek için.
    @JoinTable(name="member_role", schema="bank",
    joinColumns = @JoinColumn(name="member_id"),
    inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //users rollerini tutar.
        return roles;
    }

    @Override
    public String getUsername() { //email ile login gerçekleşir.
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //accountu lockle
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //email verification
        return true;
    }

    @Override
    public boolean isEnabled() { //login olamaması
        return true;
    }
}