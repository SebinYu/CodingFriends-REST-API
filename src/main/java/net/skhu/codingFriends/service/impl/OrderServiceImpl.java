package net.skhu.codingFriends.service.impl;

import net.skhu.codingFriends.entity.Studygroup;
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
    public List<Studygroup> updateDate(){
        return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "updateDate")) ;
    }

    @Transactional(readOnly = true)
    public List<Studygroup> startDate() {
        return orderRepository.findAll(Sort.by(Sort.Direction.ASC, "startDate")) ;
    }

}
