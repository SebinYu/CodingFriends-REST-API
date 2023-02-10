package net.skhu.codingFriends.repository.apply;

import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;

import java.math.BigInteger;
import java.util.List;

public interface ApplyCustomRepository {

    List<apply> findByUser(user user1);
    List<apply> findByApplierID(BigInteger id);

    long updateApplyStatus(apply applyTemp);

    List<apply> findByStudygroup(studygroup studygroup_id);
}
