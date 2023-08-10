package net.skhu.codingFriends.repository.studygroup;

import static net.skhu.codingFriends.entity.QStudygroup.studygroup;
import com.querydsl.jpa.impl.JPAQueryFactory;
import net.skhu.codingFriends.entity.Studygroup;
import net.skhu.codingFriends.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudygroupCustomRepositoryImpl implements StudygroupCustomRepository {

    private final JPAQueryFactory jPAQueryFactory;

    public StudygroupCustomRepositoryImpl(JPAQueryFactory jPAQueryFactory) {
        this.jPAQueryFactory = jPAQueryFactory;
    }

    @Override
    public List<Studygroup> findByTitleContaining(String keyword) {
        return jPAQueryFactory
                .selectFrom(studygroup)
                .where(studygroup.title.contains(keyword))
                .fetch();
    }

    @Override
    public List<Studygroup> searchWithLearningMaterial_idAndKeyword(Integer learningMaterial_id, String keyword) {
        return null;
    }

    @Override
    public List<Studygroup> findByUserID(User user1) {
        return jPAQueryFactory
                .selectFrom(studygroup)
                .where(studygroup.user.eq(user1))
                .fetch();
    }

    public long updateCurrentNum(Studygroup updatedStudygroup) {
        return jPAQueryFactory
                .update(studygroup)
                .set(studygroup.currentNum, updatedStudygroup.getCurrentNum())
                .where(studygroup.studyGroup_id.eq(updatedStudygroup.getStudyGroup_id()))
                .execute();
    }
}
