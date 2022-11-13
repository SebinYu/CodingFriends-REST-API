package net.skhu.repository;

import net.skhu.dto.Participation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParticipationRepository extends JpaRepository<Participation, Integer>  {

    int countByStudentId(int studentId);
}
