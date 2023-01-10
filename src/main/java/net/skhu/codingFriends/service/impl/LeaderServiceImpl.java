package net.skhu.codingFriends.service.impl;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.ApplyDto;
import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.apply.ApplyRepository;
import net.skhu.codingFriends.service.LeaderService;
import net.skhu.codingFriends.service.StudygroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaderServiceImpl implements LeaderService {
    private final ApplyRepository applyRepository;
    @Override
    public List<apply> getApplications(user user) {

        return applyRepository.findByUser(user);
    }
}