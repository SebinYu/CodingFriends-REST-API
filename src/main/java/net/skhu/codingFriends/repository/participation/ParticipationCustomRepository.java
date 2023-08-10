package net.skhu.codingFriends.repository.participation;

import net.skhu.codingFriends.entity.Studygroup;
import net.skhu.codingFriends.entity.Participationrate;

import java.util.List;

public interface ParticipationCustomRepository {
    List<Participationrate> findByStudygroup(Studygroup studygroupTemp);

    List<Participationrate> findByStudygroupAndStatus(Studygroup studygroupTemp);
}
