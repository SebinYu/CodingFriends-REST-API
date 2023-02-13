package net.skhu.mapper;


import net.skhu.dto.Objection;
import net.skhu.dto.Review;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ReviewMapper {
    @Select("SELECT *" +
            " FROM review WHERE review_id = #{review_id} " )
    List<Review> findAll(BigInteger review_id);

    @Select("SELECT review_id" +
            " FROM review WHERE studentId = #{studentId} " )
    BigInteger[] findReviewID(BigInteger studentId);

    @Select("SELECT objection" +
            " FROM review WHERE studentId = #{studentId} " )
    Integer[] findObjection(BigInteger studentId);

    @Select("SELECT user_id" +
            " FROM user WHERE userid = #{name} " )
    BigInteger findStudentId(String name);

    @Select("SELECT studentId" +
            " FROM review WHERE studyGroupPartner = #{studyGroupPartner}")
    Integer[] findUplodedUser(String studyGroupPartner);

    @Select("SELECT studyGroup_id" +
            " FROM studygroup WHERE title = #{title} " )
    BigInteger findStudygroupID(String title);

    @Select("SELECT user_id" +
            " FROM user WHERE name = #{name} " )
    BigInteger findchosenStudentID(String name);

    @Select("SELECT lectureScore" +
            " FROM participationrate WHERE studentId = #{oneStudentId} " )
    List<Integer> findAccumulatedLectureScore(String oneStudentId);

    @Select("SELECT reviewScore" +
            " FROM review WHERE studentId = #{oneStudentId} " )
    List<Integer> findAccumulatedReviewScore(String oneStudentId);

    @Update("UPDATE review SET objection = #{objection} WHERE review_id = #{review_id}")
    void update(Review review);

    @Insert("INSERT review (studentId, studygroupId, studyGroupPartner, reviewScore, reviewContents )"
            + " VALUES (#{studentId},#{studygroupId},#{studyGroupPartner}, #{reviewScore}, #{reviewContents})")
    @Options(useGeneratedKeys=true, keyProperty="review_id")
    void Insert(Review review);

    @Insert("INSERT objection (review_id, objection)"
            + " VALUES (#{review_id},#{objection})")
    @Options(useGeneratedKeys=true, keyProperty="objection_id")
    void objectionInsert(Objection objection);

    @Delete("DELETE FROM review WHERE reviewContents = #{reviewContents}")
    void delete(String reviewContents);

}
