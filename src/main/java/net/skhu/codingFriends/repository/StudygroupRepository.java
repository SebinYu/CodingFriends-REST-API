package net.skhu.codingFriends.repository;

import net.skhu.codingFriends.dto.ActionResult;
import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.codingFriends.entity.studygroup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface StudygroupRepository extends JpaRepository<studygroup, Integer>  {

    @Query("SELECT s FROM studygroup s WHERE s.studyGroup_id = ?1")
    studygroup findByStudyGroup_id(BigInteger studyGroup_id);

    List<studygroup> findByTitleContaining(String keyword);

    @Query("SELECT s FROM studygroup s WHERE s.learningMaterial_id = ?1 AND s.title like %?2%")
    List<studygroup> searchWithLearningMaterial_idAndKeyword(Integer learningMaterial_id, String keyword);

    @Transactional
    @Modifying
    @Query("UPDATE studygroup s " +
            "SET s.title=?2, s.content=?3, s.learningMaterial_id=?4," +
            "s.writer=?5, s.x_map=?6, s.y_map=?7, " +
            "s.totalNum=?8, s.currentNum=?9, s.startDate=?10, s.endDate=?11" +
            "WHERE s.studyGroup_id = ?1")
    void update(
            BigInteger studyGroup_id,
            String title,
            String content,
            int learningMaterial_id,
            String writer,
            Double x_map,
            Double y_map,
            int totalNum,
            int currentNum,
            LocalDate startDate,
            LocalDate endDate);


    @Transactional
    @Modifying
    @Query("DELETE FROM studygroup s WHERE s.studyGroup_id = ?1")
    void deleteByStudyGroup_id(BigInteger id);

}
