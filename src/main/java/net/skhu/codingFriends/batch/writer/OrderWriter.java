package net.skhu.codingFriends.batch.writer;

import lombok.extern.slf4j.Slf4j;
import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.repository.apply.ApplyForMailRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class OrderWriter implements ItemWriter<apply> {
    @Autowired
    ApplyForMailRepository applyForMailRepository;

    @Override
    public void write(List<? extends apply> list) throws Exception {
      log.debug("item writer: {}", list.get(0));
        applyForMailRepository.saveAllAndFlush(list);
    }

}
