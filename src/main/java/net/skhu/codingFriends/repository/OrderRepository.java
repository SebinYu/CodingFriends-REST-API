package net.skhu.codingFriends.repository;

import net.skhu.codingFriends.entity.Studygroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface OrderRepository extends JpaRepository<Studygroup, BigInteger> {
}
