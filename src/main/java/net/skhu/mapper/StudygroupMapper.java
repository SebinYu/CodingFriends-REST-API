package net.skhu.mapper;

import net.skhu.dto.Studygroup;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.Cacheable;

import java.math.BigInteger;
import java.util.List;

@Mapper
@CacheNamespace(flushInterval=10000)
public interface StudygroupMapper {

//    @Cacheable
    @Select("SELECT * FROM studygroup")
    List<Studygroup> findAll();


    @Select("SELECT * FROM studygroup WHERE studyGroup_id = #{studyGroup_id}")
    Studygroup findOne(BigInteger studyGroup_id);


    @Insert("INSERT studygroup (title,content,writer,totalNum,startDate,endDate)"
    		+ " VALUES (#{title},#{content},#{writer},#{totalNum},#{startDate},#{endDate})")
    @Options(useGeneratedKeys=true, keyProperty="studyGroup_id")
    void insert(Studygroup studygroup);


    @Update("UPDATE studygroup SET title = #{title}, content = #{content}, writer = #{writer}, totalNum = #{totalNum}, startDate = #{startDate}, endDate = #{endDate} WHERE studyGroup_id = #{studyGroup_id}")
    void update(Studygroup studygroup);


    @Delete("DELETE FROM studygroup WHERE studyGroup_id = #{studyGroup_id}")
    void delete(BigInteger studyGroup_id);
}
