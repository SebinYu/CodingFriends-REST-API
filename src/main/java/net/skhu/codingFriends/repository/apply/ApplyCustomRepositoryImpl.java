package net.skhu.codingFriends.repository.apply;

import com.querydsl.jpa.impl.JPAQueryFactory;
import static net.skhu.codingFriends.entity.Qstudygroup.studygroup;

import net.skhu.codingFriends.entity.user;
import org.springframework.stereotype.Repository;

import java.util.List;

import static net.skhu.codingFriends.entity.Qapply.apply;

@Repository
public class ApplyCustomRepositoryImpl implements ApplyCustomRepository {

    private final JPAQueryFactory jPAQueryFactory;

    public ApplyCustomRepositoryImpl(JPAQueryFactory jPAQueryFactory) {
        this.jPAQueryFactory = jPAQueryFactory;
    }

    @Override
    public List<net.skhu.codingFriends.entity.apply> findByUser(user user1) {
        return jPAQueryFactory
                .selectFrom(apply)
                .join(apply.studygroup, studygroup)
                .where(studygroup.user.eq(user1))
                .fetch();
    }

}
