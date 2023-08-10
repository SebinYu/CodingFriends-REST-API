package net.skhu.codingFriends.repository.apply;

import com.querydsl.jpa.impl.JPAQueryFactory;
import static net.skhu.codingFriends.entity.QStudygroup.studygroup;

import net.skhu.codingFriends.entity.Apply;
import net.skhu.codingFriends.entity.Studygroup;
import net.skhu.codingFriends.entity.User;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

import java.util.List;

import static net.skhu.codingFriends.entity.QApply.apply;

@Repository
public class ApplyCustomRepositoryImpl implements ApplyCustomRepository {

    private final JPAQueryFactory jPAQueryFactory;

    public ApplyCustomRepositoryImpl(JPAQueryFactory jPAQueryFactory) {
        this.jPAQueryFactory = jPAQueryFactory;
    }

    @Override
    public List<Apply> findByApplierID(BigInteger id) {
        return jPAQueryFactory
                .selectFrom(apply)
                .where(apply.apply_id.eq(id))
                .fetch();
    }

    @Override
    public long updateApplyStatus(Apply applyTemp) {
        return jPAQueryFactory
                .update(apply)
                .set(apply.applyStatus, applyTemp.getApplyStatus())
                .where(apply.apply_id.eq(applyTemp.getApply_id()))
                .execute();
    }

    @Override
    public List<Apply> findByStudygroup(Studygroup studygroup) {
        return jPAQueryFactory
                .selectFrom(apply)
                .where(apply.studygroup.eq(studygroup), apply.applyStatus.eq("등록"))
                .fetch();
    }

    @Override
    public List<Apply> findByUser(User user1) {
        return jPAQueryFactory
                .selectFrom(apply)
                .join(apply.studygroup, studygroup)
                .where(studygroup.user.eq(user1))
                .fetch();
    }


}
