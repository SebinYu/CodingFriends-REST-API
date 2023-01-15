package net.skhu.codingFriends.repository;

import net.skhu.codingFriends.entity.learningmaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LearningmaterialRepository extends JpaRepository<learningmaterial, Integer> {

    @Query("SELECT l FROM learningmaterial l")
    List<learningmaterial> findAllLearningMaterial();
}
