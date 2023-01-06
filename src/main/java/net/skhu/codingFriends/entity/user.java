package net.skhu.codingFriends.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int user_id;

    String username;

    @JsonIgnore
    String password;

    String name;
    String email;
    boolean enabled;
    String userType;
    boolean admin;
    String address;
    String address_detail;

    public List<String> getRoleList() {
        if (this.userType.length() > 0) {
            return Arrays.asList(this.userType.split(","));
        }
        return new ArrayList<>();
    }
}
