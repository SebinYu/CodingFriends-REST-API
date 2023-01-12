package net.skhu.codingFriends.repository.participation;

import com.querydsl.jpa.impl.JPAQueryFactory;
import net.skhu.codingFriends.entity.participationrate;
import net.skhu.codingFriends.entity.studygroup;
import org.springframework.stereotype.Repository;

import static net.skhu.codingFriends.entity.Qapply.apply;
import static net.skhu.codingFriends.entity.Qparticipationrate.participationrate;
import java.util.List;

@Repository
public class ParticipationCustomRepositoryImpl implements ParticipationCustomRepository {

    private final JPAQueryFactory jPAQueryFactory;

    public ParticipationCustomRepositoryImpl(JPAQueryFactory jPAQueryFactory) {
        this.jPAQueryFactory = jPAQueryFactory;
    }

    @Override
    public List<participationrate> findByStudygroup(studygroup studygroupTemp) {
        return jPAQueryFactory
                .selectFrom(participationrate)
                .where(participationrate.studygroup.eq(studygroupTemp))
                .fetch();
    }

    @Override
    public List<net.skhu.codingFriends.entity.participationrate> findByStudygroupAndStatus(studygroup studygroupTemp) {
        return jPAQueryFactory
                .selectFrom(participationrate)
                .where(participationrate.studygroup.eq(studygroupTemp), participationrate.weeklyAttendance.eq("미정"))
                .fetch();    }


}
