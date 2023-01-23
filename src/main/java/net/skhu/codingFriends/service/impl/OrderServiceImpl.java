package net.skhu.codingFriends.service.impl;

import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.repository.OrderRepository;
import net.skhu.codingFriends.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    public OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<studygroup> updateDate(){
        return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "updateDate")) ;
    }

    @Transactional(readOnly = true)
    public List<studygroup> startDate() {
        return orderRepository.findAll(Sort.by(Sort.Direction.ASC, "startDate")) ;
    }

}
