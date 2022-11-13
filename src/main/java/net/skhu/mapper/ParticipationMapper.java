package net.skhu.mapper;

import net.skhu.dto.Apply;
import net.skhu.dto.Participation;
import net.skhu.dto.Studygroup;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheEvict;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Mapper
public interface ParticipationMapper {


    //   로그인 사용자가 등록한 스터디 이름 조회
    @Select("SELECT title, studyGroup_id FROM studygroup WHERE writer = #{writer}")
    List<Map<String, Studygroup>> findStudygroupTitle(String writer);

    @Select("SELECT studyGroup_id FROM studygroup WHERE title = #{title} && writer = #{writer}")
    Integer findStudygroupid(String title, String writer);

    @Select("SELECT a.userId, a.updateDate, u.name " +
            " FROM apply a JOIN user u ON a.userId = u.user_id                 " +
            "                 JOIN studygroup s ON a.studygroupId = s.studyGroup_id                   " +
            "                 WHERE s.title = #{title}                   " +
            " ORDER BY u.user_id")
    List<Apply> findApplier(String title);


    @Insert("INSERT participationrate (studentId,studygroupId,studyGroup_Leader)"
            + " VALUES (#{studentId},#{studygroupId},#{studyGroup_Leader})")
    @Options(useGeneratedKeys=true, keyProperty="participationRate_id")
    void Insert(Participation participation);

    @Delete("DELETE FROM participationrate WHERE studentId = #{studentId}")
    void delete(int studentId);
}
