package net.skhu.codingFriends.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.codingFriends.entity.studygroup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface StudygroupRepository extends JpaRepository<studygroup, BigInteger>, StudygroupCustomRepository  {

//    List<studygroup> findByTitleContaining(String keyword);
//
//    @Query("SELECT s FROM studygroup s WHERE s.learningMaterial_id = ?1 AND s.title like %?2%")
//    List<studygroup> searchWithLearningMaterial_idAndKeyword(Integer learningMaterial_id, String keyword);


}
