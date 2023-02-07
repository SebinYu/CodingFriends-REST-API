package net.skhu.codingFriends.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime updateDate;

    public List<String> getRoleList() {
        if (this.userType.length() > 0) {
            return Arrays.asList(this.userType.split(","));
        }
        return new ArrayList<>();
    }
}
