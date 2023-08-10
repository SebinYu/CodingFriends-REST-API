package net.skhu.codingFriends.repository.review;

import com.querydsl.jpa.impl.JPAQueryFactory;
import net.skhu.codingFriends.entity.Review;
import net.skhu.codingFriends.entity.User;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

import static net.skhu.codingFriends.entity.QReview.review;

@Repository
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {

    private final JPAQueryFactory jPAQueryFactory;

    public ReviewCustomRepositoryImpl(JPAQueryFactory jPAQueryFactory) {
        this.jPAQueryFactory = jPAQueryFactory;
    }

    @Override
    public List<Review> findByUser(User user) {
        return jPAQueryFactory
                .selectFrom(review)
                .where(review.user.eq(user))
                .fetch();
    }

    @Override
    public long updateObjection(BigInteger review_id) {
        return jPAQueryFactory
                .update(review)
                .set(review.objection, 1)
                .where(review.review_id.eq(review_id))
                .execute();
    }


}
