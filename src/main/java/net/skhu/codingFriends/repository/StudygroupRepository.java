package net.skhu.codingFriends.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.codingFriends.entity.studygroup;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface StudygroupRepository extends JpaRepository<studygroup, Integer>  {

    @Query("SELECT s FROM studygroup s WHERE s.studyGroup_id = ?1")
    studygroup findByStudyGroup_id(BigInteger studyGroup_id);

    List<studygroup> findByTitleContaining(String keyword);

    @Query("SELECT s FROM studygroup s WHERE s.learningMaterial_id = ?1 AND s.title like %?2%")
    List<studygroup> searchWithLearningMaterial_idAndKeyword(Integer learningMaterial_id, String keyword);
}
