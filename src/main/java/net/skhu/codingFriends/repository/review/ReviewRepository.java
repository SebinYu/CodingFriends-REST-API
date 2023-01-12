package net.skhu.codingFriends.repository.review;

import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.entity.review;
import net.skhu.codingFriends.repository.apply.ApplyCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ReviewRepository extends JpaRepository<review, BigInteger>, ReviewCustomRepository {


}
