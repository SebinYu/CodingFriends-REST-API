package net.skhu.mapper;

import net.skhu.dto.Studygroup;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.math.BigInteger;
import java.util.List;

@Mapper
@CacheNamespace(flushInterval=10000)
public interface StudygroupMapper {

    @Cacheable(value= "NoticeList")
    @Select("SELECT * FROM studygroup")
    List<Studygroup> findAll();


    @Select("SELECT * FROM studygroup WHERE studyGroup_id = #{studyGroup_id}")
    Studygroup findOne(BigInteger studyGroup_id);


    @CacheEvict (value= "NoticeList", allEntries = true)
    @Insert("INSERT studygroup (title,content,writer,totalNum,startDate,endDate)"
    		+ " VALUES (#{title},#{content},#{writer},#{totalNum},#{startDate},#{endDate})")
    @Options(useGeneratedKeys=true, keyProperty="studyGroup_id")
    void insert(Studygroup studygroup);


    @CacheEvict (value= "NoticeList", allEntries = true)
    @Update("UPDATE studygroup SET title = #{title}, content = #{content}, writer = #{writer}, totalNum = #{totalNum}, startDate = #{startDate}, endDate = #{endDate} WHERE studyGroup_id = #{studyGroup_id}")
    void update(Studygroup studygroup);


    @CacheEvict (value= "NoticeList", allEntries = true)
    @Delete("DELETE FROM studygroup WHERE studyGroup_id = #{studyGroup_id}")
    void delete(BigInteger studyGroup_id);
}
