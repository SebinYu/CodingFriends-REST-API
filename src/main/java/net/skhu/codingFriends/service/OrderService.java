package net.skhu.codingFriends.service;

import net.skhu.codingFriends.entity.Studygroup;

import java.util.List;

public interface OrderService {
    List<Studygroup> getStudygroupDependOnUpdateDate();

    List<Studygroup> getStudygroupDependOnStartDate();
}
