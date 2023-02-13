package net.skhu.mapper;

import net.skhu.dto.request.RequestStudygroup;
import net.skhu.dto.response.ResponseParticipation;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Mapper
public interface ParticipationMapper {


    //   로그인 사용자가 등록한 스터디 이름 조회
    @Select("SELECT title, studyGroup_id FROM studygroup WHERE writer = #{writer}")
    List<Map<String, RequestStudygroup>> findStudygroupTitle(String writer);

    @Select("SELECT studyGroup_id FROM studygroup WHERE title = #{title} && writer = #{writer}")
    Integer findStudygroupid(String title, String writer);

    @Select("SELECT week FROM participationrate WHERE studygroupId = #{studygroupId}")
    Integer[] findAllWeek(String studygroupId);

    @Select("SELECT week FROM participationrate WHERE studygroupId = #{studygroupId}")
    Integer[] findParticipationID(String studygroupId);


    @Select("SELECT p.participationRate_id, p.studentId, p.studygroupId, p.week, p.weeklyAttendance, p.weeklyHomework, s.title, u.name" +
            " FROM participationrate p JOIN user u ON p.studentId = u.user_id                 " +
            "                 JOIN studygroup s ON p.studygroupId = s.studyGroup_id                   " +
            "                 WHERE p.studygroupId = #{studygroupId}                   " +
            " ORDER BY u.user_id")
    List<Map<String, ResponseParticipation>> findParticipant(Integer studygroupId);

    @Select("SELECT DISTINCT p.week" +
            " FROM participationrate p JOIN user u ON p.studentId = u.user_id                 " +
            "                 JOIN studygroup s ON p.studygroupId = s.studyGroup_id                   " +
            "                 WHERE p.studygroupId = #{studygroupId}                   " +
            " ORDER BY u.user_id")
    List<Map<String, ResponseParticipation>> findWeekInfo(Integer studygroupId);



    @Select("SELECT p.participationRate_id,p.studentId, p.studygroupId, p.week, p.weeklyAttendance, p.weeklyHomework, s.title, u.name, u.email" +
            " FROM participationrate p JOIN user u ON p.studentId = u.user_id                 " +
            "                 JOIN studygroup s ON p.studygroupId = s.studyGroup_id                   " +
            "                 WHERE p.week = #{week} && s.studyGroup_id = #{studygroupID}" +
            " ORDER BY u.user_id")
    List<Map<String, ResponseParticipation>> findWeeklyReport(String week, Integer studygroupID);

    @Select("SELECT p.participationRate_id,p.studentId, p.studygroupId, p.week, p.weeklyAttendance, p.weeklyHomework, s.title, u.name" +
            " FROM participationrate p JOIN user u ON p.studentId = u.user_id                 " +
            "                 JOIN studygroup s ON p.studygroupId = s.studyGroup_id                   " +
            "                 WHERE p.week = #{week}                   " +
            " ORDER BY u.user_id")
    List<Map<String, ResponseParticipation>> findExCompany(String title, String useris);


    @Select("SELECT a.studygroupId" +
            " FROM apply a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            "                 WHERE a.userId = #{idChecked}                   " +
            " ORDER BY u.user_id")
    String findAcceptedUserInfo(Integer idChecked);



    @Insert("INSERT participationrate (studentId, studygroupId, studyGroup_Leader, week, weeklyHomework, weeklyAttendance, lectureScore  )"
            + " VALUES (#{studentId},#{studygroupId},#{studyGroup_Leader}, #{week}, #{weeklyHomework}, #{weeklyAttendance}, #{lectureScore})")
    @Options(useGeneratedKeys=true, keyProperty="participationRate_id")
    void Insert(ResponseParticipation participation);

    @Delete("DELETE FROM participationrate WHERE participationRate_id = #{participationRate_id}")
    void delete(BigInteger participationRate_id);
}
