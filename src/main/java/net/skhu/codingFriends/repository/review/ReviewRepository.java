package net.skhu.codingFriends.repository.review;

import net.skhu.codingFriends.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ReviewRepository extends JpaRepository<Review, BigInteger>, ReviewCustomRepository {


}
