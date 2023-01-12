package net.skhu.codingFriends.repository.review;

import com.querydsl.jpa.impl.JPAQueryFactory;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.apply.ApplyCustomRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

import static net.skhu.codingFriends.entity.Qapply.apply;
import static net.skhu.codingFriends.entity.Qstudygroup.studygroup;

@Repository
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {

    private final JPAQueryFactory jPAQueryFactory;

    public ReviewCustomRepositoryImpl(JPAQueryFactory jPAQueryFactory) {
        this.jPAQueryFactory = jPAQueryFactory;
    }

//    @Override
//    public List<net.skhu.codingFriends.entity.apply> findByApplierID(BigInteger id) {
//        return jPAQueryFactory
//                .selectFrom(apply)
//                .where(apply.apply_id.eq(id))
//                .fetch();
//    }


}
