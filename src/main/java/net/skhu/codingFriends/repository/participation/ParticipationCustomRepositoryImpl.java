package net.skhu.codingFriends.repository.participation;

import com.querydsl.jpa.impl.JPAQueryFactory;
import net.skhu.codingFriends.entity.Studygroup;
import net.skhu.codingFriends.entity.Participationrate;
import org.springframework.stereotype.Repository;

import static net.skhu.codingFriends.entity.QParticipationrate.participationrate;
import java.util.List;

@Repository
public class ParticipationCustomRepositoryImpl implements ParticipationCustomRepository {

    private final JPAQueryFactory jPAQueryFactory;

    public ParticipationCustomRepositoryImpl(JPAQueryFactory jPAQueryFactory) {
        this.jPAQueryFactory = jPAQueryFactory;
    }

    @Override
    public List<Participationrate> findByStudygroup(Studygroup studygroupTemp) {
        return jPAQueryFactory
                .selectFrom(participationrate)
                .where(participationrate.studygroup.eq(studygroupTemp))
                .fetch();
    }

    @Override
    public List<Participationrate> findByStudygroupAndStatus(Studygroup studygroupTemp) {
        return jPAQueryFactory
                .selectFrom(participationrate)
                .where(participationrate.studygroup.eq(studygroupTemp), participationrate.weeklyAttendance.eq("미정"))
                .fetch();
    }




}
