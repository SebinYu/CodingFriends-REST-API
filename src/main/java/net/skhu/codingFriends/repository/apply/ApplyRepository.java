package net.skhu.codingFriends.repository.apply;

import net.skhu.codingFriends.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ApplyRepository extends JpaRepository<Apply, BigInteger>, ApplyCustomRepository {


}
