package net.skhu.mapper;

import net.skhu.dto.request.RequestLearningMaterial;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LearningMaterialMapper {

    @Select("SELECT learningMaterial_id, materialType FROM learningmaterial")
    List<RequestLearningMaterial> findAll();

    @Select("SELECT learningMaterial_id, materialType FROM learningmaterial WHERE learningMaterial_id = #{learningMaterial_id}")
    RequestLearningMaterial findOne(int learningMaterial_id);

    @Insert("INSERT learningmaterial (learningMaterial_id) VALUES (#{learningMaterial_id})")
    @Options(useGeneratedKeys=true, keyProperty="learningMaterial_id")
    void insert(RequestLearningMaterial learningMaterial);

    @Update("UPDATE learningmaterial SET learningMaterial_id = #{learningMaterial_id} ")
    void update(RequestLearningMaterial learningMaterial);

    @Delete("DELETE FROM learningmaterial WHERE learningMaterial_id = #{learningMaterial_id}")
    void delete(RequestLearningMaterial learningMaterial);
}
