package net.skhu.codingFriends.repository.review;

import net.skhu.codingFriends.entity.User;
import net.skhu.codingFriends.entity.Review;

import java.math.BigInteger;
import java.util.List;

public interface ReviewCustomRepository {
        List<Review> findByUser(User user);

        long updateObjection(BigInteger review_id);
}
