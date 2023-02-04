package net.skhu.codingFriends.repository.apply;

import net.skhu.codingFriends.entity.apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ApplyForMailRepository extends JpaRepository<apply, BigInteger> {
}
