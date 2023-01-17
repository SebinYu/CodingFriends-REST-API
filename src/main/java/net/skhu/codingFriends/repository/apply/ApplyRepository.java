package net.skhu.codingFriends.repository.apply;

import net.skhu.codingFriends.entity.apply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ApplyRepository extends JpaRepository<apply, BigInteger>, ApplyCustomRepository {


}
