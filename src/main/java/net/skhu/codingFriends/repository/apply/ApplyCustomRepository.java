package net.skhu.codingFriends.repository.apply;

import net.skhu.codingFriends.entity.Apply;
import net.skhu.codingFriends.entity.Studygroup;
import net.skhu.codingFriends.entity.User;

import java.math.BigInteger;
import java.util.List;

public interface ApplyCustomRepository {

    List<Apply> findByUser(User user1);
    List<Apply> findByApplierID(BigInteger id);

    long updateApplyStatus(Apply applyTemp);

    List<Apply> findByStudygroup(Studygroup studygroup_id);
}
