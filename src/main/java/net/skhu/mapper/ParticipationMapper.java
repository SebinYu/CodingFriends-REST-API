package net.skhu.mapper;

import net.skhu.dto.Apply;
import net.skhu.dto.Participation;
import net.skhu.dto.Studygroup;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ParticipationMapper {


    //   로그인 사용자가 등록한 스터디 이름 조회
    @Select("SELECT *" +
            " FROM studygroup WHERE writer = #{writer}")
    List<Studygroup> findStudygroupTitle(String writer);


    @Select("SELECT a.userId, u.name " +
            " FROM apply a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            "                 WHERE s.title = #{title}                   " +
            " ORDER BY u.user_id")
    List<Apply> findApplier(String title);


    @Select("SELECT s.title " +
            " FROM participationrate p JOIN user u ON p.studentId = u.user_id                 " +
            "                 JOIN studygroup s ON p.studygroupId = s.studyGroup_id                   " +
            "                 JOIN studygroup s ON p.studyGroup_Leader = s.writer                   " +
            "                 WHERE s.writer = #{writer}                   " +
            " ORDER BY u.user_id")
    List<Participation> findAll(String writer);

    @Insert("INSERT participationrate (studentId,studygroupId,studyGroup_Leader)"
            + " VALUES (#{studentId},#{studygroupId},#{studyGroup_Leader})")
    @Options(useGeneratedKeys=true, keyProperty="participationRate_id")
    void Insert(Participation participation);

}
