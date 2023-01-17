package net.skhu.codingFriends.repository.studygroup;

import net.skhu.codingFriends.entity.studygroup;

import java.util.List;

public interface StudygroupCustomRepository {

    List<studygroup> findByTitleContaining(String keyword);

    List<studygroup> searchWithLearningMaterial_idAndKeyword(Integer learningMaterial_id, String keyword);
}
