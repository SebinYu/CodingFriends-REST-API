package net.skhu.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UserVO {

    BigInteger user_id;
    String email;
    String password;
    String name;
    String address;
    String address_detail;


}