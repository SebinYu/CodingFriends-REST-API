package net.skhu.codingFriends.repository.studygroup;

import net.skhu.codingFriends.entity.Studygroup;
import net.skhu.codingFriends.entity.User;

import java.util.List;

public interface StudygroupCustomRepository {

    List<Studygroup> findByTitleContaining(String keyword);

    List<Studygroup> searchWithLearningMaterial_idAndKeyword(Integer learningMaterial_id, String keyword);

    List<Studygroup> findByUserID(User user1);

    long updateCurrentNum(Studygroup updatedStudygroup);
}
