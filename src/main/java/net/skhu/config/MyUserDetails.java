package net.skhu.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import net.skhu.entity.user;

@Data
public class MyUserDetails implements UserDetails {
     static final long serialVersionUID = 1L;

    final boolean accountNonExpired = true;
    final boolean accountNonLocked = true;
    final boolean credentialsNonExpired = true;
    final String password;
    final String username;
    final boolean isEnabled;
    final int userId;
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

    final String name;
    final String email;
    final String userType;
    final boolean admin;

    public MyUserDetails(user user) {
        switch (user.getUserType()) {
            case "사용자": authorities.add(new SimpleGrantedAuthority("ROLE_LEADER")); break;
            case "관리자": authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT")); break;
        }
        if (user.isAdmin()) authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        this.username = user.getUserid();
        this.password = user.getPassword();
        this.isEnabled = user.isEnabled();

        this.userId = user.getUser_id();
        this.name = user.getName();
        this.email = user.getEmail();
        this.userType = user.getUserType();
        this.admin = user.isAdmin();


    }

}
