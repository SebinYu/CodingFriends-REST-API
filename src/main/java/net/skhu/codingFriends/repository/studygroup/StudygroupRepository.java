package net.skhu.codingFriends.repository.studygroup;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.codingFriends.entity.studygroup;

import java.math.BigInteger;

public interface StudygroupRepository extends JpaRepository<studygroup, BigInteger>, StudygroupCustomRepository {

}
