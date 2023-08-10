package net.skhu.codingFriends.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime updateDate;

    public List<String> getRoleList() {
        if (this.userType.length() > 0) {
            return Arrays.asList(this.userType.split(","));
        }
        return new ArrayList<>();
    }
}
