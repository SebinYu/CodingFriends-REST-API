package net.skhu.codingFriends.batch.writer;

import lombok.extern.slf4j.Slf4j;
import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.repository.UserRepository;
import net.skhu.codingFriends.repository.apply.ApplyRepository;
import net.skhu.codingFriends.repository.studygroup.StudygroupRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class OrderWriter implements ItemWriter<apply> {
    @Autowired
    ApplyRepository applyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudygroupRepository studygroupRepository;

    @Override
    public void write(List<? extends apply> list) throws Exception {
      log.debug("item writer: {}", list.get(0));
        list.get(0).setUser(userRepository.findById(list.get(0).getUserIdtemp().intValue()).get());
        list.get(0).setStudygroup(studygroupRepository.findById(list.get(0).getStudyIdTemp()).get());
        applyRepository.saveAllAndFlush(list);
    }

}
