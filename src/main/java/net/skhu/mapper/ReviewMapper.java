package net.skhu.mapper;


import net.skhu.dto.Review;
import net.skhu.dto.response.ResponseParticipation;
import net.skhu.dto.response.ResponseStudygroup;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ReviewMapper {

    @Select("SELECT studentId" +
            " FROM review WHERE studyGroupPartner = #{studyGroupPartner}")
    Integer[] findUplodedUser(String studyGroupPartner);

    @Select("SELECT studentId" +
            " FROM review WHERE studentId = #{studentId} && studyGroupPartner = #{studyGroupPartner}")
    BigInteger[] findReviewID(String studentId, String studyGroupPartner);

    @Select("SELECT studyGroup_id" +
            " FROM studygroup WHERE title = #{title} " )
    BigInteger findStudygroupID(String title);

    @Select("SELECT user_id" +
            " FROM user WHERE name = #{name} " )
    BigInteger findchosenStudentID(String name);

    @Update("UPDATE studygroup SET title = #{title}, content = #{content}, writer = #{writer}, totalNum = #{totalNum}, startDate = #{startDate}, endDate = #{endDate} " +
            "WHERE studyGroup_id = #{studyGroup_id}")
    void update(Review review);

    @Insert("INSERT review (studentId, studygroupId, studyGroupPartner, reviewScore, reviewContents,lectureScore, objection   )"
            + " VALUES (#{studentId},#{studygroupId},#{studyGroupPartner}, #{reviewScore}, #{reviewContents}, #{lectureScore}, #{objection})")
    @Options(useGeneratedKeys=true, keyProperty="review_id")
    void Insert(Review review);

    @Delete("DELETE FROM review WHERE reviewContents = #{reviewContents}")
    void delete(String reviewContents);

}
