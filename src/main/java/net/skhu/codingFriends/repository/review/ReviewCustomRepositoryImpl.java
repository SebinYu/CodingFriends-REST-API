package net.skhu.codingFriends.repository.review;

import com.querydsl.jpa.impl.JPAQueryFactory;
import net.skhu.codingFriends.entity.review;
import net.skhu.codingFriends.entity.user;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

import static net.skhu.codingFriends.entity.Qreview.review;

@Repository
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {

    private final JPAQueryFactory jPAQueryFactory;

    public ReviewCustomRepositoryImpl(JPAQueryFactory jPAQueryFactory) {
        this.jPAQueryFactory = jPAQueryFactory;
    }

    @Override
    public List<review> findByUser(user user) {
        return jPAQueryFactory
                .selectFrom(review)
                .where(review.user.eq(user))
                .fetch();
    }



}
