package net.skhu.mapper;

import net.skhu.dto.UserVO;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user")
    List<UserVO> findAll();

    @Select("SELECT * FROM user WHERE user_id = #{user_id}")
    UserVO findOne(BigInteger user_id);

    @Insert("INSERT user (email,password,name,address, address_detail)"
            + " VALUES (#{email},#{password},#{name},#{address}, #{address_detail})")
    @Options(useGeneratedKeys=true, keyProperty="user_id")
    void insert(UserVO userVO);

    @Update("UPDATE user SET email = #{email}, password = #{password}, name = #{name}, address = #{address}, address_detail = #{address_detail} WHERE user_id = #{user_id}")
    void update(UserVO userVO);

    @Delete("DELETE FROM user WHERE studyGroup_id = #{studyGroup_id}")
    void delete(BigInteger user_id);
}
