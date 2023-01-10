package net.skhu.codingFriends.service;

import net.skhu.codingFriends.dto.ApplyDto;
import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;

import java.util.List;

public interface LeaderService {
    List<apply> getApplications(user user);

    List<studygroup> getStudygroups(user user);
}
