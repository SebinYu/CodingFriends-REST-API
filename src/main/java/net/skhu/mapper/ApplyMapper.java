package net.skhu.mapper;

import net.skhu.dto.Apply;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Mapper
public interface ApplyMapper {

    @Select("SELECT a.*, u.name, u.user_id" +
            " FROM apply a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            " ORDER BY u.user_id")
    List<Apply> findAll();

    @Select("SELECT a.*, u.name" +
            " FROM apply a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            "                 WHERE a.studygroupId = #{studyGroup_id}                   " +
            " ORDER BY u.user_id")
    List<Apply> findApplyList(BigInteger studyGroup_id);

    @Select("SELECT s.title " +
            " FROM apply a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            "                 WHERE u.userid = #{userName}                   " +
            " ORDER BY u.user_id")
    List<Apply> findUserApplyList(String userName);

    @Select("SELECT a.userId, a.studygroupId, u.name " +
            " FROM apply a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            "                 WHERE a.studygroupId = #{studygroupID} AND a.applyStatus = #{applyStatus}              " +
            " ORDER BY u.user_id")
    List<Map<String, Apply>> findAcceptedAppliers(Integer studygroupID, String applyStatus);

    @Insert("INSERT apply (userId,studygroupId, title, application)"
            + " VALUES (#{userId},#{studygroupId},#{title},#{application})")
    @Options(useGeneratedKeys=true, keyProperty="apply_id")
    void insert(Apply apply);


    @Update("UPDATE apply SET applyStatus = #{applyStatus} WHERE userId = #{userId}")
    void update(Apply apply);

    @Delete("DELETE FROM apply WHERE userId = #{userId}")
    void delete(BigInteger userId);


}
