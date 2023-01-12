package net.skhu.codingFriends.repository.participation;

import net.skhu.codingFriends.entity.participationrate;
import net.skhu.codingFriends.entity.studygroup;

import java.util.List;

public interface ParticipationCustomRepository {
    List<participationrate> findByStudygroup(studygroup studygroupTemp);
}
