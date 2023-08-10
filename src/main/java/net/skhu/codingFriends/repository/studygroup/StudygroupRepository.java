package net.skhu.codingFriends.repository.studygroup;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.codingFriends.entity.Studygroup;

import java.math.BigInteger;

public interface StudygroupRepository extends JpaRepository<Studygroup, BigInteger>, StudygroupCustomRepository {

}
