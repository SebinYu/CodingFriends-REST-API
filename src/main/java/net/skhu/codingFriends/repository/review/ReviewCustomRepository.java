package net.skhu.codingFriends.repository.review;

import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.entity.review;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;

import java.math.BigInteger;
import java.util.List;

public interface ReviewCustomRepository {
        List<review> findByUser(user user);

        long updateObjection(BigInteger review_id);
}
