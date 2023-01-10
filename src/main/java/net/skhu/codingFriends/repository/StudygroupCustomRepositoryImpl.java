package net.skhu.codingFriends.repository;

import static net.skhu.codingFriends.entity.Qstudygroup.studygroup;
import com.querydsl.jpa.impl.JPAQueryFactory;
import net.skhu.codingFriends.entity.studygroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudygroupCustomRepositoryImpl implements StudygroupCustomRepository{

    private final JPAQueryFactory jPAQueryFactory;

    public StudygroupCustomRepositoryImpl(JPAQueryFactory jPAQueryFactory) {
        this.jPAQueryFactory = jPAQueryFactory;
    }

    @Override
    public List<studygroup> findByTitleContaining(String keyword) {
        return jPAQueryFactory
                .selectFrom(studygroup)
                .where(studygroup.title.contains(keyword))
                .fetch();
    }

    @Override
    public List<studygroup> searchWithLearningMaterial_idAndKeyword(Integer learningMaterial_id, String keyword) {
        return null;
    }
}
