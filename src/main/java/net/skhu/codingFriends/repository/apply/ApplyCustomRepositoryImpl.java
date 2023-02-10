package net.skhu.codingFriends.repository.apply;

import com.querydsl.jpa.impl.JPAQueryFactory;
import static net.skhu.codingFriends.entity.Qstudygroup.studygroup;

import net.skhu.codingFriends.entity.user;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

import java.time.LocalDate;
import java.util.Collections;

import java.util.List;

import static net.skhu.codingFriends.entity.Qapply.apply;

@Repository
public class ApplyCustomRepositoryImpl implements ApplyCustomRepository {

    private final JPAQueryFactory jPAQueryFactory;

    public ApplyCustomRepositoryImpl(JPAQueryFactory jPAQueryFactory) {
        this.jPAQueryFactory = jPAQueryFactory;
    }

    @Override
    public List<net.skhu.codingFriends.entity.apply> findByApplierID(BigInteger id) {
        return jPAQueryFactory
                .selectFrom(apply)
                .where(apply.apply_id.eq(id))
                .fetch();
    }

    @Override
    public long updateApplyStatus(net.skhu.codingFriends.entity.apply applyTemp) {
        return jPAQueryFactory
                .update(apply)
                .set(apply.applyStatus, applyTemp.getApplyStatus())
                .where(apply.apply_id.eq(applyTemp.getApply_id()))
                .execute();
    }

    @Override
    public List<net.skhu.codingFriends.entity.apply> findByStudygroup(net.skhu.codingFriends.entity.studygroup studygroup) {
        return jPAQueryFactory
                .selectFrom(apply)
                .where(apply.studygroup.eq(studygroup), apply.applyStatus.eq("등록"))
                .fetch();
    }

    @Override
    public List<net.skhu.codingFriends.entity.apply> findByUser(user user1) {
        return jPAQueryFactory
                .selectFrom(apply)
                .join(apply.studygroup, studygroup)
                .where(studygroup.user.eq(user1))
                .fetch();
    }

    @Override
    public long updateMail_Sent(net.skhu.codingFriends.entity.apply applyTemp) {
        return jPAQueryFactory
                .update(apply)
                .set(apply.mail_sent, Collections.singletonList(1))
                .where(apply.apply_id.eq(applyTemp.getApply_id()))
                .execute();
    }


}
