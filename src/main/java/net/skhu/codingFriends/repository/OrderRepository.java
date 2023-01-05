package net.skhu.codingFriends.repository;

import net.skhu.codingFriends.entity.studygroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface OrderRepository extends JpaRepository<studygroup, BigInteger> {
}
