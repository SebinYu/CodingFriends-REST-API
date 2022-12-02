package net.skhu.mapper;

import net.skhu.dto.request.RequestApply;
import net.skhu.dto.request.RequestStudygroup;
import net.skhu.dto.response.ResponseApply;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface ApplyMapper {

    @Select("SELECT a.*, u.name, u.user_id" +
            " FROM apply a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            " ORDER BY u.user_id")
    List<ResponseApply> findAll();

    @Select("SELECT a.*, u.name" +
            " FROM apply a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            "                 WHERE a.studygroupId = #{studyGroup_id}                   " +
            " ORDER BY u.user_id")
    List<ResponseApply> findApplyList(BigInteger studyGroup_id);

    @Select("SELECT s.title " +
            " FROM apply a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            "                 WHERE u.userid = #{userName}                   " +
            " ORDER BY u.user_id")
    List<ResponseApply> findUserApplyList(String userName);

    @Select("SELECT a.userId, a.studygroupId, u.name, u.email " +
            " FROM apply a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            "                 WHERE a.studygroupId = #{studygroupID} AND a.applyStatus = #{applyStatus}              " +
            " ORDER BY u.user_id")
    List<Map<String, ResponseApply>> findAcceptedAppliers(Integer studygroupID, String applyStatus);

    @Select("SELECT a.userId, s.title, s.endDate, u.name " +
            " FROM apply a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            "                 WHERE u.userid = #{userid} AND a.applyStatus = #{applyStatus}              " +
            " ORDER BY u.user_id")
    List<Map<String, RequestApply>> findApplyLists(String userid, String applyStatus);

    @Select("SELECT s.endDate" +
            " FROM apply a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            "                 WHERE u.userid = #{userid} AND a.applyStatus = #{applyStatus}              " +
            " ORDER BY u.user_id")
    LocalDate[] findEndDate(String userid, String applyStatus);


    @Select("SELECT s.title" +
            " FROM apply a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            "                 WHERE u.userid = #{userid} AND a.applyStatus = #{applyStatus}              " +
            " ORDER BY u.user_id")
    List<RequestStudygroup> findEndDateTitle(String userid, String applyStatus);

    @Select("SELECT u.name" +
            " FROM apply a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            "                 WHERE s.title = #{title}              " +
            " ORDER BY u.user_id")
    String[] findExCompany(String title);

    @Select("SELECT u.userid" +
            " FROM apply a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            "                 WHERE s.title = #{title}              " +
            " ORDER BY u.user_id")
    String[] findExCompanyID(String title);

    @Select("SELECT name" +
            " FROM user WHERE userid = #{loginID}")
    String findExCompanyName(String loginID);

    @Insert("INSERT apply (userId,studygroupId, title, application)"
            + " VALUES (#{userId},#{studygroupId},#{title},#{application})")
    @Options(useGeneratedKeys=true, keyProperty="apply_id")
    void insert(RequestApply apply);


    @Update("UPDATE apply SET applyStatus = #{applyStatus} WHERE userId = #{userId}")
    void update(RequestApply apply);


    @Delete("DELETE FROM apply WHERE userId = #{userId}")
    void delete(BigInteger userId);


}
