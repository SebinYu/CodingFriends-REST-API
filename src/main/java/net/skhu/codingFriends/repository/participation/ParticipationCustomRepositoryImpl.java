package net.skhu.codingFriends.repository.participation;

import com.querydsl.jpa.impl.JPAQueryFactory;
import net.skhu.codingFriends.entity.Qstudygroup;
import net.skhu.codingFriends.entity.studygroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParticipationCustomRepositoryImpl implements ParticipationCustomRepository {

    private final JPAQueryFactory jPAQueryFactory;

    public ParticipationCustomRepositoryImpl(JPAQueryFactory jPAQueryFactory) {
        this.jPAQueryFactory = jPAQueryFactory;
    }

//    @Override
//    public List<studygroup> findByTitleContaining(String keyword) {
//        return jPAQueryFactory
//                .selectFrom(Qstudygroup.studygroup)
//                .where(Qstudygroup.studygroup.title.contains(keyword))
//                .fetch();
//    }

}
