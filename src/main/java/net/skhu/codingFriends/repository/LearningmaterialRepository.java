package net.skhu.codingFriends.repository;

import net.skhu.codingFriends.entity.Learningmaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LearningmaterialRepository extends JpaRepository<Learningmaterial, Integer> {

    @Query("SELECT l FROM Learningmaterial l")
    List<Learningmaterial> findAllLearningMaterial();
}
