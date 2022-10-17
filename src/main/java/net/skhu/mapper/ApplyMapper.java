package net.skhu.mapper;

import net.skhu.dto.Apply;
import net.skhu.dto.Studygroup;
import net.skhu.dto.UserVO;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ApplyMapper {

    @Select("SELECT a.*, u.name, u.user_id" +
            " FROM applys a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            " ORDER BY u.user_id")
    List<Apply> findAll();

    @Select("SELECT a.*, u.name" +
            " FROM applys a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            "                 WHERE a.studygroupId = #{studyGroup_id}                   " +
            " ORDER BY u.user_id")
    List<Apply> findApplyList(BigInteger studyGroup_id);

    @Insert("INSERT applys (userId,studygroupId,application)"
            + " VALUES (#{userId},#{studygroupId},#{application})")
    @Options(useGeneratedKeys=true, keyProperty="apply_id")
    void insert(Apply apply);


    @Update("UPDATE applys SET userId = #{userId}, studygroupId = #{studygroupId}, application = #{application} WHERE apply_id = #{apply_id}")
    void update(Apply apply);


    @Delete("DELETE FROM applys WHERE apply_id = #{apply_id}")
    void delete(BigInteger apply_id);


}
